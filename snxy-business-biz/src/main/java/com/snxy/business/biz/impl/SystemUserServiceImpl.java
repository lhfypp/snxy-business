package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.SystemUserMapper;
import com.snxy.business.domain.SystemUser;
import com.snxy.business.service.SystemUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class SystemUserServiceImpl implements SystemUserService {
    @Resource
    private SystemUserMapper systemUserMapper;

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

    @Transactional(rollbackFor = Exception.class)
    public void updateSystemMobile(Long systemUserId, String newMobile) {
        systemUserMapper.updateSystemMobile(systemUserId, newMobile);
    }
}
