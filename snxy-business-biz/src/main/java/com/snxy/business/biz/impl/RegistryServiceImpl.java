package com.snxy.business.biz.impl;

import com.snxy.business.domain.OnlineUser;
import com.snxy.business.domain.SystemUser;
import com.snxy.business.service.OnlineUserService;
import com.snxy.business.service.RegistryService;
import com.snxy.business.service.SystemUserService;
import com.snxy.common.exception.BizException;
import com.snxy.common.util.MD5Util;
import com.snxy.common.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.beans.ExceptionListener;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * Created by 24398 on 2018/9/19.
 */
@Service
@Slf4j
public class RegistryServiceImpl implements RegistryService{

    @Resource
    private SystemUserService systemUserService;
    @Resource
    private OnlineUserService onlineUserService;

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    private static final String TEST_SMS_CODE  = "111111";
    private static final String REGISTRY_KEY_TEMPLATE = "REGISTRY-%s";
    private static final Long EXPIRE_TIME = 5L;
    private static final Byte NORMAL_ACCOUNT_STATUS = 0;
    private static final Byte NON_DELETE = 1;
    private static final String ROW_PASSWORD  = "111111";


    private   static final String ACCOUNT_PREFIX = "1000000";
    private   Integer accountId = 1;

    private Lock lock = new ReentrantLock();    //注意这个地方


    @Override
    public void getRegistrySmsCode(String mobile) {
        StringUtil.checkMobile(mobile);
        // 先检查改手机号是否已经注册
        SystemUser systemUser = this.systemUserService.selectByMobile(mobile);
        if(systemUser != null){
            // 改手机号已注册，直接登陆
            throw new BizException("改手机号已经注册");
        }

        // 没有注册，调用smsService 发送验证码，同时保存验证码到redis
        String redisKey = String.format(REGISTRY_KEY_TEMPLATE,mobile);
        redisTemplate.opsForValue().set(redisKey,TEST_SMS_CODE,EXPIRE_TIME, TimeUnit.MINUTES);

    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void checkSmsCodeAndRegister(String mobile, String smsCode) {
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
         SystemUser systemUser  = new SystemUser();
           systemUser.setAccountStatus(NORMAL_ACCOUNT_STATUS);
           systemUser.setGmtCreate(new Date());
           systemUser.setIsDelete(NON_DELETE);
           systemUser.setMobile(mobile);
           systemUser.setPwd(MD5Util.encrypt(ROW_PASSWORD));
           systemUser.setRegDate(new Date());
           systemUser.setAccount(this.generateAccount());

         boolean flag = this.systemUserService.newSystemUser(systemUser);
         if(!flag){
             throw new BizException("注册失败，添加系统用户异常");
         }
       // 插入OnlineUser
        OnlineUser onlineUser = new OnlineUser();
           onlineUser.setPhone(mobile);
           onlineUser.setIsDelete((byte)1);
           onlineUser.setSystemUserId(systemUser.getId());

        boolean result = this.onlineUserService.newOnlineUser(onlineUser);
        if( !result ){
             throw new BizException("注册失败，添加在线用户失败");
        }

    }


    private String generateAccount(){
         lock.lock();
         StringBuilder sb = new StringBuilder(ACCOUNT_PREFIX);
         String account = sb.append(accountId.toString()).toString();
         accountId ++;
        return account;
    }

    public static void main(String[] args) {
        RegistryServiceImpl registryService = new RegistryServiceImpl();
        String account =   registryService.generateAccount();

        System.out.println("account  : "+account);
    }

    @Override
    public void changeInitPassword(Long systemUserId, String password) {
        this.systemUserService.changeInitPassword(systemUserId,password);
    }
}
