package com.snxy.business.biz.impl;

import com.snxy.business.service.RegistryService;
import com.snxy.common.exception.BizException;
import com.snxy.common.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;


/**
 * Created by 24398 on 2018/9/19.
 */
@Service
@Slf4j
public class RegistryServiceImpl implements RegistryService{




    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    private static final String TEST_SMS_CODE  = "111111";
    private static final String REGISTRY_KEY_TEMPLATE = "REGISTRY-%s";
    private static final Long EXPIRE_TIME = 5L;
    @Override
    public void getRegistrySmsCode(String mobile) {
        StringUtil.checkMobile(mobile);
        // 先检查改手机号是否已经注册

        // 没有注册，调用smsService 发送验证码，同时保存验证码到reids
        String redisKey = String.format(REGISTRY_KEY_TEMPLATE,mobile);
        redisTemplate.opsForValue().set(redisKey,TEST_SMS_CODE,EXPIRE_TIME, TimeUnit.MINUTES);

    }


    @Override
    public void checkSmsCode(String mobile, String smsCode) {
        String redisKey = String.format(REGISTRY_KEY_TEMPLATE,mobile);
        String cacheSmsCode = (String) redisTemplate.opsForValue().get(redisKey);
        if(cacheSmsCode == null){
          // 过期
            throw new BizException("验证码过期，请重新获取");
        }
        if(!smsCode.equals(cacheSmsCode)){
            throw new BizException("验证码输入有误");
        }

        // system_user online_user 表插入数据



    }
}
