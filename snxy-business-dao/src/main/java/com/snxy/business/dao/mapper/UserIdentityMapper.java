package com.snxy.business.dao.mapper;

public interface UserIdentityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserIdentity record);

    int insertSelective(UserIdentity record);

    UserIdentity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserIdentity record);

    int updateByPrimaryKey(UserIdentity record);
}