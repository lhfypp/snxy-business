package com.snxy.business.biz.impl;

import com.snxy.business.biz.feign.FileService;
import com.snxy.business.dao.mapper.VegetableCertificateMapper;
import com.snxy.business.domain.VegetableCertificate;
import com.snxy.business.service.VegetableCertificateService;
import com.snxy.business.service.vo.ValicatePictureVO;
import com.snxy.common.exception.BizException;
import com.snxy.common.response.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class VegetableCertificateServiceImpl implements VegetableCertificateService {
    @Resource
    private VegetableCertificateMapper vegetableCertificateMapper;
    @Resource
    private FileService fileService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void upload(Long deliveryOrderId, List<ValicatePictureVO> certificates) {
        List<VegetableCertificate> vegetableCertificateList = new ArrayList<>();
        for (int i = 0; i < certificates.size(); i++) {
            Integer certificateType = certificates.get(i).getCertificateType();
            VegetableCertificate vegetableCertificate = uploadImg(certificates.get(i).getFile(),deliveryOrderId,certificateType);
            //如果是质检单
            if(certificateType == 2){
                vegetableCertificate.setXfdCertificate(0);
            }
            vegetableCertificateList.add(vegetableCertificate);
        }
        vegetableCertificateMapper.insertCertificateList(vegetableCertificateList);
    }

    @Override
    public List<VegetableCertificate> selectByDeliveryOrderId(Long deliveryOrderId) {
        List<VegetableCertificate> vegetableCertificateList = vegetableCertificateMapper.selectByDeliveryOrderId(deliveryOrderId);
        return vegetableCertificateList;
    }

    public VegetableCertificate uploadImg(MultipartFile file,Long deliveryOrderId,Integer certificateType){
        ResultData<String> upload = fileService.upload(file);
        if (!upload.isResult()) {
            throw new BizException(upload.getMsg());
        }
        String url = upload.getData();
        VegetableCertificate vegetableCertificate = VegetableCertificate.builder()
                .gmtCreate(new Date())
                .status(1)
                .uploadTime(new Date())
                .url(url)
                .certificateType(certificateType)
                .deliveryOrderId(deliveryOrderId)
                .build();
        return vegetableCertificate;
    }
}
