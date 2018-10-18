package com.snxy.business.dao.mapper;

import com.snxy.business.domain.CompanyVegetable;

import java.util.List;

public interface CompanyVegetableMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CompanyVegetable record);

    int insertSelective(CompanyVegetable record);

    CompanyVegetable selectByPrimaryKey(Long id);

    List<CompanyVegetable> selectByCompanyId(Long userCompanyId);

    CompanyVegetable selectByGoodsName(String goodsName);

    int updateByPrimaryKeySelective(CompanyVegetable record);

    int updateByPrimaryKey(CompanyVegetable record);
}