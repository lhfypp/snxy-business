package com.snxy.business.biz.feign;

import com.snxy.business.domain.MessageInfo;
import com.snxy.common.response.ResultData;
import org.springframework.stereotype.Component;

@Component
public class MessagePushServiceFallBack implements MessagePushService {
    private static final String msg = "snxy-push-service 服务降级";

    @Override
    public ResultData pushMessage(MessageInfo messageInfo) {
        return ResultData.fail("消息推送失败 ："+msg);
    }
}
