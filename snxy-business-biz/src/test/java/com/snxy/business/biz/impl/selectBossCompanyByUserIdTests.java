package com.snxy.business.biz.impl;

import com.snxy.business.domain.MerchantCompany;
import com.snxy.business.service.CompanyUserRelationService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class selectBossCompanyByUserIdTests {
    @Resource
    private CompanyUserRelationService companyUserRelationService;
    @Test
    public void selectBossCompany(){
        MerchantCompany merchantCompany = companyUserRelationService.selectBossCompanyByUserId(1L);
        System.out.print("=========商户公司名称"+merchantCompany.getMerchantName()+"企业认证照片"+merchantCompany.getCorporateCertificationUrl()+"社会信息码"+merchantCompany.getSocialInfoCode());
    }
}
