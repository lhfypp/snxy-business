package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.OnlineUserMapper;

import com.snxy.business.service.OnlineUserService;
import com.snxy.common.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.snxy.business.domain.OnlineUser;

import com.snxy.business.service.SystemUserService;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.redis.core.RedisTemplate;

import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.concurrent.TimeUnit;


@Service
@Slf4j
public class OnlineUserServiceImpl implements OnlineUserService {
    @Resource
    private OnlineUserMapper onlineUserMapper;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private SystemUserService systemUserService;

    @Transactional(rollbackFor = Exception.class)
    public void updateName(Long systemUserId, String name) {
        onlineUserMapper.updateNameBySystemUserId(systemUserId, name);
    }

    @Override
    public OnlineUser selectByPhone(String phone) {
        OnlineUser onlineUser = onlineUserMapper.selectByPhone(phone);
        return onlineUser;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertOnlineUser(OnlineUser onlineUser) {
        onlineUserMapper.insertOnlineUser(onlineUser);
    }

    @Override
    public List<OnlineUser> selectByPhoneList(List<String> phoneList) {
        List<OnlineUser> onlineUserList = onlineUserMapper.selectByPhoneList(phoneList);
        return onlineUserList;
    }

    @Override
    public void insertOnlineUserList(List<OnlineUser> onlineUserList) {
        onlineUserMapper.insertOnlineUserList(onlineUserList);
    }

    @Override
    public List<OnlineUser> selectByOnlineUserIdList(List<Long> onlineUserIdList) {
        List<OnlineUser> onlineUserList = onlineUserMapper.selectByOnlineUserIdList(onlineUserIdList);
        return onlineUserList;
    }

    @Override
    public String getSmsCode(String oldMobile) {

//        随机生成6位数验证码
        String smsCode = RandomStringUtils.randomNumeric(6);
//        将验证码保存到redis中，并设置有效期
        redisTemplate.opsForValue().set(oldMobile + "updateMobile", smsCode, 60, TimeUnit.MINUTES);
//        这里将来会调用短信服务给用户发送验证码 TODO

        return smsCode;
    }

    @Override
    public void updateOnlineMobile(Long systemUserId, String oldMobile, String newMobile, String smsCode) {

//        判断传过来的验证码是否过期
        Object code = redisTemplate.opsForValue().get(oldMobile + "updateMobile");
        log.info("========================" + code);
        if (code == null) {
            throw new BizException("验证码已过期，重新获取");
        }
//       如果没过期，从redis中取出验证码
        String smsCode1 = (String) redisTemplate.opsForValue().get(oldMobile + "updateMobile");
        System.out.print("===========================" + smsCode1);
//        首先判断用户传入的验证码和系统的验证码是否一致
        if (smsCode.length() == 0 || smsCode.isEmpty()) {
            throw new BizException("验证码为空");
        } else if (!smsCode1.equals(smsCode)) {
            throw new BizException("验证码输入错误");
        }
//        如果验证码输入正确的话，可以修改手机号，用systemId获取onlineId
        Long onlineUserId = onlineUserMapper.selectOnlineUserIdBySystemUserId(systemUserId);
//        修改在线用户的手机号
        onlineUserMapper.updateOnlineMobile(onlineUserId, newMobile);
//        修改系统用户的手机号
        systemUserService.updateSystemMobile(systemUserId, newMobile);

    }

    @Override
    //获取 onlineUserId
    public long searchOnlineUserId(String SystemUserId) {
        Long onlineUserId = onlineUserMapper.selectIdBySystemUserID(SystemUserId);
        if (onlineUserId == null) {
            throw new BizException("没有该用户信息");
        }
        return onlineUserId;
    }

    @Override
    public Long selectOnlineUserIdBySystemUserId(Long systemUserId) {
        return onlineUserMapper.selectOnlineUserIdBySystemUserId(systemUserId);
    }

    @Override
    public OnlineUser selectByOnlineUserId(Long onlineUserId) {
        OnlineUser onlineUser = onlineUserMapper.selectByPrimaryKey(onlineUserId);
        return onlineUser;
    }
}

