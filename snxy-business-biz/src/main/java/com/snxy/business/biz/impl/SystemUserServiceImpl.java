package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.SystemUserMapper;
import com.snxy.business.service.SystemUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
@Service
@Slf4j
public class SystemUserServiceImpl implements SystemUserService {
    @Resource
    private SystemUserMapper systemUserMapper;

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

    /**
     * 更换系统用户手机号
     * @param systemUserId
     * @param newMobile
     */
    @Override
    public void updateSystemMobile(Long systemUserId, String newMobile) {
        systemUserMapper.updateSystemMobile(systemUserId, newMobile);
    }
}
