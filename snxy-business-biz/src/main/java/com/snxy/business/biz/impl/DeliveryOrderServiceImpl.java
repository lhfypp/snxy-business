package com.snxy.business.biz.impl;

import com.snxy.business.biz.feign.FileService;
import com.snxy.business.dao.mapper.*;
import com.snxy.business.domain.*;
import com.snxy.business.service.DeliveryOrderService;
import com.snxy.business.service.vo.*;
import com.snxy.common.exception.BizException;
import com.snxy.common.response.ResultData;
import com.snxy.common.util.PageInfo;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import com.github.pagehelper.PageHelper;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


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

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Resource
    private FileService fileService;

    @Resource
    private EntryFeeMapper entryFeeMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createDeliveryOrder(DeliveryOrderVo deliveryOrderVo) {

        //订单信息DeliveryOrder
        DeliveryOrder deliveryOrder = new DeliveryOrder();
        deliveryOrder.setOrderNo(deliveryOrderVo.getOrderNo());
        deliveryOrder.setSenderName(deliveryOrderVo.getSenderName());
        deliveryOrder.setSenderMobile(deliveryOrderVo.getSenderMobile());
        deliveryOrder.setReceiverName(deliveryOrderVo.getReceiverName());
        deliveryOrder.setReceiverMobile(deliveryOrderVo.getReceiverMobile());
        deliveryOrder.setReceiverCompany(deliveryOrderVo.getReceiverCompany());
        deliveryOrder.setStartAddr(deliveryOrderVo.getStartAddr());
        deliveryOrder.setEndAddr(deliveryOrderVo.getEndAddr());
        deliveryOrder.setSendTime(deliveryOrderVo.getSendTime());
        deliveryOrder.setDeliveryFee(deliveryOrderVo.getDeliveryFee());
        deliveryOrder.setDistance(deliveryOrderVo.getDistance());
        deliveryOrder.setEstArrivalTime(deliveryOrderVo.getEstArrivalTime());
        deliveryOrder.setGmtCreate(new Date());
        deliveryOrder.setQrcodeUrl("二维码地址");
        deliveryOrder.setTruckTypeId(deliveryOrderVo.getTruckTypeId());
        deliveryOrder.setStatus(0);
        deliveryOrder.setLocationCertificate(1);

        deliveryOrderMapper.insertSelective(deliveryOrder);
        Long id = deliveryOrder.getId();
        //货品信息VegetableDeliveryRelation
        List<Goods> goodsList = deliveryOrderVo.getGoodsList();
        List<VegetableDeliveryRelation> vegetableDeliveryRelationList = new ArrayList<>();
        for (int i = 0; i < goodsList.size(); i++) {
            VegetableDeliveryRelation vegetableDeliveryRelation = new VegetableDeliveryRelation();
            vegetableDeliveryRelation.setDeliveryOrderId(id);
            vegetableDeliveryRelation.setGoodsId(goodsList.get(i).getGoodsId());
            vegetableDeliveryRelation.setGoodsName(goodsList.get(i).getGoodsName());
            vegetableDeliveryRelation.setGoodsPrice(goodsList.get(i).getGoodsPrice());
            vegetableDeliveryRelation.setGoodsWeight(goodsList.get(i).getGoodsWeight());
            vegetableDeliveryRelationList.add(vegetableDeliveryRelation);
        }

        vegetableDeliveryRelationMapper.insertGoodList(vegetableDeliveryRelationList);
        //货物照片上传
        List<MultipartFile> goodsImage = deliveryOrderVo.getFile();
        List<VegetableImage> vegetableImageList = new ArrayList<>();
        for (int i = 0; i < goodsImage.size(); i++) {
            ResultData<String> upload = fileService.upload(goodsImage.get(i));
            if (!upload.isResult()) {
                throw new BizException(upload.getMsg());
            }
            String goodsImgUrl = upload.getData();

            VegetableImage vegetableImage = new VegetableImage();
            vegetableImage.setDeliveryOrderId(id);
            vegetableImage.setType(1);
            vegetableImage.setUploadTime(new Date());
            vegetableImage.setUrl(goodsImgUrl);
            vegetableImageList.add(vegetableImage);
        }
        vegetableImageMapper.insertVegetableImageList(vegetableImageList);
        //产地证明上传VegetableCertificate
        List<ValicatePicture> certificates = deliveryOrderVo.getCertificates();
        List<VegetableCertificate> vegetableCertificateList = new ArrayList<>();
        for (int i = 0; i < certificates.size(); i++) {
            //如果是1，就是产地证明
            ResultData<String> upload = fileService.upload(certificates.get(i).getFile());
            if (!upload.isResult()) {
                throw new BizException(upload.getMsg());
            }
            String data = upload.getData();
            VegetableCertificate vegetableCertificate = new VegetableCertificate();
            vegetableCertificate.setDeliveryOrderId(id);
            vegetableCertificate.setUploadTime(new Date());
            vegetableCertificate.setUrl(data);
            if (certificates.get(i).getCertificateType() == 1) {
                vegetableCertificate.setCertificateType(1);
            }
            if (certificates.get(i).getCertificateType() == 2) {
                vegetableCertificate.setCertificateType(2);
            }
            vegetableCertificateList.add(vegetableCertificate);
        }

        vegetableCertificateMapper.insertImageList(vegetableCertificateList);

        //远程调用自动计算进门费用接口，计算出预计的进门费用插入entryfree表
    }

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
    @Transactional(rollbackFor = Exception.class)
    public String getOrderNo() {
        Integer lastOrderNo = (Integer) redisTemplate.opsForValue().get("orderNo");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String format = simpleDateFormat.format(new Date());
        String date = "FH" + format;
        String orderNo;

        if (lastOrderNo == null || lastOrderNo.equals("")) {
            redisTemplate.opsForValue().set("orderNo", 1);
            orderNo = date + String.format("%06d", 1);
        } else {
            redisTemplate.opsForValue().set("orderNo", lastOrderNo + 1);
            orderNo = date + String.format("%06d", lastOrderNo + 1);
        }

        return orderNo;
    }

    @Override
    public List<BillInfo> selectDriverOrder(Long driverMobile) {
        List orderIdList = currOrderReceiverMapper.selectOrderIdByDriverMobile(driverMobile);
        List<BillInfo> billInfoList = deliveryOrderMapper.selectDriverOrderByOderId(orderIdList);

        return billInfoList;
    }

    @Override
    public DriverOrderInfo selectOrderByOrderId(Long orderId) {
        return null;
    }

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
    public BillInfoDetail searchDeliverOrderinfo(Long deliveryOrderId) {
        return null;
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
    public void cancelOrder(String orderId) {
        int status=6;//1.取消，6合格关闭，7不合格关闭
        deliveryOrderMapper.cancelOrderByOrderId(orderId,status);

    }

    @Override
    public void cancelOrder(Long orderId,Integer status) {
        deliveryOrderMapper.cancelOrderByOrderId(orderId, status);
    }

    @Override
    public void changeDriver(String orderId, String driverName, String drivePhone) {

        currOrderReceiverMapper.changeDriver(Long.parseLong(orderId),driverName,drivePhone);
    }

    @Override
    public void updateOrder(UpdateBillInfoDetail billInfoDetail){
        deliveryOrderMapper.updateOrder(billInfoDetail);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateEndLoading(Long deliveryOrderId) {
        deliveryOrderMapper.updateEndLoading(deliveryOrderId);
    }

    @Override
    public void acceptOrder(String orderId){
        int status=2;//接收订单
        deliveryOrderMapper.cancelOrderByOrderId(orderId,status);
    }

    @Override
    public void resufedOrder(String orderId){
        int status=9;//拒绝订单
        deliveryOrderMapper.cancelOrderByOrderId(orderId,status);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void adminModifyOrder(AdminChangeOrderVo adminChangeOrderVo) {

        //修改货物图片上传
        List<VegetableImage> vegetableImageList = new ArrayList<>();
        List<MultipartFile> files = adminChangeOrderVo.getFiles();
        Long deliveryOrderId = adminChangeOrderVo.getDeliveryOrderId();

        for (int i = 0; i < files.size(); i++) {
            ResultData<String> upload = fileService.upload(files.get(i));
            if(!upload.isResult()){
                throw new BizException(upload.getMsg());
            }
            VegetableImage vegetableImage = new VegetableImage();
            vegetableImage.setDeliveryOrderId(deliveryOrderId);
            vegetableImage.setType(2);
            vegetableImage.setUploadTime(new Date());
            vegetableImage.setUrl(upload.getData());
            vegetableImage.setIsDelete((byte)0);
            vegetableImageList.add(vegetableImage);
        }

        vegetableImageMapper.insertVegetableImageList(vegetableImageList);

        //装车情况上传
        deliveryOrderMapper.updateLoadStatus(adminChangeOrderVo.getDeliveryOrderId(),adminChangeOrderVo.getLoadStatus());

        //进门费用修改
        EntryFee entryFee = new EntryFee();
        entryFee.setActualFee(adminChangeOrderVo.getEntryFee());
        entryFee.setRemark(adminChangeOrderVo.getRemark());
        entryFee.setDeliveryOrderId(adminChangeOrderVo.getDeliveryOrderId());
        entryFeeMapper.updateByOrderNo(entryFee);

    }

    @Override
    public OrderVo selectOrderMessageBydeliveryOrderId(Long deliveryOrderId) {
        OrderVo orderVo = new OrderVo();
        DeliveryOrder deliveryOrder = deliveryOrderMapper.selectByPrimaryKey(deliveryOrderId);
        BeanUtils.copyProperties(deliveryOrder, orderVo);

        List<Goods> goodsList = vegetableDeliveryRelationMapper.selectAllByOrderId(deliveryOrderId);
        orderVo.setGoodsList(goodsList);
        CurrOrderReceiver currOrderReceiver = currOrderReceiverMapper.selectDriverMessageByOrderId(deliveryOrderId);
        BeanUtils.copyProperties(currOrderReceiver, orderVo);

        List<VegetableImage> vegetableImageList = vegetableImageMapper.selectByOderId(deliveryOrderId);
        List<String> urlList = new ArrayList<>();
        for (int i = 0; i < vegetableImageList.size(); i++) {
            VegetableImage vegetableImage = vegetableImageList.get(i);
            String url = vegetableImage.getUrl();
            urlList.add(url);
        }
        orderVo.setUrlList(urlList);

        List<VegetableCertificate> vegetableCertificateList = vegetableCertificateMapper.selectByOrderId(deliveryOrderId);

        orderVo.setVegetableCertificateList(vegetableCertificateList);

        return orderVo;
    }

    @Override
    public Map<String ,Object> getVehiclesForDriver(String orderId) {
        //用在线用户id进行查询该用户的所有车辆信息（driverinfo，vehicle表）
        long onlineUserId=1;
        List<VehiclePartInfo> VehiclePartInfoList=vehicleMapper.searchVehiclePartInfo(onlineUserId);
        Map<String ,Object>vehicleInfosMap=new HashMap<>();
        vehicleInfosMap.put("orderId",orderId);
        vehicleInfosMap.put("vehiclePartInfo",VehiclePartInfoList);
        return vehicleInfosMap;
    }

    @Override
    public void insertCurrOrderReceiver(String OrderId,String vehicleId) {
        InsertCurrentOrder insertCurrentOrder=new InsertCurrentOrder();
        insertCurrentOrder.setDeliveryOrderId(Long.parseLong(OrderId));
        insertCurrentOrder.setDriverMobile("15101267019");//从用户信息获取
        insertCurrentOrder.setDriverName("赵四");//从用户信息获取
        insertCurrentOrder.setVehicleId(Long.parseLong(vehicleId));
        currOrderReceiverMapper.insertCurrentOrder(insertCurrentOrder);
    }

    @Override
    public void updateCurrOrderReceiver(String OrderId, String vehicleId) {
        currOrderReceiverMapper.updateCurrOrderReceiver(OrderId,vehicleId);
    }

    @Override
    public void tranferOrder(String orderId, String driveMobile, String driverName) {
        currOrderReceiverMapper .tranferOrder(Long.parseLong(orderId),driveMobile,driverName);
    }
}
