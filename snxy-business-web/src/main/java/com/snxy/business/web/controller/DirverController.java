package com.snxy.business.web.controller;

import com.snxy.business.biz.feign.FilePicService;
import com.snxy.business.domain.EntranceFeeCapacity;
import com.snxy.business.domain.Vehicle;
import com.snxy.business.service.DirverInfoService;
import com.snxy.business.service.DiscernFileSerice;
import com.snxy.business.service.EntranceFeeCapacityService;
import com.snxy.business.service.VehicleService;
import com.snxy.business.service.vo.*;

import com.snxy.common.exception.BizException;
import com.snxy.common.response.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/dirver")
@Slf4j
class DirverController {
    @Resource
    private DirverInfoService dirverInfoService;
    @Resource
    private VehicleService vehicleService;
    @Resource
    private EntranceFeeCapacityService entranceFeeCapacityService;
    @Resource
    private DiscernFileSerice discernFileSerice;

    //司机信息  身份证  驾驶证保存
    @RequestMapping("/basicInfo/new")
    public ResultData saveDriver(DriverPictureVO driverPictureVO){
        String result = dirverInfoService.saveDriverInfo(driverPictureVO);
        return ResultData.success(result);
    }
    //车辆行驶证保存
    @RequestMapping("/vehicle/new")
    public ResultData saveVehicle(VehicleVO vehicleVO){
        String result = vehicleService.saveVehicle(vehicleVO);
        return ResultData.success(result);
    }
    //车类型列表
    @RequestMapping("/carType/list")
    public ResultData carTypeList(){
        List<EntranceFeeCapacity> entranceFeeCapacityList = entranceFeeCapacityService.carTypeList();
        return ResultData.success(entranceFeeCapacityList);
    }
    //行驶证列表展示
    @RequestMapping("/vehicle/list")
    public ResultData vehicleList(Long dirverInfoId){
        List<Vehicle> vehicleList = vehicleService.vehicleList(dirverInfoId);
        return ResultData.success(vehicleList);
    }
    //身份证正面上传识别
    @RequestMapping("/file/picture/front")
    public ResultData fileFront(MultipartFile file){
        IdCardInfoVO idCardInfoVO = discernFileSerice.idcardFront(file);
        return ResultData.success(idCardInfoVO);
    }
    //身份证反面上传识别
    @RequestMapping("/file/picture/back")
    public ResultData fileBack(MultipartFile file){
       IdCardInfoVO idCardInfoVO = discernFileSerice.idcardBack(file);
        return ResultData.success(idCardInfoVO);
    }

    // 司机获取车辆信息
    @RequestMapping("vehicleInfo/search")
    public ResultData getVhicleInfoForDriver(@RequestAttribute(value = "systemUser",required = false) SystemUserVO systemUserVO){
    long driverId=1;
    return ResultData.success(dirverInfoService.searchVehicleInfo(driverId));
    }

    //驾驶证上传识别
    @RequestMapping("/file/driving")
    public ResultData drivingFront(MultipartFile file){
       DriverLicenseVO driverLicenseVO = discernFileSerice.drivingFront(file);
        return ResultData.success(driverLicenseVO);
    }
    //行驶证上传识别
    @RequestMapping("/file/vehicle")
    public ResultData vehicleFront(MultipartFile file) {
       VehicleLicenseVO vehicleLicenseVO = discernFileSerice.vehicFront(file);
        return ResultData.success(vehicleLicenseVO);
    }
}