package com.snxy.business.dao.mapper;


import com.snxy.business.domain.CompanyPartInfo;
import com.snxy.business.domain.MerchantCompany;
import org.apache.ibatis.annotations.Param;




import com.snxy.business.domain.MerchantCompany;

import java.util.List;

public interface MerchantCompanyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MerchantCompany record);

    int insertSelective(MerchantCompany record);

    MerchantCompany selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MerchantCompany record);

    int updateByPrimaryKey(MerchantCompany record);

    List<CompanyPartInfo> selectAllComInfoByComName(@Param("companyName") String companyName);


    List<String> selectAllName();

}