package com.snxy.business.biz.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    public List<BillInfo> searchDeliveryOrder(String orderStatus, String searchName) {
        //从用户对象获取
        String userPhone="15101267019";
        String identityName="2";
        //用于存放商户或者代办所有的手机信息
        List<String> sendPhones=new ArrayList<String>();
        if("2".equals(identityName)) {
            sendPhones.add(userPhone);
        }else if ("1".equals(identityName)){

            sendPhones = systemUserInfoMapper.searchPhones(userPhone);
        }else{
            return null;
        }
            return deliveryOrderMapper.searchDeliveryOrder(sendPhones, orderStatus, searchName);

    }

    @Override
    public DeliveryOrder searchDeliverOrderinfo(Long deliveryOrderId) {
        return deliveryOrderMapper.selectBydDeliveryOrderId(deliveryOrderId);
    }

    @Override
    public PageInfo<BillInfo> searchDeliveryOrderByPage(String orderStatus, String searchName) {
        //从用户对象获取
        String userPhone="15101267019";
        String identityName="2";
        //用于存放商户或者代办所有的手机信息
        List<String> sendPhones=new ArrayList<String>();
        if("2".equals(identityName)) {
            sendPhones.add(userPhone);
        }else if ("1".equals(identityName)){

            sendPhones = systemUserInfoMapper.searchPhones(userPhone);
        }else{
            return null;
        }
        PageHelper.startPage(1,10);
        List<BillInfo> listBillInfo=deliveryOrderMapper.searchDeliveryOrder(sendPhones, orderStatus, searchName);
        PageInfo<BillInfo> pageInfo = new PageInfo<BillInfo>(listBillInfo);
        return pageInfo;
    }


}
