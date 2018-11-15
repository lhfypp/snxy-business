package com.snxy.business.web.config;

import com.alibaba.fastjson.JSONObject;
import com.snxy.business.domain.IdentityType;
import com.snxy.business.service.vo.IdentityVO;
import com.snxy.business.service.vo.SystemUserVO;
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
import java.util.ArrayList;
import java.util.List;

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
                this.setDebugSystemUser(  request);
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
        SystemUserVO systemUserVO = JSONObject.parseObject(newJson, SystemUserVO.class);// mapper.readValue(newJson, SystemUserVO.class);

        request.setAttribute("systemUser", systemUserVO);

        return true;
    }
   private void setDebugSystemUser(HttpServletRequest request){

       List<IdentityVO> identityVOList = new ArrayList<>();
       IdentityVO identityVO1 = IdentityVO.builder().id(1).identityName("商户负责人").build();
       IdentityVO identityVO2 = IdentityVO.builder().id(3).identityName("司机").build();
       identityVOList.add(identityVO1);
       identityVOList.add(identityVO2);
       SystemUserVO systemUserVO = SystemUserVO.builder().phone("13999999999").systemUserId(45L).onlineUserId(30L).name("二狗子").identityTypes(identityVOList).build();// mapper.readValue(newJson, SystemUserVO.class);

       request.setAttribute("systemUser", systemUserVO);
   }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}
