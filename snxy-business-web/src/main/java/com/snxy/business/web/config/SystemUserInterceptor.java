package com.snxy.business.web.config;

import com.alibaba.fastjson.JSONObject;
import com.snxy.business.service.vo.SystemUserVo;
import com.snxy.common.response.ResultData;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

/**
 * 对重要页面添加openid,通过Attribute可以获取
 * Created by lvhai on 2018/10/18.
 */
@Slf4j
@ConfigurationProperties(prefix = "business")
@Component
@Data
public class SystemUserInterceptor implements HandlerInterceptor {

    // private static final String WECHAT_CODE_OPENID="/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_base&state=1#wechat_redirect";

 //   @Value(value = "${business.localDebug}")
    private boolean localDebug ;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

//        String requestUrl = request.getRequestURL().toString();
//        String queryString = request.getQueryString();
//        if (!StringUtils.isEmpty(queryString)) {
//            requestUrl += "?" + queryString;
//        }
//        log.debug("请求url地址:{}", requestUrl);
        String userJson = request.getHeader("systemUser");
        if (StringUtils.isEmpty(userJson)) {
            ///TODO 正式系统需要设置false,并且返回验证信息
            if (localDebug == true) {
                setDebugSystemUser(  request);
                return true;
            }
            ResultData<String> errorResult = ResultData.fail("没有用户信息,请求被拒绝", 401);
            PrintWriter writer = null;
            try {
                writer = response.getWriter();
                writer.write(JSONObject.toJSONString(errorResult));
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (writer != null) {
                    writer.close();
                }
            }
            return false;
        }

        String newJson = URLDecoder.decode(userJson, "UTF-8");
        log.debug("用户信息JsonString:{}",newJson);
        SystemUserVo systemUserVO = JSONObject.parseObject(newJson, SystemUserVo.class);// mapper.readValue(newJson, SystemUserVO.class);

        request.setAttribute("systemUser", systemUserVO);

        return true;
    }
   private void setDebugSystemUser(HttpServletRequest request){
        String debugUserJson="{\"token\":\"F62ECF21F6E66FB1BAE4C0BFEAA5C9ADB7C1B460DF33A4AF72024AA4DF981700D28A27C878D25ABB00F363A0EABBDA02\",\"name\":\"thomas\",\"systemUserId\":1,\"identityTypes\":[{\"systemUserId\":1,\"identityId\":1,\"isActive\":true,\"identityName\":\"代办\"},{\"systemUserId\":1,\"identityId\":2,\"isActive\":false,\"identityName\":\"商户公司\"},{\"systemUserId\":1,\"identityId\":3,\"isActive\":false,\"identityName\":\"司机\"}],\"expireTime\":1540951624593,\"account\":\"10000001\"}";
       SystemUserVo systemUserVO = JSONObject.parseObject(debugUserJson, SystemUserVo.class);// mapper.readValue(newJson, SystemUserVO.class);

       request.setAttribute("systemUser", systemUserVO);
   }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}
