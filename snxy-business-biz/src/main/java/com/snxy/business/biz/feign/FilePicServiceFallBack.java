package com.snxy.business.biz.feign;

import com.snxy.business.service.vo.IdCardInfoVO;
import com.snxy.common.response.ResultData;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FilePicServiceFallBack implements FilePicService {

    @Override
    public ResultData<IdCardInfoVO> idcardFront(MultipartFile idFrontUrl) {
        return ResultData.fail("身份证上传失败");
    }

    @Override
    public ResultData<IdCardInfoVO> idcardBack(MultipartFile idBackUrl) {
        return ResultData.fail("身份证上传失败");
    }
}
