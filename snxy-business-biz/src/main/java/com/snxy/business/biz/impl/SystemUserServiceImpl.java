package com.snxy.business.biz.impl;

import com.snxy.business.biz.feign.SmsService;
import com.snxy.business.dao.mapper.SystemUserMapper;
import com.snxy.business.domain.SystemUser;
import com.snxy.business.service.SystemUserService;
import com.snxy.common.exception.BizException;
import com.snxy.common.util.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class SystemUserServiceImpl implements SystemUserService {
    @Resource
    private SystemUserMapper systemUserMapper;
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private SmsService smsService;

    /**
     * 更换系统用户姓名
     *
     * @param systemUserId
     * @param name
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateName(Long systemUserId, String name) {
        systemUserMapper.updateNameByPrimaryKey(systemUserId, name, new Date());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertSystemUser(SystemUser systemUser) {
        systemUserMapper.insertSystemUser(systemUser);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertSystemUserList(List<SystemUser> systemUserList) {
        systemUserMapper.insertSystemUserList(systemUserList);
    }

    /**
     * 更换系统用户手机号
     *
     * @param systemUserId
     * @param newMobile
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateSystemMobile(Long systemUserId, String newMobile) {
        systemUserMapper.updateSystemMobile(systemUserId, newMobile);
    }

    @Override
    public SystemUser selectByMobile(String phone) {
        SystemUser systemUser = systemUserMapper.selectByPhone(phone);
        return systemUser;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePersonalPassWord(String oldPwd, String newPwd, Long systemUserId) {
        //根据systemUserId查询当前用户保存在数据库中密码
        String DBPwd = systemUserMapper.selectPwdBySystemUserId(systemUserId);
        if (DBPwd==null){
            throw new BizException("当前用户没有设置密码，请先设置");
        }else if (!DBPwd.equals(MD5Util.encrypt(oldPwd))){
            throw new BizException("对不起，您输入的旧密码有误，请重新输入");
        }
        //旧密码和数据库密码一致后，可以修改密码，要将新密码加密再修改
        systemUserMapper.updatePwdBySystemUserId(systemUserId,MD5Util.encrypt(newPwd));
    }

    @Override
    public void updatePassword(String password, Long systemUserId) {
        systemUserMapper.updatePassword(password,systemUserId);
    }

    @Override
    public void updatePersonalMobile(Long systemUserId, String newMobile, String smsCode) {

        //判断验证码是否过期
        Object obj = redisTemplate.opsForValue().get(newMobile);
        if (obj==null){
            throw new BizException("验证码已过期，重新获取");
        }
        //如果没过期，就从redis中获取
        String code1 = (String)redisTemplate.opsForValue().get(newMobile);
        if (smsCode.length() == 0 || smsCode.isEmpty()) {
            throw new BizException("验证码为空");
        } else if (!code1.equals(smsCode)) {
            throw new BizException("验证码输入错误");
        }
        //验证码正确后可以修改手机号
         systemUserMapper.updatePersonalMobile(systemUserId,newMobile);
    }

    @Override
    public void updateRegisterPWD(Long systemUserId, String newPwd) {
        systemUserMapper.updateRegisterPWD(systemUserId,newPwd);
    }

    @Override
    public void isTruePwd(Long systemUserId ,String password) {
        String DBpwd = systemUserMapper.selectPwdBySystemUserId(systemUserId);
        //与用户传入的密码比对，正确就修改手机号，否则就提示错误
        if (DBpwd==null){
            throw new BizException("对不起，你还没设置密码哦");
        }else if (! DBpwd.equals(MD5Util.encrypt(password))){
            throw new BizException("您输入的密码错误，请再次输入");
        }
    }

    @Override
    public String getSmsCode(String newMobile) {
        String smsCode = RandomStringUtils.randomNumeric(6);
        smsService.sendSmsCode(newMobile,smsCode,1L);
        redisTemplate.opsForValue().set(newMobile,smsCode,20,TimeUnit.MINUTES);
        return smsCode;
    }


}