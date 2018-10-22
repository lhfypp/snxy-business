package com.snxy.business.biz.schedule;


import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class OrderNoScheduler {

    @Resource
    private RedisTemplate redisTemplate;

    @Scheduled(cron = "0 0 0 * * ?")
    public void resetOrderNo(){
        redisTemplate.delete("orderNo");
        log.info("redis中orderNo的key删除");
    }
}
