package com.snxy.business.dao.mapper;

import com.snxy.business.domain.CompanyPrincipalLog;

public interface CompanyPrincipalLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CompanyPrincipalLog record);

    int insertSelective(CompanyPrincipalLog record);

    CompanyPrincipalLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CompanyPrincipalLog record);

    int updateByPrimaryKey(CompanyPrincipalLog record);
}