package com.snxy.business.web.controller;

import com.snxy.business.service.DirverInfoService;
import com.snxy.business.service.vo.DriverBasicInfoVo;
import com.snxy.common.response.ResultData;
import lombok.extern.slf4j.Slf4j;

import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;



@RestController
@Slf4j
@RequestMapping("/driver")
@Validated
public class DriverController {
    @Resource
    private DirverInfoService dirverInfoService;
    //司机信息编辑
    //司机信息新建
    @RequestMapping("/basicInfo/new")
    public ResultData saveDriverInfo(@Validated DriverBasicInfoVo driverBasicInfo){
        return ResultData.success(dirverInfoService.saveDriverInfo(driverBasicInfo));
    }
   //司机基础信息查看
    @RequestMapping("/basicInfo/search")
    public ResultData searchDriverInfo(){
        return ResultData.success(dirverInfoService.searchDriverInfo());
    }
    //司机车辆信息列表查询
    @RequestMapping("/car/list")
    public ResultData searchVichleList(){

        return ResultData.success(dirverInfoService.searchVhicles());
    }


}
