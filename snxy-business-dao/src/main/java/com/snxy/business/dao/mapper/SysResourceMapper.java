package com.snxy.business.dao.mapper;

import com.snxy.business.domain.SysResource;

public interface SysResourceMapper {
    int deleteByPrimaryKey(Long resourceId);

    int insert(SysResource record);

    int insertSelective(SysResource record);

    SysResource selectByPrimaryKey(Long resourceId);

    int updateByPrimaryKeySelective(SysResource record);

    int updateByPrimaryKey(SysResource record);
}