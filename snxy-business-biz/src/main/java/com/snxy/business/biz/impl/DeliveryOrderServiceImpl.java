package com.snxy.business.biz.impl;

import com.github.pagehelper.PageHelper;
import com.snxy.business.dao.mapper.*;
import com.snxy.business.domain.CompanyUserRelation;
import com.snxy.business.domain.DeliveryOrder;

import com.snxy.business.dao.mapper.DeliveryOrderMapper;
import com.snxy.business.service.DeliveryOrderService;
import com.snxy.common.util.PageInfo;

import com.snxy.business.domain.*;
import com.snxy.business.service.*;
import com.snxy.business.service.vo.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import java.math.BigDecimal;

@Service
@Slf4j
public class DeliveryOrderServiceImpl implements DeliveryOrderService {

    @Resource
    private DeliveryOrderMapper deliveryOrderMapper;
    @Resource
    private CompanyUserRelationMapper companyUserRelationMapper;
    @Resource
    private EntranceFeeDetailMapper entranceFeeDetailMapper;
    @Resource
    private VegetableMapper vegetableMapper;
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private OnlineUserService onlineUserService;
    @Resource
    private CompanyUserRelationService companyUserRelationService;
    @Resource
    private MerchantCompanyService merchantCompanyService;
    @Resource
    private SystemUserService systemUserService;
    @Resource
    private VegetableCertificateService vegetableCertificateService;
    @Resource
    private OrderLogService orderLogService;

    //商户负责人查看在途订单列表
    @Override
    public PageInfo<DeliveryOrder> selectByCreatorId(Long onlineUserId, Long status) {
        //查询公司id
        CompanyUserRelation companyUserRelation = companyUserRelationMapper.selectByOnlineUserId(onlineUserId);
        Long companyId = companyUserRelation.getCompanyId();
        //查询订单列表
        PageHelper.startPage(1,10);
        List<DeliveryOrder> deliveryOrderList = deliveryOrderMapper.selectByCreatorKey(companyId,status);
        PageInfo<DeliveryOrder> deliveryOrderPageInfo = new PageInfo<>();
        deliveryOrderPageInfo.setData(deliveryOrderList);
        return deliveryOrderPageInfo;
    }
    //订单节点信息
    @Override
    public String selectByOrderNo(String orderNo) {
        String statu = "";
        Integer status = deliveryOrderMapper.selectByOrderNo(orderNo);
      switch (status){
          case 0:{
              statu = "等待司机确认";
              break;
          }
          case 1:{
              statu = "等待负责人确认";
              break;
          }
          case 2:{
              statu = "运输中";
              break;
          }
          case 3:{
              statu = "待检测";
              break;
          }
          case 4:{
              statu = "检测中";
              break;
          }
          case 5:{
              statu = "订单完成";
              break;
          }
          case 6:{
              statu = "不合格，货物返回";
              break;
          }
      }
        return statu;
    }

    @Override
    public BillVO createOrderNo(Long onlineUserId) {
        String orderNo = this.getOrderNo();
        OnlineUser onlineUser = onlineUserService.selectByOnlineUserId(onlineUserId);
        CompanyUserRelation companyUserRelation = companyUserRelationService.selectCompanyUserRelation(onlineUserId);
        MerchantCompany merchantCompany = merchantCompanyService.selectByCompanyId(companyUserRelation.getCompanyId());
        BillVO billVO = BillVO.builder()
                .orderNo(orderNo)
                .companyName(merchantCompany.getMerchantName())
                .userName(onlineUser.getUserName())
                .build();

        return billVO;
    }

