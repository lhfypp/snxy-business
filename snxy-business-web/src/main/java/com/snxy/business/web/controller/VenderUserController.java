package com.snxy.business.web.controller;

import com.snxy.business.domain.MerchantCompany;
import com.snxy.business.service.MerchantCompanyService;
import com.snxy.common.response.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/*
*商户信息设置
**/

@RestController
@Slf4j
@RequestMapping("/vender")
public class VenderUserController {

    @Resource
    private MerchantCompanyService merchantCompanyService;

    /*
    * 我的公司
    * */
    @RequestMapping(value = "/company/basicInfo")
    public ResultData basicInfo (Long id){
        //此处参数写死，后期从前台获取onlineId作为参数
        List<MerchantCompany> merchantCompanyList = merchantCompanyService.selectByPrimaryKey(1L);

        return ResultData.success("显示公司接口成功",merchantCompanyList);
    }

    /*
    * 创建新公司
    * */
    @RequestMapping(value = "/company/new")
    public ResultData createNewCompany (MerchantCompany merchantCompany,Long id){
        //此处参数写死，后期从前台获取公司详细信息merchantCompany和onlineId作为参数
        merchantCompanyService.createNewCompany(merchantCompany,id);
        return ResultData.success("创建新公司接口成功");
    }
}
