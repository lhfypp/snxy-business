package com.snxy.business.web.controller;

import com.snxy.business.biz.feign.FilePicService;
import com.snxy.business.domain.DriverPicture;
import com.snxy.business.domain.EntranceFeeCapacity;
import com.snxy.business.domain.Vehicle;
import com.snxy.business.service.DirverInfoService;
import com.snxy.business.service.EntranceFeeCapacityService;
import com.snxy.business.service.VehicleService;
import com.snxy.business.service.vo.DriverLicenseVO;
import com.snxy.business.service.vo.IdCardInfoVO;
import com.snxy.business.service.vo.VehicleLicenseVO;
import com.snxy.common.response.ResultData;
import lombok.extern.slf4j.Slf4j;
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
    private FilePicService filePicService;

    //司机信息  身份证  驾驶证保存
    @RequestMapping("/basicInfo/new")
    public ResultData saveDriver(DriverPicture driverPicture) {
        dirverInfoService.saveDriverInfo(driverPicture);
        return ResultData.success("");
    }
    //车辆行驶证保存
    @RequestMapping("/vehicle/new")
    public ResultData saveVehicle(Vehicle vehicle){
        String result = vehicleService.saveVehicle(vehicle);
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
       ResultData<IdCardInfoVO> idCardInfoVO = filePicService.idcardFront(file);
        return ResultData.success(idCardInfoVO);
    }
    //身份证反面上传识别
    @RequestMapping("/file/picture/back")
    public ResultData fileBack(MultipartFile file){
        ResultData<IdCardInfoVO> idCardInfoVO = filePicService.idcardBack(file);
        return ResultData.success(idCardInfoVO);
    }
    //驾驶证上传识别
    @RequestMapping("/file/driving")
    public ResultData drivingFront(MultipartFile file){
        ResultData<DriverLicenseVO> driverLicenseVO = filePicService.drivingFront(file);
        return ResultData.success(driverLicenseVO);
    }
    //行驶证上传识别
    @RequestMapping("/file/vehicle")
    public ResultData vehicleFront(MultipartFile file) {
        ResultData<VehicleLicenseVO> vehicleLicenseVO = filePicService.vehicFront(file);
        return ResultData.success(vehicleLicenseVO);
    }
}