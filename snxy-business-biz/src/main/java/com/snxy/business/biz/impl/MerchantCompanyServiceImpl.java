package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.MerchantCompanyMapper;
import com.snxy.business.domain.MerchantCompany;
import com.snxy.business.service.CompanyUserRelationService;
import com.snxy.business.service.MerchantCompanyService;
import com.snxy.business.service.vo.MerchantCompanyVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class MerchantCompanyServiceImpl implements MerchantCompanyService {

    @Resource
    private MerchantCompanyMapper merchantCompanyMapper;
    @Resource
    private CompanyUserRelationService companyUserRelationService;

    @Override
    public List<MerchantCompany> selectByPrimaryKey(Long id) {
        List list = companyUserRelationService.selectByOnlineId(id);

        List<MerchantCompany> merchantCompanys = merchantCompanyMapper.selectByPrimaryKey(list);

        return merchantCompanys;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createNewCompany(MerchantCompanyVo merchantCompanyVo, Long onlineUserId) {

    }
}
