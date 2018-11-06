package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.MerchantCompanyMapper;
import com.snxy.business.domain.MerchantCompany;
import com.snxy.business.service.MerchantCompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Slf4j
public class MerchantCompanyServiceImpl implements MerchantCompanyService {
    @Resource
    private MerchantCompanyMapper merchantCompanyMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertCompanyMessage(MerchantCompany merchantCompany) {
        merchantCompanyMapper.insertSelective(merchantCompany);
    }
}
