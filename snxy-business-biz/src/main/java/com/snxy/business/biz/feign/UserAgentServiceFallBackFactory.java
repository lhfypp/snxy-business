
package com.snxy.business.biz.feign;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserAgentServiceFallBackFactory implements UserAgentService {
    @Override
    public void refresh(Long systemUserId) {
        log.error("刷新用户缓存信息失败");
    }
}