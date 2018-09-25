package com.snxy.business.biz.feign;

import com.snxy.common.response.ResultData;
import com.snxy.user.agent.service.vo.LoginUserVO;
import com.snxy.user.agent.service.vo.SystemUserVO;
import org.springframework.stereotype.Component;

/**
 * Created by 24398 on 2018/9/21.
 */

@Component
public class UserAgentServiceFallBackFactory implements UserAgentService {

    private static final String msg = "user-agent服务降级";

    @Override
    public ResultData<SystemUserVO> login(LoginUserVO loginUserVO) {
        return ResultData.fail("登陆失败 ："+msg);
    }
}