package com.snxy.business.biz.feign;

import com.snxy.business.domain.MessageInfo;
import com.snxy.common.response.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;

@FeignClient(name = "snxy-push-service",fallbackFactory = MessagePushServiceFallBack.class)
public interface MessagePushService {
    @RequestMapping(value = "/uPush/send")
    ResultData pushMessage(@RequestBody MessageInfo messageInfo);
}
