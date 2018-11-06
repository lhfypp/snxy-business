package com.snxy.business.biz.impl;

import com.snxy.business.domain.CompanyUserRelation;
import com.snxy.business.domain.MerchantCompany;
import com.snxy.business.service.CompanyUserRelationService;
import com.snxy.business.service.MerchantCompanyService;
import com.snxy.business.service.MerchantService;
import com.snxy.business.service.vo.NewCompanyVO;
import com.snxy.common.exception.BizException;
import com.snxy.common.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service
@Slf4j
public class MerchantServiceImpl implements MerchantService {
    @Resource
    private MerchantCompanyService merchantCompanyService;
    @Resource
    private CompanyUserRelationService companyUserRelationService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void newCompany(NewCompanyVO newCompanyVO) {
        //加上判断是否填写信息===姓名不能为空
        if(StringUtil.isBlank(newCompanyVO.getMerchantName())){
            throw new BizException("公司名称为空");
        }

        MerchantCompany merchantCompany = MerchantCompany.builder()
                .certificationStatus((byte)0)
                .gmtCreate(new Date())
                .build();
        BeanUtils.copyProperties(newCompanyVO,merchantCompany);
        merchantCompanyService.insertCompanyMessage(merchantCompany);

        CompanyUserRelation companyUserRelation = CompanyUserRelation.builder()
                .companyId(merchantCompany.getId())
                .onllineUserId(newCompanyVO.getOnlineUserId())
                .gmtCreate(new Date())
                .isResponsible(0)
                .build();
        companyUserRelationService.insertCompanyUserRelation(companyUserRelation);
    }
}
