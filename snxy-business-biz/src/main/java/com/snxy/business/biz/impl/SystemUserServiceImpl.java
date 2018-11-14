package com.snxy.business.biz.impl;

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
import sun.security.provider.MD5;

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


    /**
     * 更换系统用户姓名
     * @param systemUserId
     * @param name
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateName(Long systemUserId, String name) {
        systemUserMapper.updateNameByPrimaryKey(systemUserId,name,new Date());
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
     * @param systemUserId
     * @param newMobile
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateSystemMobile(Long systemUserId, String newMobile) {
        systemUserMapper.updateSystemMobile(systemUserId, newMobile);
    }

    /**
     * 修改密码前获取验证码
     * @param mobile
     * @return
     */
    @Override
    public String updatePwdGetSmsCode(String mobile) {

        //将来调用短信服务给手机号发送验证码，测试我们先后台生成6位
        String smsCode = RandomStringUtils.randomNumeric(6);
        //将验证码存到redis中，设置有效期为半小时
        redisTemplate.opsForValue().set(mobile+"updatePWD",smsCode,30, TimeUnit.MINUTES);
        //根据当前手机号查出当前的密码
        String DBpwd = systemUserMapper.selectPwdByMobile(mobile);
        //因为数据库中密码是加密的，查出来要解密
        String plainPwd = MD5Util.encrypt(DBpwd);
        System.out.print("======================="+plainPwd+"======="+DBpwd);
        return smsCode;
    }

    /**
     * 修改密码
     * @param mobile
     * @param smsCode
     * @param password
     */
    @Override
    public void updatePwd(String mobile, String smsCode, String password) {
        //从redis中取出验证码
        Object obj = redisTemplate.opsForValue().get(mobile + "updatePWD");
        if (obj==null){
            throw new BizException("验证码已过期,请重新获取");
        }
        //验证码没过期，从redis取出来和用户输入的进行比对
       String redisSmsCode = (String)redisTemplate.opsForValue().get(mobile + "updatePWD");
        if (smsCode.length()==0||smsCode.isEmpty()){
            throw new BizException("验证码为空");
        }else if (!redisSmsCode.equals(smsCode)){
            throw new BizException("验证码输入错误");
        }
        //修改密码,将明文改成密文
        String DBpwd = MD5Util.encrypt(password);
        systemUserMapper.updatePwdByMobile(mobile,DBpwd);
    }
}
