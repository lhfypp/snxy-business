package com.snxy.business.service;


import com.snxy.business.service.vo.CompanyVO;

import java.util.List;

public interface PerSettingsHomepageService {
    List<CompanyVO> searchCompanyVO(String companyName);
    String saveJoinTheMerchants(long userId, long companyId);
}
