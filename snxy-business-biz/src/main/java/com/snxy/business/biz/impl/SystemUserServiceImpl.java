package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.SystemUserMapper;
import com.snxy.business.domain.SystemUser;
import com.snxy.business.domain.UserMobileCode;
import com.snxy.business.service.SystemUserService;
import com.snxy.common.exception.BizException;
import com.snxy.common.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class SystemUserServiceImpl implements SystemUserService {
    @Resource
    private SystemUserMapper systemUserMapper;

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public void modifyName(String name, Long userId) {
        SystemUser systemUser = new SystemUser();
        systemUser.setId(userId);
        systemUser.setChineseName(name);
        systemUserMapper.updateByPrimaryKeySelective(systemUser);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void smsCode(Long userId,String newMobile) {
        if(newMobile == null){
            throw new BizException("没有手机号");
        }
        if(!StringUtil.checkMobile(newMobile)){
            log.error("手机号格式错误: [{}]",newMobile);
            throw new BizException("手机号格式错误");
        }
        String mobile = systemUserMapper.selectMobileById(userId);
        /*
        * 调用接口，获取验证码
        *
        * */
        final String smsCode = "123456";

        UserMobileCode userMobileCode = new UserMobileCode();
        userMobileCode.setMobile(newMobile);
        userMobileCode.setSmsCode(smsCode);
        //存储在redis中
        String redisKey = userId.toString();
        redisTemplate.opsForValue().set(redisKey,userMobileCode,5, TimeUnit.MINUTES);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modifyPhone(Long userId, String smsCode) {
        if(smsCode == null){
            throw new BizException("验证码为空");
        }
        UserMobileCode userMobileCode = (UserMobileCode) redisTemplate.opsForValue().get(userId.toString());
        if (userMobileCode.getSmsCode().equals(smsCode)){
            SystemUser systemUser = null;
            systemUser.setId(userId);
            systemUser.setMobile(userMobileCode.getMobile());
            systemUserMapper.updateByPrimaryKeySelective(systemUser);
        }else {
            log.error("验证码输入错误:[{}]",smsCode);
            throw new BizException("验证码输入错误");
        }
    }

    @Override
    public void identityCheck(String identityNo, Long userId) {

    }

    @Override
    public void passwordReset(Long userId, String newPassword, String oldPassword) {

    }
}
