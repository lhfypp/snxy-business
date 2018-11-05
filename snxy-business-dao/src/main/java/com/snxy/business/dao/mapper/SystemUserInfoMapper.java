package com.snxy.business.dao.mapper;

public interface SystemUserInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SystemUserInfo record);

    int insertSelective(SystemUserInfo record);

    SystemUserInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SystemUserInfo record);

    int updateByPrimaryKey(SystemUserInfo record);
}