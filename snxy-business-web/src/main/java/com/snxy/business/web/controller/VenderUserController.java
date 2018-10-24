package com.snxy.business.web.controller;

import com.snxy.business.domain.MerchantCompany;
import com.snxy.business.service.MerchantCompanyService;
import com.snxy.business.service.vo.MerchantCompanyVo;
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
    public ResultData basicInfo (Long onlineUserId){
        List<MerchantCompany> merchantCompanyList = merchantCompanyService.selectByPrimaryKey(onlineUserId);

        return ResultData.success(merchantCompanyList);
    }

    /*
    * 创建新公司
    * */
    @RequestMapping(value = "/company/new")
    public ResultData createNewCompany (MerchantCompanyVo merchantCompanyVo, Long onlineUserId){
        merchantCompanyService.createNewCompany(merchantCompanyVo,onlineUserId);
        return ResultData.success("");
    }
}
