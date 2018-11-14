package com.snxy.business.dao.mapper;

import com.snxy.business.domain.IdentityType;

import java.util.List;

public interface IdentityTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(IdentityType record);

    int insertSelective(IdentityType record);

    IdentityType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IdentityType record);

    int updateByPrimaryKey(IdentityType record);

    List<IdentityType> selectAllType();

    List<IdentityType> listAll(byte b);
}