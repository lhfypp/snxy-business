package com.snxy.business.dao.mapper;

public interface MerchantCompanyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MerchantCompany record);

    int insertSelective(MerchantCompany record);

    MerchantCompany selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MerchantCompany record);

    int updateByPrimaryKey(MerchantCompany record);
}