package com.snxy.business.biz.feign;



import com.snxy.business.service.vo.SystemUserVO;
import com.snxy.common.response.ResultData;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name="snxy-user-agent",fallbackFactory = UserAgentServiceFallBackFactory.class)
public interface UserAgentService {




    @PostMapping(value = "/user/change/CacheUser")
    void refresh(@RequestParam("systemUserId")Long systemUserId);


}
