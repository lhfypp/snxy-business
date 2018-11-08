package com.snxy.business.biz.feign;

import com.snxy.business.service.vo.IdCardInfoVO;
import com.snxy.common.response.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;

@FeignClient(name = "snxy-orc",fallbackFactory = FilePicServiceFallBack.class)
public interface FilePicService {

    //上传识别身份证正面
   @RequestMapping(value = "/orc/idcardFront",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResultData<IdCardInfoVO> idcardFront(@RequestParam("idFrontUrl") MultipartFile idFrontUrl);

   //上传识别身份证反面
   @RequestMapping(value = "/orc/idcardBack",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResultData<IdCardInfoVO> idcardBack(@RequestParam("idBackUrl") MultipartFile idBackUrl);
}
