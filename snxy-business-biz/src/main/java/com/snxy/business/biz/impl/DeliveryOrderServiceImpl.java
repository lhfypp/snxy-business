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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
    public class DeliveryOrderServiceImpl implements DeliveryOrderService {
    @Resource
    private SystemUserInfoMapper systemUserInfoMapper;
    @Resource
    private DeliveryOrderMapper deliveryOrderMapper;
    @Resource
    private VehicleMapper vehicleMapper;
    @Resource
    private VegetableCertificateMapper vegetableCertificateMapper;

    @Resource
    private VegetableImageMapper vegetableImageMapper;

    @Resource
    private VegetableDeliveryRelationMapper vegetableDeliveryRelationMapper;
    @Resource
    private CurrOrderReceiverMapper currOrderReceiverMapper;
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
    public BillInfoDetail searchDeliverOrderinfo(String  deliveryOrderId) {
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

    @Override
    public void cancelOrder(String orderNO) {
        int status=6;//1.取消，6合格关闭，7不合格关闭
        deliveryOrderMapper.cancelOrderByOrderId(orderNO,status);

    }

    @Override
    public void changeDriver(String orderId, String driverName, String drivePhone) {

        currOrderReceiverMapper.changeDriver(Long.parseLong(orderId),driverName,drivePhone);
    }
    public void updateOrder(UpdateBillInfoDetail billInfoDetail){
        deliveryOrderMapper.updateOrder(billInfoDetail);
    }
   public void acceptOrder(String orderNo){
        int status=2;//接收订单
       deliveryOrderMapper.cancelOrderByOrderId(orderNo,status);
   }

    @Override
    public Map<String ,Object> getVehiclesForDriver(String orderNO) {
        //用在线用户id进行查询该用户的所有车辆信息（driverinfo，vehicle表）
        long onlineUserId=1;
        List<VehiclePartInfo> VehiclePartInfoList=vehicleMapper.searchVehiclePartInfo( onlineUserId);
        Map<String ,Object>vehicleInfosMap=new HashMap<>();
        vehicleInfosMap.put("orderNO",orderNO);
        vehicleInfosMap.put("vehiclePartInfo",VehiclePartInfoList);
        return vehicleInfosMap;
    }
}
