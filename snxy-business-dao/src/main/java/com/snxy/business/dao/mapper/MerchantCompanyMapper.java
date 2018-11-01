package com.snxy.business.dao.mapper;

import com.snxy.business.domain.MerchantCompany;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface MerchantCompanyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MerchantCompany record);

    int insertSelective(MerchantCompany record);

    MerchantCompany selectByPrimaryKey(Long companyId);

    int updateByPrimaryKeySelective(MerchantCompany record);

    int updateByPrimaryKey(MerchantCompany record);

}