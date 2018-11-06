package com.snxy.business.dao.mapper;

import com.snxy.business.domain.SystemUser;

public interface SystemUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SystemUser record);

    int insertSelective(SystemUser record);

    SystemUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SystemUser record);

    int updateByPrimaryKey(SystemUser record);

    void updateUserNameById(Long systemUserId, String userName);
}