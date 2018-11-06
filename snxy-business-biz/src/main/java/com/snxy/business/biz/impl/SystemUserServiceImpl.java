package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.SystemUserMapper;
import com.snxy.business.service.SystemUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class SystemUserServiceImpl implements SystemUserService {

    @Resource
    private SystemUserMapper systemUserMapper;

    @Override
    public void updateUserNameById(Long systemUserId, String userName) {
        systemUserMapper.updateUserNameById(systemUserId,userName);
    }
}
