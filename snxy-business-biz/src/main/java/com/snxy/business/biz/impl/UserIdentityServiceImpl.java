package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.UserIdentityMapper;
import com.snxy.business.domain.UserIdentity;
import com.snxy.business.service.UserIdentityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class UserIdentityServiceImpl implements UserIdentityService {
    @Resource
    private UserIdentityMapper userIdentityMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertIdentity(UserIdentity userIdentity) {
        userIdentityMapper.insertIdentity(userIdentity);
    }

    @Override
    public void insertIdentityList(List<UserIdentity> userIdentityList) {
        userIdentityMapper.insertIdentityList(userIdentityList);
    }
}
