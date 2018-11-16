package com.snxy.business.biz.impl;

import com.snxy.business.biz.asy.AsyRefreshCacheUser;
import com.snxy.business.biz.feign.UserAgentService;
import com.snxy.business.domain.UserIdentity;
import com.snxy.business.service.OnlineUserService;
import com.snxy.business.service.RegisterService;
import com.snxy.business.service.SystemUserService;
import com.snxy.business.service.UserIdentityService;
import com.snxy.common.exception.BizException;
import com.snxy.common.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RegisterServiceImpl implements RegisterService {
    @Resource
    private OnlineUserService onlineUserService;
    @Resource
    private SystemUserService systemUserService;
    @Resource
    private UserIdentityService userIdentityService;
    @Resource
    private AsyRefreshCacheUser asyRefreshCacheUser;
    @Resource
    private RedisTemplate redisTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void chooseIdentity(Long systemUserId, String name, Integer identityId, Long onlineUserId){
        if(identityId!=5){
            if (name!=null&&!name.equals("")) {
                systemUserService.updateName(systemUserId, name);

                asyRefreshCacheUser.refreshCacheUser(systemUserId);
                log.info("当前线程名   ："+Thread.currentThread().getName());
                onlineUserService.updateName(systemUserId, name);
            }else {
                throw new BizException("您还没有输入姓名，请先输入姓名。");
            }
        }
        UserIdentity userIdentity = UserIdentity.builder()
                    .identityId(identityId)
                    .onlineUserId(onlineUserId)
                    .build();

        UserIdentity userIdentity1 = userIdentityService.selectByOnlineUserId(userIdentity.getOnlineUserId(),userIdentity.getIdentityId());
//        if(userIdentity1!=null){
//            throw new BizException("已添加，请勿重复添加");
//        }
        userIdentityService.insertIdentity(userIdentity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void newPassword(String password, Long systemUserId) {
        systemUserService.updatePassword(password,systemUserId);
    }

    @Override
    public void updateRegisterPWD(Long systemUserId, String mobile ,String smsCode, String newPwd) {
        //判断smsCode是否过期
        Object obj = redisTemplate.opsForValue().get(mobile);
        if (obj==null){
            throw new BizException("验证码已过期");
        }
        //如果没过期，就拿用户输入的手机号作为key从redis中查
        String code = (String)redisTemplate.opsForValue().get(mobile);
        if (smsCode.length() == 0 || smsCode.isEmpty()) {
            throw new BizException("验证码为空");
        } else if (!code.equals(smsCode)) {
            throw new BizException("验证码输入错误");
        }
        systemUserService.updateRegisterPWD(systemUserId,newPwd);
    }

    @Override
    public String getSmsCode(String mobile) {
        //给当前手机号发送验证码 TODO
        String smsCode = RandomStringUtils.randomNumeric(6);
        //将验证码存到redis中，设置有效期为30分钟
        redisTemplate.opsForValue().set(mobile,smsCode,30, TimeUnit.MINUTES);
        return smsCode;
    }
}
