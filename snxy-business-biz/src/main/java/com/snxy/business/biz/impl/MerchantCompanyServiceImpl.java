package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.CompanyUserRelationMapper;
import com.snxy.business.dao.mapper.MerchantCompanyMapper;
import com.snxy.business.domain.CompanyUserRelation;
import com.snxy.business.domain.MerchantCompany;
import com.snxy.business.service.MerchantCompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class MerchantCompanyServiceImpl implements MerchantCompanyService {

    @Resource
    private MerchantCompanyMapper merchantCompanyMapper;
    @Resource
    private CompanyUserRelationMapper companyUserRelationMapper;

    @Override
    public List<MerchantCompany> selectByPrimaryKey(Long id) {
        List list = companyUserRelationMapper.selectByOnlineId(id);

        List<MerchantCompany> merchantCompanys = merchantCompanyMapper.selectByPrimaryKey(list);

        return merchantCompanys;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createNewCompany(MerchantCompany merchantCompany, Long id) {

    }
}
