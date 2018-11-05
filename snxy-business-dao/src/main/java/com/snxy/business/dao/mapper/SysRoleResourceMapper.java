package com.snxy.business.dao.mapper;

import com.snxy.business.domain.SysRoleResource;

public interface SysRoleResourceMapper {
    int insert(SysRoleResource record);

    int insertSelective(SysRoleResource record);
}