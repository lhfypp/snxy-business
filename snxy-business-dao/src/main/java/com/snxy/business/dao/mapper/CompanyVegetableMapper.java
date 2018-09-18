package com.snxy.business.dao.mapper;

import com.snxy.business.domain.CompanyVegetable;

public interface CompanyVegetableMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CompanyVegetable record);

    int insertSelective(CompanyVegetable record);

    CompanyVegetable selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CompanyVegetable record);

    int updateByPrimaryKey(CompanyVegetable record);
}