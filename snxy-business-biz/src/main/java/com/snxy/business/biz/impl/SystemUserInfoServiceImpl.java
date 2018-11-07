package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.SystemUserInfoMapper;
import com.snxy.business.dao.mapper.SystemUserMapper;
import com.snxy.business.service.SystemUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class SystemUserInfoServiceImpl implements SystemUserInfoService {
    @Resource
    private SystemUserInfoMapper systemUserInfoMapper;

    /**
     * 获取客服电话
     * @param systemUserId
     * @return
     */
    @Override
    public String selectMobileBySystemUserId(Long systemUserId) {

        return systemUserInfoMapper.selectMobileBySystemUserId(systemUserId);
    }
}
