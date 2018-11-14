package com.snxy.business.service;

import com.snxy.business.domain.MerchantCompany;
import com.snxy.business.service.vo.CompanyVO;

import java.util.List;

public interface MerchantCompanyService {
    void insertCompanyMessage(MerchantCompany merchantCompany);

    List<String> selectAllName();

    MerchantCompany selectByCompanyId(Long companyId);

    List<CompanyVO> selectByCompanyName(String companyName);
}
