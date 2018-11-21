package com.snxy.business.biz.impl;

import com.snxy.business.biz.feign.FilePicService;
import com.snxy.business.service.DiscernFileSerice;
import com.snxy.business.service.vo.DriverLicenseVO;
import com.snxy.business.service.vo.IdCardInfoVO;
import com.snxy.business.service.vo.VehicleLicenseVO;
import com.snxy.common.exception.BizException;
import com.snxy.common.response.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@Service
@Slf4j
public class DiscernFileSericeImpl implements DiscernFileSerice {

    @Resource
    private FilePicService filePicService;

    //身份证正面识别
    @Override
    public IdCardInfoVO idcardFront(MultipartFile file) {
        ResultData<IdCardInfoVO> resultData =null;
        IdCardInfoVO idCardInfoVO = null;
        try {
            resultData = filePicService.idcardFront(file);
        }catch (Exception e){
            e.printStackTrace();
            throw new BizException("未能识别，请重新上传图片");
        }
        if (resultData.isResult()){
            idCardInfoVO = resultData.getData();
        }else {
            throw new BizException(resultData.getMsg());
        }
        return idCardInfoVO;
    }

    //身份证反面识别
    @Override
    public IdCardInfoVO idcardBack(MultipartFile file) {
        ResultData<IdCardInfoVO> resultData = null;
        try {
             resultData = filePicService.idcardBack(file);
        }catch (Exception e){
            e.printStackTrace();
            throw new BizException("未能识别，请重新上传图片");
        }
        IdCardInfoVO idCardInfoVO = null;
        if (resultData.isResult()){
            idCardInfoVO = resultData.getData();
        }else {
            throw new BizException(resultData.getMsg());
        }
        return idCardInfoVO;
    }

    //驾驶证识别
    @Override
    public DriverLicenseVO drivingFront(MultipartFile file) {
        ResultData<DriverLicenseVO> resultData = null;
        try {
            resultData = filePicService.drivingFront(file);
        }catch (Exception e){
            e.printStackTrace();
            throw new BizException("未能识别，请重新上传图片");
        }
        DriverLicenseVO driverLicenseVO = null;
        if (resultData.isResult()){
            driverLicenseVO = resultData.getData();
        }else {
            throw new BizException(resultData.getMsg());
        }
        return driverLicenseVO;
    }

    //行驶证识别
    @Override
    public VehicleLicenseVO vehicFront(MultipartFile file) {
        ResultData<VehicleLicenseVO> resultData = null;
        try {
            resultData = filePicService.vehicFront(file);
        }catch (Exception e){
            e.printStackTrace();
            throw new BizException("未能识别，请重新上传图片");
        }
        VehicleLicenseVO vehicleLicenseVO = null;
        if (resultData.isResult()){
            vehicleLicenseVO = resultData.getData();
        }else {
            throw new BizException(resultData.getMsg());
        }
        return vehicleLicenseVO;
    }
}
