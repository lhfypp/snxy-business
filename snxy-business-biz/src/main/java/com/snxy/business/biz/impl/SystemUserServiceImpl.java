package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.SystemUserMapper;
import com.snxy.business.domain.SystemUser;
import com.snxy.business.domain.UserMobileCode;
import com.snxy.business.service.SystemUserService;
import com.snxy.common.exception.BizException;
import com.snxy.common.util.MD5Util;
import com.snxy.common.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class SystemUserServiceImpl implements SystemUserService {
    @Resource
    private SystemUserMapper systemUserMapper;

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    private final String SMS_CODE = "123456";

    @Override
    public void modifyName(String name, Long userId) {
        if (name==null) {
            throw new BizException("要修改的姓名为空");
        }
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
        String smsCode = this.getSmsCode(userId);

        UserMobileCode userMobileCode = new UserMobileCode();
        userMobileCode.setMobile(newMobile);
        userMobileCode.setSmsCode(smsCode);
        //存储在redis中
        String redisKey = userId.toString();
        redisTemplate.opsForValue().set(redisKey+"smsCode",userMobileCode,5, TimeUnit.MINUTES);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modifyPhone(Long userId, String smsCode) {
        if(smsCode == null){
            throw new BizException("验证码为空");
        }
        UserMobileCode userMobileCode = (UserMobileCode) redisTemplate.opsForValue().get(userId.toString()+"smsCode");
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
    public void identityAuthorize(Long userId, String identityNo, List<MultipartFile> identityImages) {

    }

    @Override
    public void passwordReset(Long userId) {
        String smsCode = this.getSmsCode(userId);
        redisTemplate.opsForValue().set(userId.toString()+"passwordReset",smsCode,5,TimeUnit.MINUTES);
    }

    @Override
    public void checkSmsCode(Long userId, String smsCode) {
        if(smsCode == null){
            throw new BizException("验证码是空的");
        }
        if(!redisTemplate.opsForValue().get(userId.toString()+"passwordReset").toString().equals(smsCode)){
            throw new BizException("输入的验证码错误");
        }
    }

    @Override
    public void passwordResetNew(Long userId, String newPassword) {
        if (newPassword == null){
            throw new BizException("新密码为空");
        }
        SystemUser systemUser = null;
        systemUser.setId(userId);
        systemUser.setPwd(MD5Util.encrypt(newPassword));

        systemUserMapper.updateByPrimaryKeySelective(systemUser);
    }

    @Override
    public void passwordModify(Long userId, String oldPassword, String newPassword) {
        if (oldPassword == null){
            throw new BizException("旧密码为空");
        }
        if (newPassword == null){
            throw new BizException("新密码为空");
        }
    }

    //调用外部接口，获取短信验证码
    public String getSmsCode(Long userId){
        String mobile = systemUserMapper.selectMobileById(userId);
        systemUserMapper.selectMobileById(userId);
        /*
         * 调用接口，获取验证码
         *
         * */
        return SMS_CODE;
    }
}
