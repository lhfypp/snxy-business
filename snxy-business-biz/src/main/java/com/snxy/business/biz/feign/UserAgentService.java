package com.snxy.business.biz.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;

@FeignClient(name="snxy-user-agent",fallbackFactory = UserAgentServiceFallBackFactory.class)
public interface UserAgentService {

    @PostMapping(value = "/user/change/CacheUser")
    void refresh(@RequestParam("systemUserId")Long systemUserId);

}
