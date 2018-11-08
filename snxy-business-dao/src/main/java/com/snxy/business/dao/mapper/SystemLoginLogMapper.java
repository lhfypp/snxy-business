package com.snxy.business.dao.mapper;

import com.snxy.business.domain.SystemLoginLog;

public interface SystemLoginLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SystemLoginLog record);

    int insertSelective(SystemLoginLog record);

    SystemLoginLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SystemLoginLog record);

    int updateByPrimaryKey(SystemLoginLog record);
}