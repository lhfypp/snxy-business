package com.snxy.business.service;

import com.snxy.business.service.vo.ValicatePictureVO;

import java.util.List;

public interface VegetableCertificateService {
    void upload(Long deliveryOrderId, List<ValicatePictureVO> certificates);
}
