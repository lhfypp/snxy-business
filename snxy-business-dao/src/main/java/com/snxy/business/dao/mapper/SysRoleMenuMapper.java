package com.snxy.business.dao.mapper;

import com.snxy.business.domain.SysRoleMenu;

public interface SysRoleMenuMapper {
    int insert(SysRoleMenu record);

    int insertSelective(SysRoleMenu record);
}