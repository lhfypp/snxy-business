package com.snxy.business.biz.impl;

import com.snxy.business.domain.MerchantCompany;
import com.snxy.business.service.DirverInfoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SelectDriverOfCompanyByIdTests {
    @Resource
    private DirverInfoService dirverInfoService;
    @Test
    public void selectDirverCompany(){
        List<MerchantCompany> merchantCompanyList = dirverInfoService.selectDriverOfCompanyById(1L);
        for (int i=0;i<merchantCompanyList.size();i++){
            System.out.print("=========商户公司名称"+merchantCompanyList.get(i).getMerchantName()+merchantCompanyList.get(i).getSocialInfoCode()+merchantCompanyList.get(i).getCorporateCertificationUrl());
        }
    }
}
