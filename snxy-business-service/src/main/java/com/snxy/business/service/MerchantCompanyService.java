package com.snxy.business.service;


import com.snxy.business.service.vo.MerchantCompanyVo;
import com.snxy.business.service.vo.ModifyCompanyMessageVo;

import java.util.List;

public interface MerchantCompanyService {
    MerchantCompanyVo selectByPrimaryKey(Long id);

    void createNewCompany(MerchantCompanyVo merchantCompanyVo);

    void modifyCompanyMessage(ModifyCompanyMessageVo modifyCompanyMessageVo);
}
