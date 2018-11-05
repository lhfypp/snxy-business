package com.snxy.business.dao.mapper;

public interface IdInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(IdInfo record);

    int insertSelective(IdInfo record);

    IdInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(IdInfo record);

    int updateByPrimaryKey(IdInfo record);
}