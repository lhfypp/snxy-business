package com.snxy.business.biz.asy;


import com.snxy.business.biz.feign.UserAgentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class AsyRefreshCacheUser {
    @Resource
    private UserAgentService userAgentService;

    @Async
    public void refreshCacheUser(Long systemUserId){
        userAgentService.refresh(systemUserId);
        log.info("当前线程名  ："+Thread.currentThread().getName());
    }
}
