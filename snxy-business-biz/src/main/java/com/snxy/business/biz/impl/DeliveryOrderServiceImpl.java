package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.*;
import com.snxy.business.domain.*;
import com.snxy.business.service.DeliveryOrderService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class DeliveryOrderServiceImpl implements DeliveryOrderService {
    @Resource
    private SystemUserInfoMapper systemUserInfoMapper;
    @Resource
    private DeliveryOrderMapper deliveryOrderMapper;

    @Resource
    private VegetableCertificateMapper vegetableCertificateMapper;

    @Resource
    private VegetableImageMapper vegetableImageMapper;

    @Resource
    private VegetableDeliveryRelationMapper vegetableDeliveryRelationMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createDeliveryOrder(DeliveryOrder deliveryOrder, VegetableDeliveryRelation vegetableDeliveryRelation, VegetableCertificate vegetableCertificate, VegetableImage vegetableImage) {
        deliveryOrderMapper.insertSelective(deliveryOrder);
        Long id = deliveryOrder.getId();

        vegetableCertificate.setDeliveryOrderId(id);
        vegetableCertificateMapper.insertSelective(vegetableCertificate);

        vegetableImage.setDeliveryOrderId(id);
        vegetableImageMapper.insertSelective(vegetableImage);

        vegetableDeliveryRelation.setDeliveryOrderId(id);
        vegetableDeliveryRelationMapper.insertSelective(vegetableDeliveryRelation);
    }
    @Override
    public List<BillInfo> searchDeliveryOrder(Long useId, String orderStatus, String serchName) {

        //用于存放商户或者代办所有的手机信息
        List<String>sendPhones=new ArrayList<String>();
        sendPhones= systemUserInfoMapper.searchPhones(useId);
        return  deliveryOrderMapper.searchDeliveryOrder(sendPhones,orderStatus,serchName);
    }
}
