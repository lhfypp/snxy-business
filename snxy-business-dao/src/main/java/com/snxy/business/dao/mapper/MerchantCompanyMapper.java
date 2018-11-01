package com.snxy.business.dao.mapper;

import com.snxy.business.domain.MerchantCompany;

import java.util.List;
import java.util.Map;


public interface MerchantCompanyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MerchantCompany record);

    int insertSelective(MerchantCompany record);

    List<MerchantCompany> selectByPrimaryKey(List list);

    int updateByPrimaryKeySelective(MerchantCompany record);

    int updateByPrimaryKey(MerchantCompany record);

    MerchantCompany selectCompanyInfoById(long id);

}