    @Override
    public void saveDeliveryOrder(SystemUserVO systemUserVO, DeliveryOrderVo deliveryOrderVo) {
        OnlineUser receiverOnlineUser = onlineUserService.selectByPhone(deliveryOrderVo.getReceiverVO().getReceiverMobile());
        OnlineUser driverOnlineUser = onlineUserService.selectByPhone(deliveryOrderVo.getDriverVO().getDriverMobile());

        DeliveryOrder deliveryOrder = DeliveryOrder.builder()
                .creatorId(systemUserVO.getOnlineUserId())
                .gmtCreate(new Date())
                .receiverOnlineUserId(receiverOnlineUser.getId())
                .driverOnlineUserId(driverOnlineUser.getId())
                .status(1)//初始已经接单状态
                .build();

        //产地证明，质检单
        List<ValicatePictureVO> certificates = deliveryOrderVo.getCertificates();
        List<Integer> certificateTypes = certificates.parallelStream().map(ValicatePictureVO::getCertificateType).collect(Collectors.toList());
        //如果有产地证明
        if(certificateTypes.contains(1)){
            deliveryOrder.setLocationCertificate(1);
        }else {
            deliveryOrder.setLocationCertificate(0);
        }
        //如果有质检单
        if(certificateTypes.contains(2)){
            deliveryOrder.setQualityCertificate(1);
        }else {
            deliveryOrder.setQualityCertificate(0);
        }
        //二维码生成url添加



        deliveryOrderMapper.insertSelective(deliveryOrder);

        //产地证明，质检单图片上传保存
        vegetableCertificateService.upload(deliveryOrder.getId(),certificates);

        //货品信息保存
        List<GoodsVO> goodsVOList = deliveryOrderVo.getGoodsVOList();
        //vegetableDeliveryRelationService.insertGoodsVOList(goodsVOList,deliveryOrder.getId());

        //远程调用自动计算进门费用接口，计算出预计的进门费用插入entryfree表


        //操作订单日志表
        OrderLog orderLog = createOrderLog(systemUserVO,deliveryOrder.getId(),0,"用户新建订单");
        orderLogService.insertLog(orderLog);

        //发布订单对司机手机号进行判断是否注册
        SystemUser systemUser = systemUserService.selectByMobile(systemUserVO.getPhone());
        if (systemUser==null){
            //当查询不到司机的手机号注册信息时给司机手机号发送app下载了短信链接
            System.out.println("给司机发送APP下载短信链接");
        }else {
            //如果司机已经注册则app推送订单消息
            System.out.println("通过APP给司机推送消息");
        }
    }

