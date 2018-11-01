package com.snxy.business.web.controller;

import com.snxy.business.service.VegetablePriceService;
import com.snxy.business.service.vo.VegetablePriceListVo;
import com.snxy.common.response.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping("/vegetable")
@Validated
public class VegetableController {
    @Resource
    private VegetablePriceService vegetablePriceService;
    @RequestMapping("/price/list")
    public ResultData searchVegetablePriceList(@Valid VegetablePriceListVo vegetablePriceListVo){

        return ResultData.success(vegetablePriceService.selectVegetablePrices(vegetablePriceListVo));
    }
}
