package com.snxy.business.dao.mapper;

import com.snxy.business.domain.DriverPicture;
import com.snxy.business.domain.IdInfo;

import com.snxy.business.domain.IdInfo;

public interface IdInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(IdInfo record);

    int insertSelective(IdInfo record);

    IdInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(IdInfo record);

    int updateByPrimaryKey(IdInfo record);

    String selectByidNumber(String identityNO);
}