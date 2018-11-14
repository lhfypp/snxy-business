package com.snxy.business.biz.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.TIMEOUT;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.TimeoutUtils;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class redisTest {
    @Resource
    private RedisTemplate<String,Object> redisTemplate;
    @Test
    public void getRedisTemplate() {
        redisTemplate.opsForValue().set("test", "100",6,TimeUnit.MINUTES);
        log.info(redisTemplate.opsForValue().get("test").toString()+"222222");
    }
}
