package com.snxy.business.biz.feign;

import com.snxy.common.response.ResultData;
import com.snxy.user.agent.service.vo.LoginUserVO;
import com.snxy.user.agent.service.vo.SystemUserVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 24398 on 2018/9/21.
 */
@FeignClient(name="snxy-user-agent",fallbackFactory = UserAgentServiceFallBackFactory.class)
public interface UserAgentService  {

     @RequestMapping("/user/login")
    ResultData<SystemUserVO> login(@RequestBody LoginUserVO loginUserVO);

}
