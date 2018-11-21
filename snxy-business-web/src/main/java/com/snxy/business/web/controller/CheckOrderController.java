package com.snxy.business.web.controller;

import com.snxy.business.domain.QualitySheet;
import com.snxy.business.service.QualitySheetService;
import com.snxy.common.response.ResultData;
import lombok.extern.slf4j.Slf4j;
import com.snxy.business.service.vo.CheckSheetVO;

import com.snxy.business.service.vo.SystemUserVO;
import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import javax.validation.constraints.NotBlank;

@RestController
@Slf4j
@RequestMapping("/user")
@Validated
public class CheckOrderController {
    @Resource
    private QualitySheetService qualitySheetService;
    //生成检测号
    @RequestMapping("/checkOrderNo/generate")
    public ResultData generateCheckOderNo(){
       return ResultData.success( qualitySheetService.generateCheckOderNo());
    }
    //检测单详情
    @RequestMapping("/qualitySheet/info")
    public ResultData qualitySheetByOrderId(Long checkId) {
        QualitySheet qualitySheet = qualitySheetService.qualitySheetByOrderId(checkId);
        return ResultData.success(qualitySheet);
    }

    @RequestMapping("/checkBill/save")
    public ResultData saveCheckBill(CheckSheetVO checkSheetVO,@RequestAttribute(value = "systemUser", required = false) SystemUserVO systemUserVO){
        long userId=1;
        return ResultData.success(qualitySheetService.createQualitySheet(checkSheetVO,userId));
    }
    //新建检测单
    @RequestMapping("/qualitySheet/create")
    public ResultData createQualitySheet(String orderId) {
        return ResultData.success(qualitySheetService.createQualitySheet(orderId));
    }

    //待检测单列表(线上的，or根据菜品名称,or单号查询)
    @RequestMapping("/willBeQualitySheet/list")
    public ResultData searchWllBeQualitySheet(@RequestAttribute(value = "systemUser", required = false) SystemUserVO systemUserVO, String searchName) {
        //查询出所有的待检测单,已交押金
        return ResultData.success(qualitySheetService.searchWillBeQualitySheet(systemUserVO, searchName));
    }

    //全部检测单
    @RequestMapping("/qualitySheet/list")
    public ResultData searchQualitySheet(@RequestAttribute(value = "systemUser", required = false) SystemUserVO systemUserVO, String searchName) {
        //查询出所有的检测证明
        return ResultData.success(qualitySheetService.searchQualitySheet(systemUserVO, searchName));
    }
    //检测报告列表
    @RequestMapping("/qualitySheetReport/list")
    public ResultData seeAboutQualitySheetReportList(@RequestAttribute(value = "systemUser", required = false) SystemUserVO systemUserVO) {
        //查看检测报告列表
        return ResultData.success(qualitySheetService.searchQualitySheetReportList(systemUserVO));
    }
    //查看检测报告(详情)
    @RequestMapping("/qualitySheetReport/view")
    public ResultData seeAboutQualitySheetReportInfo(Long checkId) {
        QualitySheet qualitySheet = qualitySheetService.qualitySheetByOrderId(checkId);
        return ResultData.success(qualitySheet);
    }

    //删除检测单(线下)
    @RequestMapping("qualitySheet/delete")
    public ResultData deleteQualitySheet(@NotBlank(message = "检测号不能为空") String qualitySheetId) {
        qualitySheetService.deleteQualitySheetById(qualitySheetId);
        return ResultData.success("删除检测单成功");
    }


}


