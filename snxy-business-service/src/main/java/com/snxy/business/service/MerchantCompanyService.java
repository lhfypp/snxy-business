package com.snxy.business.service;


import com.snxy.business.domain.MerchantCompany;

import java.util.List;

public interface MerchantCompanyService {
    List<MerchantCompany> selectByPrimaryKey(Long id);

    void createNewCompany(MerchantCompany merchantCompany, Long id);
}
