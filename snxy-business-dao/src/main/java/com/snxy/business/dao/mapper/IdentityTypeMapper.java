package com.snxy.business.dao.mapper;

public interface IdentityTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(IdentityType record);

    int insertSelective(IdentityType record);

    IdentityType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IdentityType record);

    int updateByPrimaryKey(IdentityType record);
}