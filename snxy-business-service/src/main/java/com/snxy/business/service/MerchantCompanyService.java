package com.snxy.business.service;

import com.snxy.business.domain.MerchantCompany;

import java.util.List;

public interface MerchantCompanyService {
    void insertCompanyMessage(MerchantCompany merchantCompany);

    List<String> selectAllName();
}