    @Override
    public HomePageOrderVO showNewestOrder(SystemUserVO systemUserVO) {
        // onlineUserId
        Long onlineUserId = systemUserVO.getOnlineUserId();
        //身份集合
        List<IdentityVO> identityTypes = systemUserVO.getIdentityTypes();
        //负责人身份查询出的集合
        List<DeliveryOrder> responsibleDeliverOrders = new ArrayList<>();
        //非负责人查询出的集合
        List<DeliveryOrder> otherRoleDeliveryOrders ;
        // 查询订单

        List<Integer> identityIdList = identityTypes.parallelStream().map(IdentityVO::getId).collect(Collectors.toList());
        if(identityIdList.contains(1)){
            //以负责人身份查询订单
            CompanyUserRelation companyUserRelation = companyUserRelationService.selectCompanyUserRelation(systemUserVO.getOnlineUserId());
            responsibleDeliverOrders = deliveryOrderMapper.selectByCompanyId(companyUserRelation.getCompanyId());
        }
         // 以非负责人身份查询
        otherRoleDeliveryOrders = deliveryOrderMapper.selectAllByIdAndPhone(systemUserVO.getOnlineUserId(),systemUserVO.getPhone());

        DeliveryOrder returnDeliveryOrder = null;
        HomePageOrderVO homePageOrderVO = null ;

         // 判断是否有司机待处理的订单
             List<DeliveryOrder> driverUrgentDeliveryOrders = new ArrayList<>();

        for(int i =0;i< otherRoleDeliveryOrders.size() -1;i++){
            DeliveryOrder deliveryOrder = otherRoleDeliveryOrders.get(i);
            if(deliveryOrder.getDriverOnlineUserId() != null && deliveryOrder.getDriverOnlineUserId().equals(onlineUserId)){
                // 额外的状态判断，等待司机确认发车
                   if(deliveryOrder.getStatus() == 0){
                       driverUrgentDeliveryOrders.add(deliveryOrder);
                   }
            }
        }

        if(driverUrgentDeliveryOrders.size() > 0){
            if (identityIdList.contains(3)) {
                returnDeliveryOrder = this.getUrgentDeliverOrder(driverUrgentDeliveryOrders);
                homePageOrderVO = HomePageOrderVO.builder()
                        .deliveryOrder(returnDeliveryOrder)
                        .code(1)
                        .build();
                return homePageOrderVO;
            }
        }

        // 判断是否有负责人处理的订单

        List<DeliveryOrder> responsibleUrgentDeliveryOrders = new ArrayList<>();

        for(int i =0;i< responsibleDeliverOrders.size() -1;i++){
            DeliveryOrder deliveryOrder = responsibleDeliverOrders.get(i);
            if(deliveryOrder.getStatus() == 1){
                // 额外的状态判断，等待负责人确认
                responsibleUrgentDeliveryOrders.add(deliveryOrder);
            }
        }

        if(responsibleUrgentDeliveryOrders.size() > 0){
            if (identityIdList.contains(1)) {
                returnDeliveryOrder = this.getUrgentDeliverOrder(responsibleUrgentDeliveryOrders);
                homePageOrderVO = HomePageOrderVO.builder()
                        .deliveryOrder(returnDeliveryOrder)
                        .code(2)
                        .build();
                return homePageOrderVO;
            }
        }

        // 没有需要司机及负责人确认的订单
        responsibleDeliverOrders.addAll(otherRoleDeliveryOrders);
        if(responsibleDeliverOrders.isEmpty()){
            //没有未处理订单 怎么处理

        }else{
            returnDeliveryOrder = this.getUrgentDeliverOrder(responsibleUrgentDeliveryOrders);
            homePageOrderVO = HomePageOrderVO.builder()
                    .deliveryOrder(returnDeliveryOrder)
                    .build();
        }

        return homePageOrderVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void confirmationOrder(SystemUserVO systemUserVO,DriverConfirmationVO driverConfirmationVO) {
        DeliveryOrder deliveryOrder = new DeliveryOrder();
        BeanUtils.copyProperties(driverConfirmationVO,deliveryOrder);
        deliveryOrder.setGmtModified(new Date());
        deliveryOrderMapper.updateByPrimaryKeySelective(deliveryOrder);

        //身份判断
        String message = "";
        if(driverConfirmationVO.getStatus()==0){
            message = "司机确认发货";
            //调用消息推送接口，给订单的负责人推送消息


        }else if(driverConfirmationVO.getStatus()==1){
            message = "商户确认订单";
        }

        OrderLog orderLog = createOrderLog(systemUserVO,driverConfirmationVO.getId(),driverConfirmationVO.getStatus(),message);
        orderLogService.insertLog(orderLog);
    }

    @Override
    public DeliveryOrder showOrderDetails(Long deliveryOrderId) {
        DeliveryOrder deliveryOrder = deliveryOrderMapper.selectByPrimaryKey(deliveryOrderId);
        return deliveryOrder;
    }


    public DeliveryOrder getUrgentDeliverOrder(List<DeliveryOrder> deliveryOrders){
        if(deliveryOrders == null || deliveryOrders.isEmpty()){
            return null;
        }

        deliveryOrders.sort((o1, o2) -> {
            if(o1.getGmtModified() != null && o2.getGmtModified() != null){
                if(o1.getGmtModified().getTime() > o1.getGmtModified().getTime()){
                     return 1;
                }else{
                    return -1;
                }
            }else if(o1.getGmtModified() == null && o2.getGmtModified() != null){
                if(o1.getGmtCreate().getTime() > o2.getGmtModified().getTime()){
                    return 1;
                }else{
                    return -1;
                }
            }else if(o1.getGmtModified() != null && o2.getGmtModified() ==null ){
                if(o1.getGmtModified().getTime() > o2.getGmtCreate().getTime()){
                    return 1;
                }else{
                    return -1;
                }
            }else{
                if(o1.getGmtCreate().getTime() > o2.getGmtCreate().getTime()){
                    return 1;
                }else{
                    return -1;
                }
            }
        });


        return deliveryOrders.get(0);
    }

    //创建订单号
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

    //操作日志对象创建
    public OrderLog createOrderLog(SystemUserVO systemUserVO,Long deliveryOrderId,Integer status,String message){
        OrderLog orderLog = OrderLog.builder()
                .deliveryOrderId(deliveryOrderId)
                .gmtCreate(new Date())
                .operatorId(systemUserVO.getOnlineUserId())
                .operatorName(systemUserVO.getName())
                .status(status)
                .operationDesc(message)
                .build();
        return orderLog;
    }
    @Override
    public DeliveryOrder searchDeliveryOrderById(Long id) {
        return deliveryOrderMapper.selectByPrimaryKey(id);

}

    //计算收费
    @Override
    public String chargeCount(ChargeCountVO chargeCountVO) {
        //查询菜品大类
        Long entranceFeeCategoryId = vegetableMapper.selectByVegetableId(chargeCountVO.getEntranceFeeCategoryId());
        //价格
        String cost  ="";
        BigDecimal num = new BigDecimal("0.5");
        BigDecimal num2 = new BigDecimal("4");
        BigDecimal num3 = new BigDecimal("0.75");
        BigDecimal price = entranceFeeDetailMapper.selectPriceById(chargeCountVO.getEntranceFeeCapacityId(),entranceFeeCategoryId);
        if (price.equals(null)){
            return "价格不存在";
        }else {
            if (1 == chargeCountVO.getLoadStatus()){
                cost = "当前收费为："+price;
            }else if (2 == chargeCountVO.getLoadStatus()){
                cost = "当前收费为："+price.multiply(num);
            }else if (3 == chargeCountVO.getLoadStatus()){
                cost = "当前收费为："+price.divide(num2,2,BigDecimal.ROUND_HALF_UP);
            }else if (4 == chargeCountVO.getLoadStatus()){
                cost = "当前收费为："+price.multiply(num3);
            }
            return cost;
        }
    }
}