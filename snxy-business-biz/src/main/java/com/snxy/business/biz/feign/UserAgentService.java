package com.snxy.business.biz.feign;

<<<<<<< HEAD

import com.snxy.business.service.vo.SystemUserVO;
import com.snxy.common.response.ResultData;
=======
>>>>>>> 0715307c3886f405043faeee6e33e8c66c8ff20e
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;

@FeignClient(name="snxy-user-agent",fallbackFactory = UserAgentServiceFallBackFactory.class)
public interface UserAgentService {

<<<<<<< HEAD
     @RequestMapping("/user/login")
     ResultData<SystemUserVO> login(@RequestBody LoginUserVO loginUserVO);
=======
    @PostMapping(value = "/user/change/CacheUser")
    void refresh(@RequestParam("systemUserId")Long systemUserId);
>>>>>>> 0715307c3886f405043faeee6e33e8c66c8ff20e

}
