package com.snxy.business.dao.mapper;

import com.snxy.business.domain.SystemUserInfo;

public interface SystemUserInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SystemUserInfo record);

    int insertSelective(SystemUserInfo record);

    SystemUserInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SystemUserInfo record);

    int updateByPrimaryKey(SystemUserInfo record);

    String selectMobileBySystemUserId(Long systemUserId);
}