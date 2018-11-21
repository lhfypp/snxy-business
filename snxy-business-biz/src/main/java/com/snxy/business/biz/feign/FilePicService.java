package com.snxy.business.biz.feign;

import com.snxy.business.biz.config.FeignMultipartSupportConfig;
import com.snxy.business.service.vo.BusinessLicenseVO;
import com.snxy.business.service.vo.DriverLicenseVO;
import com.snxy.business.service.vo.IdCardInfoVO;
import com.snxy.business.service.vo.VehicleLicenseVO;
import com.snxy.common.response.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@FeignClient(name = "snxy-ocr",fallbackFactory = FilePicServiceFallBack.class,
        configuration = FeignMultipartSupportConfig.class)
public interface FilePicService {

    //上传识别身份证正面
   @PostMapping(value = "/ocr/idcardFront", consumes = MULTIPART_FORM_DATA_VALUE)
    ResultData<IdCardInfoVO> idcardFront(@RequestPart("file") MultipartFile idFrontUrl);

   //上传识别身份证反面
   @RequestMapping(value = "/ocr/idcardBack")
    ResultData<IdCardInfoVO> idcardBack(@RequestPart("file") MultipartFile idBackUrl);

   //上传识别驾驶证
    @RequestMapping(value = "/ocr/drivingLicenseFront")
    ResultData<DriverLicenseVO> drivingFront(@RequestPart("file") MultipartFile drivingLicenseFrontUrl);

    //上传识别行驶证
    @RequestMapping(value = "/ocr/vehicleLicense")
    ResultData<VehicleLicenseVO> vehicFront(@RequestPart("file") MultipartFile vehicleDrivingLicenseFrontUrl);

    //上传识别公司信用图片
    @RequestMapping(value = "/ocr/businessLicense", consumes = MULTIPART_FORM_DATA_VALUE)
    ResultData<BusinessLicenseVO> businessFront(@RequestPart("file") MultipartFile corporateCertificationUrl);
}
