package com.snxy.business.dao.mapper;

public interface DirverInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DirverInfo record);

    int insertSelective(DirverInfo record);

    DirverInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DirverInfo record);

    int updateByPrimaryKey(DirverInfo record);
}