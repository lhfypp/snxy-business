package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.*;
import com.snxy.business.domain.*;
import com.snxy.business.service.DeliveryOrderService;
import com.snxy.common.util.PageInfo;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.HashMap;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
    public class DeliveryOrderServiceImpl implements DeliveryOrderService {
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


    @Resource
    private CurrOrderReceiverMapper currOrderReceiverMapper;

    @Resource
    private RedisTemplate<String,Object> redisTemplate;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createDeliveryOrder(DeliveryOrderVo deliveryOrderVo) {

        //订单信息DeliveryOrder
        DeliveryOrder deliveryOrder = new DeliveryOrder();
        deliveryOrder.setOrderNo(deliveryOrderVo.getOrderNo());
        deliveryOrder.setSenderName(deliveryOrderVo.getSellerName());
        deliveryOrder.setSenderMobile(deliveryOrderVo.getSellerMobile());
        deliveryOrder.setReceiverName(deliveryOrderVo.getBuyerName());
        deliveryOrder.setReceiverMobile(deliveryOrderVo.getBuyerMobile());
        deliveryOrder.setReceiverCompany(deliveryOrderVo.getBuyerCompany());
        deliveryOrder.setStartAddr(deliveryOrderVo.getSendAddr());
        deliveryOrder.setEndAddr(deliveryOrderVo.getBuyAddr());
        deliveryOrder.setSendTime(deliveryOrderVo.getSendTime());
        deliveryOrder.setDeliveryFee(deliveryOrderVo.getPrice());
        deliveryOrder.setDistance(deliveryOrderVo.getDistance());
        deliveryOrder.setEstArrivalTime(deliveryOrderVo.getEstArrivalTime());
        deliveryOrder.setGmtCreate(new Date());
        deliveryOrder.setQrcodeUrl("二维码地址");
        deliveryOrder.setTruckTypeId(deliveryOrderVo.getTruckTypeId());
        deliveryOrder.setStatus(0);

        deliveryOrderMapper.insertSelective(deliveryOrder);
        Long id = deliveryOrder.getId();
        //货品信息VegetableDeliveryRelation
        VegetableDeliveryRelation vegetableDeliveryRelation = new VegetableDeliveryRelation();
        vegetableDeliveryRelation.setDeliveryOrderId(id);
        vegetableDeliveryRelation.setGoodsId(deliveryOrderVo.getGoodsId());
        vegetableDeliveryRelation.setGoodsName(deliveryOrderVo.getGoodsName());
        vegetableDeliveryRelation.setGoodsPrice(deliveryOrderVo.getGoodsPrice());
        vegetableDeliveryRelation.setGoodsWeight(deliveryOrderVo.getGoodsWeight());

        vegetableDeliveryRelationMapper.insertSelective(vegetableDeliveryRelation);
        //产地证明VegetableCertificate,必须传，不传无法提交
        VegetableCertificate vegetableCertificate = new VegetableCertificate();
        vegetableCertificate.setDeliveryOrderId(id);
        vegetableCertificate.setCertificateType(1);
        vegetableCertificate.setUploadTime(deliveryOrderVo.getLocationCertificateUploadTime());
        vegetableCertificate.setUrl("产地证明图片地址");

        vegetableCertificateMapper.insertSelective(vegetableCertificate);
        //质检单VegetableCertificate
        VegetableCertificate vegetableCertificate2 = new VegetableCertificate();
        vegetableCertificate2.setDeliveryOrderId(id);
        vegetableCertificate2.setCertificateType(2);
        vegetableCertificate2.setUploadTime(deliveryOrderVo.getQualityCertificateUploadTime());
        vegetableCertificate2.setUrl("质检单图片地址");

        vegetableCertificateMapper.insertSelective(vegetableCertificate2);
        //货品照片VegetableImage
        VegetableImage vegetableImage = new VegetableImage();
        vegetableImage.setDeliveryOrderId(id);
        vegetableImage.setType(1);
        vegetableImage.setUploadTime(deliveryOrderVo.getGoodsImgUploadTime());
        vegetableImage.setUrl("货品图片地址");

        vegetableImageMapper.insertSelective(vegetableImage);


    }

    @Override
    public String getOrderNo() {
        Integer lastOrderNo = (Integer) redisTemplate.opsForValue().get("orderNo");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String format = simpleDateFormat.format(new Date());
        String date= "FH"+format;
        String orderNo;

        if(lastOrderNo==null||lastOrderNo.equals("")){
            redisTemplate.opsForValue().set("orderNo",1);
            orderNo = date+String.format("%06d",1);
        }else {
            redisTemplate.opsForValue().set("orderNo",lastOrderNo+1);
            orderNo = date+String.format("%06d",lastOrderNo+1);
        }

        return orderNo;
    }

    @Override

    public BillInfoDetail searchDeliverOrderinfo(String  deliveryOrderId) {

    public List<BillInfo> selectDriverOrder(Long userId) {
        List list = currOrderReceiverMapper.selectOrderIdByPrimaryKey(userId);

        List<BillInfo> driverOrders = deliveryOrderMapper.selectDriverOrder(list);
        return driverOrders;
    }
    public BillInfoDetail searchDeliverOrderinfo(Long deliveryOrderId) {

        return deliveryOrderMapper.selectBydDeliveryOrderId(deliveryOrderId);

    }

    @Override
    public PageInfo<BillInfo> searchDeliveryOrderByPage(String orderStatus, String searchName) {
        return null;
    }

    @Override
    public DriverOrderInfo selectOrderByOrderId(Long orderId) {
        List<GoodsInfo> goodsInfos = vegetableDeliveryRelationMapper.selectByOrderId(orderId);
        DriverOrderInfo driverOrderInfo = deliveryOrderMapper.selectDriverOrderBydDeliveryOrderId(orderId);
        driverOrderInfo.setGoodsInfos(goodsInfos);
        return driverOrderInfo;
    }

    @Override
    public void createDeliveryOrder(DeliveryOrder deliveryOrder, VegetableDeliveryRelation vegetableDeliveryRelation, VegetableCertificate vegetableCertificate, VegetableImage vegetableImage) {

    }

    @Override
    public List<BillInfo> searchDeliveryOrder(String orderStatus, String searchName) {
        return null;
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
