package com.snxy.business.web.controller;

import com.snxy.business.service.vo.TabletOrderVO;
import com.snxy.common.response.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/tablet")
@Validated
public class IntoDoorAboutTabletController {
    //新建发货单
    @RequestMapping("/deliverOrder/Create")
    public ResultData createOrder(TabletOrderVO TabletOrderVO){

        return null;
    }
    //自动生成总金额
    //扫码后展示发货单
    //设置产地证明无效
    //设置检测证明无效
    //进门发货单(当天)
    //进门发货单（历史）
    //收费（支付 进门扫码上传支付条码进行付款）
    //收费（支付）--进门查询收费结果
    //收费（支付）--进门扫码上传支付条码进行付款
    //收费（支付）--进门查询收费结果
}
