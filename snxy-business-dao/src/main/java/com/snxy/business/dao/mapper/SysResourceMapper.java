package com.snxy.business.dao.mapper;

public interface SysResourceMapper {
    int deleteByPrimaryKey(Long resourceId);

    int insert(SysResource record);

    int insertSelective(SysResource record);

    SysResource selectByPrimaryKey(Long resourceId);

    int updateByPrimaryKeySelective(SysResource record);

    int updateByPrimaryKey(SysResource record);
}