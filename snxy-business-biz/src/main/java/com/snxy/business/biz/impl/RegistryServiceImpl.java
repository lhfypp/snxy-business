package com.snxy.business.biz.impl;

import com.snxy.business.biz.feign.UserAgentService;
import com.snxy.business.domain.IdentityType;
import com.snxy.business.domain.OnlineUser;
import com.snxy.business.domain.SystemUser;

import com.snxy.business.service.*;
import com.snxy.business.service.vo.LoginUserVO;
import com.snxy.business.service.vo.SystemUserVo;
import com.snxy.common.exception.BizException;


import com.snxy.common.response.ResultData;
import com.snxy.common.util.MD5Util;


import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;



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
    private UserIdentityService userIdentityService;
    @Resource
    private IdentityTypeService identityTypeService;
    @Resource
    private UserAgentService userAgentService;


    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    private static final String TEST_SMS_CODE  = "111111";
    private static final String REGISTRY_KEY_TEMPLATE = "REGISTRY-%s";
    private static final Long EXPIRE_TIME = 5L;
    private static final Byte NORMAL_ACCOUNT_STATUS = 0;
    private static final Byte NON_DELETE = 1;
    private static final String ROW_PASSWORD  = "111111";
    private static final   String ACCOUNT_KEY = "Account_Suffix";
    private   static final String ACCOUNT_PREFIX = "1000000";
    private   static final Integer ACCOUNT_SUFFIX_INIT = 1;

  //  private Lock lock = new ReentrantLock();


    @PostConstruct
    public void initAccountIdSuffix(){
        Integer accountSuffix = (Integer) redisTemplate.opsForValue().get(ACCOUNT_KEY);
        if(accountSuffix == null){
            redisTemplate.opsForValue().set(ACCOUNT_KEY,ACCOUNT_SUFFIX_INIT);
        }

    }


    @Override
    public void getRegistrySmsCode(String mobile) {

        // 先检查改手机号是否已经注册
        boolean hasRegistered = this.checkMobileRegistered(mobile);
        if(hasRegistered){
            // 改手机号已注册，直接登陆
            throw new BizException("改手机号已经注册");
        }

        // 没有注册，调用smsService 发送验证码，同时保存验证码到redis
        String redisKey = String.format(REGISTRY_KEY_TEMPLATE,mobile);
        redisTemplate.opsForValue().set(redisKey,TEST_SMS_CODE,EXPIRE_TIME, TimeUnit.MINUTES);

    }

    boolean  checkMobileRegistered(String mobile){
        SystemUser systemUser = this.systemUserService.selectByMobile(mobile);
        if(systemUser == null){
            return false;
        }
        return true;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void checkSmsCodeAndRegister(String mobile, String smsCode) {

        // 先检查是否已经预定
        boolean hasRegistered = this.checkMobileRegistered(mobile);
        if(hasRegistered){
            throw new BizException("注册失败，该手机号已经注册");
        }

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
           onlineUser.setIsDelete(NON_DELETE);
           onlineUser.setSystemUserId(systemUser.getId());
        boolean result = this.onlineUserService.newOnlineUser(onlineUser);
        if( !result ){
             throw new BizException("注册失败，添加在线用户失败");
        }

    }


    private String generateAccount(){
         StringBuilder sb = new StringBuilder(ACCOUNT_PREFIX);
         Long num  = (Long) this.redisTemplate.opsForValue().increment(ACCOUNT_KEY,1L);
         String account = sb.append(num.toString()).toString();
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

    @Override
    public List<IdentityType> listIdentity() {
        boolean excludeDeleted = true;
        List<IdentityType> userIdentities = this.identityTypeService.listAll(excludeDeleted);
        return userIdentities;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveIdentityAndName(Long onlineUserId, String name, List<Integer> identities) {
        // 查询onlineUserId
        OnlineUser onlineUser = this.onlineUserService.selectById(onlineUserId);
        if(onlineUser == null){
            log.error("设置用户身份类型失败： [{}]","没有查询到在线用户");
            throw new BizException("没有查询到在线用户");
        }
        Long systemUserId = onlineUser.getSystemUserId();
        // 保存姓名
        boolean flag = this.systemUserService.saveUserName(systemUserId,name);
        if(!flag){
            log.error("设置用户身份类型失败： [{}]","保存用户姓名失败");
            throw new BizException("保存用户姓名失败");
        }
        // 保存身份类型  之前的全部删除
        this.userIdentityService.setUserIdentity(onlineUserId,identities);

    }

    public SystemUserVo getToken(LoginUserVO loginUserVO){
        loginUserVO.setPassword(ROW_PASSWORD);
        ResultData<SystemUserVo> resultData = this.userAgentService.login(loginUserVO);
        if( !resultData.isResult()){
            log.error("获取token失败 ： [{}]",resultData.getMsg());
            throw new BizException(resultData.getMsg());
        }
        SystemUserVo systemUserVO = resultData.getData();
        return systemUserVO;
    }

}
