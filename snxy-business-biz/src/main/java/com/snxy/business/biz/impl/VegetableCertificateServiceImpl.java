package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.VegetableCertificateMapper;
import com.snxy.business.domain.Valication;
import com.snxy.business.domain.VegetableCertificate;
import com.snxy.business.service.VegetableCertificateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class VegetableCertificateServiceImpl implements VegetableCertificateService {
    @Resource
    private VegetableCertificateMapper vegetableCertificateMapper;

    @Override
    public List<Valication> getValications(long orderId) {
        return vegetableCertificateMapper.selectListByOrderId(orderId);
    }

	 @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertImageList(List<VegetableCertificate> vegetableCertificateList) {
        vegetableCertificateMapper.insertImageList(vegetableCertificateList);
    }

    @Override
    public List<VegetableCertificate> selectByOrderId(Long deliveryOrderId) {
        List<VegetableCertificate> vegetableCertificateList = vegetableCertificateMapper.selectByOrderId(deliveryOrderId);
        return vegetableCertificateList;
    }
}
