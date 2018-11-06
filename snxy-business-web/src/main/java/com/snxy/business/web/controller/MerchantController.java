package com.snxy.business.web.controller;

import com.snxy.business.service.MerchantService;
import com.snxy.business.service.vo.NewCompanyVO;
import com.snxy.common.response.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/merchant")
public class MerchantController {
    @Resource
    private MerchantService merchantService;

    /*
    * 公司负责人完善公司信息
    * */
    @RequestMapping("/company/new")
    public ResultData newCompany(@RequestBody NewCompanyVO newCompanyVO){
        merchantService.newCompany(newCompanyVO);
        return ResultData.success("");
    }
}
