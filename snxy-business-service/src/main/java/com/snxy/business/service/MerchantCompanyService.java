package com.snxy.business.service;


import com.snxy.business.domain.MerchantCompany;
import com.snxy.business.service.vo.MerchantCompanyVo;

import java.util.List;

public interface MerchantCompanyService {
    List<MerchantCompany> selectByPrimaryKey(Long id);

    void createNewCompany(MerchantCompanyVo merchantCompanyVo, Long onlineUserId);
}
