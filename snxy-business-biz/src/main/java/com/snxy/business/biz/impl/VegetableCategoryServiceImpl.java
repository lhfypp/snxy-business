package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.DeliveryOrderMapper;
import com.snxy.business.dao.mapper.VegetableCertificateMapper;
import com.snxy.business.domain.VegetableCertificate;
import com.snxy.business.service.VegetableCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Slf4j
public class VegetableCategoryServiceImpl implements VegetableCategoryService {

    @Resource
    private DeliveryOrderMapper deliveryOrderMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void checkProductionCertificate(Long productionCertificate, Integer qualitied,Long orderNo) {

        deliveryOrderMapper.updateLocationCertificate(productionCertificate,qualitied);

        if(qualitied==0){
            deliveryOrderMapper.cancelOrderByOrderId(orderNo,7);
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void checkQualityCertificate(Long qualityCertificateId, Integer qualitied,Long orderNo) {
        deliveryOrderMapper.updateQualityCertificate(qualityCertificateId,qualitied);
        if(qualitied==0){
            deliveryOrderMapper.cancelOrderByOrderId(orderNo,7);
        }
    }
}
