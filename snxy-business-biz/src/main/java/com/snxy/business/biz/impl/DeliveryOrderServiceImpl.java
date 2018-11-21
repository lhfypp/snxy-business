package com.snxy.business.biz.impl;

import com.snxy.business.biz.config.IdentityTypeEnum;
import com.github.pagehelper.PageHelper;
import com.snxy.business.biz.feign.SmsService;
import com.snxy.business.dao.mapper.CompanyUserRelationMapper;
import com.snxy.business.dao.mapper.DeliveryOrderMapper;
import com.snxy.business.dao.mapper.EntranceFeeDetailMapper;
import com.snxy.business.dao.mapper.*;
import com.snxy.business.domain.CompanyUserRelation;
import com.snxy.business.domain.DeliveryOrder;

import com.snxy.business.service.DeliveryOrderService;
import com.snxy.common.util.PageInfo;
import com.snxy.business.domain.*;
import com.snxy.business.service.*;
import com.snxy.business.service.vo.*;
import com.snxy.common.exception.BizException;
import com.snxy.common.util.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
    private VegetableDeliveryRelationService vegetableDeliveryRelationService;
    @Resource
    private OrderLogService orderLogService;
    @Resource
    private UserIdentityService userIdentityService;
    @Resource
    private VehicleService vehicleService;
    @Resource
    private EntryFeeService entryFeeService;
    @Resource
    private GuaranteeDepositService guaranteeDepositService;
    @Resource
    private SmsService smsService;
    @Resource
    private MerchantService merchantService;

    //商户负责人查看在途订单列表
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


//    @Override
//    public BillVO createOrderNo(Long onlineUserId) {
//        String orderNo = this.getOrderNo();
//        OnlineUser onlineUser = onlineUserService.selectByOnlineUserId(onlineUserId);
//        CompanyUserRelation companyUserRelation = companyUserRelationService.selectCompanyUserRelation(onlineUserId);
//        MerchantCompany merchantCompany = merchantCompanyService.selectByCompanyId(companyUserRelation.getCompanyId());
//        BillVO billVO = BillVO.builder()
//                .orderNo(orderNo)
//                .companyName(merchantCompany.getMerchantName())
//                .userName(onlineUser.getUserName())
//                .build();
//
//        return billVO;
//    }

    @Override
    public void saveDeliveryOrder(SystemUserVO systemUserVO, DeliveryOrderVo deliveryOrderVo) {
        //消息推送人电话集合
        List<String> phoneList = new ArrayList<>();

        String orderNo = this.getOrderNo();
        String driverName = deliveryOrderVo.getDriverVO().getDriverName();
        String driverMobile = deliveryOrderVo.getDriverVO().getDriverMobile();
        String senderName = deliveryOrderVo.getSenderVO().getSenderName();
        String senderMobile = deliveryOrderVo.getSenderVO().getSenderMobile();
        OnlineUser receiverOnlineUser = onlineUserService.selectByPhone(deliveryOrderVo.getReceiverVO().getReceiverMobile());
        OnlineUser driverOnlineUser = onlineUserService.selectByPhone(deliveryOrderVo.getDriverVO().getDriverMobile());
        OnlineUser senderOnlineUser = onlineUserService.selectByPhone(deliveryOrderVo.getSenderVO().getSenderMobile());

        DeliveryOrder deliveryOrder = DeliveryOrder.builder()
                .creatorId(systemUserVO.getOnlineUserId())
                .gmtCreate(new Date())
                .status(0)//初始已经接单状态
                .creator(systemUserVO.getName())
                .senderName(deliveryOrderVo.getSenderVO().getSenderName())
                .senderMobile(deliveryOrderVo.getSenderVO().getSenderMobile())
                .driverName(driverOnlineUser.getUserName())
                .driverMobile(driverOnlineUser.getPhone())
                .receiverMobile(deliveryOrderVo.getReceiverVO().getReceiverMobile())
                .receiverName(deliveryOrderVo.getReceiverVO().getReceiverName())
                .orderNo(orderNo)
                .locationCertificate(deliveryOrderVo.getHasLocationCertificate()==true ? 1 : 0)
                .qualityCertificate(deliveryOrderVo.getHasQualityCertificate()==true ? 1 : 0)
                .build();
        //收货人部分判断
        if(receiverOnlineUser==null){
            throw new BizException("收货人未使用APP，无法新建订单");
        }
        CompanyUserRelation companyUserRelation = companyUserRelationService.selectByOnlineUserId(receiverOnlineUser.getId());
        if(companyUserRelation==null){
            throw new BizException("收货人未绑定公司信息，无法新建订单");
        }
        MerchantCompany merchantCompany = merchantCompanyService.selectByCompanyId(companyUserRelation.getCompanyId());
        deliveryOrder.setReceiverOnlineUserId(receiverOnlineUser.getId());
        deliveryOrder.setReceiverCompanyId(companyUserRelation.getCompanyId());
        deliveryOrder.setReceiverCompany(merchantCompany.getMerchantName());
        //推送APP消息给收货人
        phoneList.add(receiverOnlineUser.getPhone());



        //司机部分判断
        if(driverOnlineUser==null){
            OnlineUser driverUser = createUser(driverMobile, driverName);
            UserIdentity userIdentity = UserIdentity.builder()
                    .onlineUserId(driverUser.getId())
                    .identityId(IdentityTypeEnum.Driver.getIdentityTypeId())
                    .build();
            userIdentityService.insertIdentity(userIdentity);
            deliveryOrder.setDriverOnlineUserId(driverUser.getId());
        }else {
            deliveryOrder.setDriverOnlineUserId(driverOnlineUser.getId());

            List<UserIdentity> userIdentityList = userIdentityService.selectListByOnlineUserId(driverOnlineUser.getId());
            List<Integer> identityIdList = userIdentityList.parallelStream().map(userIdentity -> userIdentity.getIdentityId()).collect(Collectors.toList());
            if(!identityIdList.contains(IdentityTypeEnum.Driver.getIdentityTypeId())){
                UserIdentity userIdentity = UserIdentity.builder()
                        .identityId(IdentityTypeEnum.Driver.getIdentityTypeId())
                        .onlineUserId(driverOnlineUser.getId())
                        .build();
                userIdentityService.insertIdentity(userIdentity);
            }
            //推送APP消息
            phoneList.add(driverOnlineUser.getPhone());

        }

        //发货人部分判断
        if(senderOnlineUser==null){
            OnlineUser senderUser = createUser(senderMobile, senderName);
            UserIdentity userIdentity = UserIdentity.builder()
                    .onlineUserId(senderUser.getId())
                    .identityId(IdentityTypeEnum.Agency.getIdentityTypeId())
                    .build();
            userIdentityService.insertIdentity(userIdentity);
        }else {
            //推送APP消息
            phoneList.add(senderOnlineUser.getPhone());

        }

        //二维码生成url添加
//        MultipartFile file = CreateQRCode.createCode(deliveryOrderVo.getOrderNo());
//        ResultData<String> upload = fileService.upload(file);
//        if (!upload.isResult()) {
//            throw new BizException(upload.getMsg());
//        }
//        String url = upload.getData();
//        deliveryOrder.setQrcodeUrl(url);



        deliveryOrderMapper.insertSelective(deliveryOrder);

        //产地证明，质检单图片上传保存
        List<ValicatePictureVO> valicatePictureVOS = new ArrayList<>();
        MultipartFile[] file = deliveryOrderVo.getFile();
        if(deliveryOrderVo.getHasLocationCertificate()==true){
            if(deliveryOrderVo.getHasQualityCertificate()==true){
                ValicatePictureVO valicatePictureVO = ValicatePictureVO.builder()
                        .certificateType(1)
                        .file(file[0])
                        .build();
                ValicatePictureVO valicatePictureVO1 = ValicatePictureVO.builder()
                        .certificateType(2)
                        .file(file[1])
                        .build();
                valicatePictureVOS.add(valicatePictureVO);
                valicatePictureVOS.add(valicatePictureVO1);
            }else {
                ValicatePictureVO valicatePictureVO = ValicatePictureVO.builder()
                        .certificateType(1)
                        .file(file[0])
                        .build();
                valicatePictureVOS.add(valicatePictureVO);
            }
        }else {
            if(deliveryOrderVo.getHasQualityCertificate()==true){
                ValicatePictureVO valicatePictureVO = ValicatePictureVO.builder()
                        .certificateType(2)
                        .file(file[0])
                        .build();
                valicatePictureVOS.add(valicatePictureVO);
            }
        }


        vegetableCertificateService.upload(deliveryOrder.getId(),valicatePictureVOS);


        //货品信息保存
        GoodsVO[] goodsVOS = deliveryOrderVo.getGoodsVOS();
        List<GoodsVO> goodsVOList = new ArrayList<>();
        for (GoodsVO goodsVO : goodsVOS) {
            goodsVOList.add(goodsVO);
        }

        vegetableDeliveryRelationService.insertGoodsVOList(goodsVOList,deliveryOrder.getId());

        //操作订单日志表
        OrderLog orderLog = createOrderLog(systemUserVO,deliveryOrder.getId(),0,"用户新建订单");
        orderLogService.insertLog(orderLog);

        //收货人，发货人，司机APP消息推送
        String ticker = "您有一个新的订单";
        String title = "新订单";
        String remark = "备注";
        merchantService.pushMessage(phoneList,ticker,title,remark);

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
        if(identityIdList.contains(IdentityTypeEnum.Head.getIdentityTypeId())){
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
            if (identityIdList.contains(IdentityTypeEnum.Driver.getIdentityTypeId())) {
                returnDeliveryOrder = this.getUrgentDeliverOrder(driverUrgentDeliveryOrders);
                List<VegetableDeliveryRelation> vegetableDeliveryRelationList = vegetableDeliveryRelationService.selectGoodsByDeliveryId(returnDeliveryOrder.getId());

                homePageOrderVO = HomePageOrderVO.builder()
                        .deliveryOrder(returnDeliveryOrder)
                        .code(1)
                        .goodsName(vegetableDeliveryRelationList.get(0).getVegetableName())
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
            if (identityIdList.contains(IdentityTypeEnum.Head.getIdentityTypeId())) {
                returnDeliveryOrder = this.getUrgentDeliverOrder(responsibleUrgentDeliveryOrders);
                List<VegetableDeliveryRelation> vegetableDeliveryRelationList = vegetableDeliveryRelationService.selectGoodsByDeliveryId(returnDeliveryOrder.getId());
                homePageOrderVO = HomePageOrderVO.builder()
                        .deliveryOrder(returnDeliveryOrder)
                        .code(2)
                        .build();
                if(vegetableDeliveryRelationList.size()>0){
                    homePageOrderVO.setGoodsName(vegetableDeliveryRelationList.get(0).getVegetableName());
                }
                return homePageOrderVO;
            }
        }

        // 没有需要司机及负责人确认的订单
        responsibleDeliverOrders.addAll(otherRoleDeliveryOrders);
        if(responsibleDeliverOrders.isEmpty()){
            //没有未处理订单 怎么处理  随便给个死图
            return null;
        }else{
            returnDeliveryOrder = this.getUrgentDeliverOrder(responsibleUrgentDeliveryOrders);
            List<VegetableDeliveryRelation> vegetableDeliveryRelationList = vegetableDeliveryRelationService.selectGoodsByDeliveryId(returnDeliveryOrder.getId());
            homePageOrderVO = HomePageOrderVO.builder()
                    .deliveryOrder(returnDeliveryOrder)
                    .goodsName(vegetableDeliveryRelationList.get(0).getVegetableName())
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
        DeliveryOrder deliveryOrder1 = deliveryOrderMapper.selectByPrimaryKey(driverConfirmationVO.getId());

        //身份判断
        String message = "";
        if(driverConfirmationVO.getStatus()==0){
            List<CompanyUserRelation> companyUserRelationList = companyUserRelationService.selectFounderByCompanyId(deliveryOrder1.getReceiverCompanyId());
            List<Long> onlineUserIdList = companyUserRelationList.parallelStream().map(CompanyUserRelation::getOnlineUserId).collect(Collectors.toList());
            List<OnlineUser> onlineUserList = onlineUserService.selectByOnlineUserIdList(onlineUserIdList);
            List<String> phoneList = onlineUserList.parallelStream().map(OnlineUser::getPhone).collect(Collectors.toList());

            message = "司机确认发货";
            //调用消息推送接口，给订单的负责人推送消息
            phoneList.add(deliveryOrder1.getDriverMobile());
            String ticker = "司机确认发货";
            String title = "司机确认发货";
            String remark = "备注";
            merchantService.pushMessage(phoneList,ticker,title,remark);

        }else if(driverConfirmationVO.getStatus()==1){
            message = "商户确认订单";
            List<String> phoneList = new ArrayList<>();
            phoneList.add(deliveryOrder1.getDriverMobile());
            String ticker = "商户确认订单";
            String title = "商户确认订单";
            String remark = "备注";
            merchantService.pushMessage(phoneList,ticker,title,remark);
        }

        OrderLog orderLog = createOrderLog(systemUserVO,driverConfirmationVO.getId(),driverConfirmationVO.getStatus(),message);
        orderLogService.insertLog(orderLog);
    }

    @Override
    public DeliveryOrderMessageVO showOrderDetails(Long deliveryOrderId) {
        DeliveryOrder deliveryOrder = deliveryOrderMapper.selectByPrimaryKey(deliveryOrderId);
        Vehicle vehicle = vehicleService.selectByCarNo(deliveryOrder.getDriverPlateNumber());
        DriverVO driverVO = DriverVO.builder()
                .driverMobile(deliveryOrder.getDriverMobile())
                .driverName(deliveryOrder.getDriverName())
                .carPlateNo(deliveryOrder.getDriverPlateNumber())
                .build();
        ReceiverVO receiverVO = ReceiverVO.builder()
                .receiverMobile(deliveryOrder.getReceiverMobile())
                .receiverName(deliveryOrder.getReceiverName())
                .endAddr(deliveryOrder.getEndAddr())
                .build();
        SenderVO senderVO = SenderVO.builder()
                .senderMobile(deliveryOrder.getSenderMobile())
                .senderName(deliveryOrder.getSenderName())
                .startAddr(deliveryOrder.getStartAddr())
                .build();
        EntryFee entryFee = entryFeeService.selectByDeliveryOrderId(deliveryOrder.getId());
        List<VegetableDeliveryRelation> vegetableDeliveryRelationList = vegetableDeliveryRelationService.selectGoodsByDeliveryId(deliveryOrder.getId());
        List<GoodsVO> goodsVOList = vegetableDeliveryRelationList.parallelStream().map(s -> GoodsVO.builder()
                                          .loadStatus(s.getLoadStatus())
                                          .vegetableId(s.getVegetableId())
                                          .vegetableName(s.getVegetableName())
                                          .vegetablePrice(s.getVegetablePrice())
                                          .vegetableWeight(s.getVegetableWeight())
                                          .build())
                                          .collect(Collectors.toList());
        List<VegetableCertificate> vegetableCertificateList = vegetableCertificateService.selectByDeliveryOrderId(deliveryOrderId);
        List<ValicatePictureUrlVO> valicatePictureUrlVOList = vegetableCertificateList.parallelStream().map(s -> ValicatePictureUrlVO.builder()
                                                  .certificateType(s.getCertificateType())
                                                  .url(s.getUrl())
                                                  .build())
                                                  .collect(Collectors.toList());
        GuaranteeDeposit guaranteeDeposit = guaranteeDepositService.selectByDeliveryOrderId(deliveryOrder.getId());

        DeliveryOrderMessageVO deliveryOrderMessageVO = DeliveryOrderMessageVO.builder()
                .orderNo(deliveryOrder.getOrderNo())
                .creator(deliveryOrder.getCreator())
                .creator(deliveryOrder.getCreator())
                .tonnage(vehicle.getTonnage())
                .driverVO(driverVO)
                .receiverVO(receiverVO)
                .senderVO(senderVO)
                .actualFee(entryFee.getActualFee())
                .deposit(guaranteeDeposit.getGuaranteeDeposit())
                .estFee(entryFee.getEstFee())
                .goodsVOS(goodsVOList)
                .receiverCompany(deliveryOrder.getReceiverCompany())
                .valicatePictureVOS(valicatePictureUrlVOList)
                .loadStatus(deliveryOrder.getLoadStatus())
                .build();
        if(deliveryOrder.getQualityCertificate()==0){
            BigDecimal price = new BigDecimal(20);
            deliveryOrderMessageVO.setCheckFee(price);
            deliveryOrderMessageVO.setTotalFee(price.add(new BigDecimal(200)).add(entryFee.getEstFee()));
        }else {
            deliveryOrderMessageVO.setTotalFee(entryFee.getEstFee().add(new BigDecimal(200)));
        }
        return deliveryOrderMessageVO;
    }

    @Override
    public List<DeliveryOrder> selectByCompanyId(Long companyId) {
        List<DeliveryOrder> deliveryOrderList = deliveryOrderMapper.selectByCompanyId(companyId);
        return deliveryOrderList;
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

    //添加用户系统账户
    public OnlineUser createUser(String phone,String name){
        SystemUser systemUser = SystemUser.builder()
                .account("系统生成账号"+phone)
                .chineseName(name)
                .mobile(phone)
                .regDate(new Date())
                .pwd(MD5Util.encrypt("111111"))
                .accountStatus((byte)0)
                .gmtCreate(new Date())
                .build();
        systemUserService.insertSystemUser(systemUser);

        OnlineUser onlineUser = OnlineUser.builder()
                .userName(name)
                .phone(phone)
                .systemUserId(systemUser.getId())
                .build();
        onlineUserService.insertOnlineUser(onlineUser);

        //这种没有注册的情况下，调用短信通知接口，通知被添加的用户进行APP下载（预留）
        smsService.sendSmsCode(phone,"您有一个订单，请下载APP",1L);

        return onlineUser;
    }
    @Override
    public DeliveryOrder searchDeliveryOrderById(Long id) {
        return deliveryOrderMapper.selectByPrimaryKey(id);

}

    @Override
    public PageInfo<HomePageOrderVO> showOrderList(SystemUserVO systemUserVO, Integer pageNum, Integer pageSize, Integer status) {
        List<IdentityVO> identityTypes = systemUserVO.getIdentityTypes();
        List<Integer> identityIdList = identityTypes.parallelStream().map(IdentityVO::getId).collect(Collectors.toList());
        //负责人身份查询出的集合
        List<DeliveryOrder> responsibleDeliverOrders ;
        //非负责人查询出的集合
        List<DeliveryOrder> otherRoleDeliveryOrders ;
        //所有订单货品关系集合
        List<VegetableDeliveryRelation> vegetableDeliveryRelationList = vegetableDeliveryRelationService.selectAll();
        Map<Long,String> orderIdNameMap = vegetableDeliveryRelationList.parallelStream().collect(Collectors.toMap(VegetableDeliveryRelation::getDeliveryOrderId, VegetableDeliveryRelation::getVegetableName, (key1, key2) -> key1));
        //返回的集合
        List<HomePageOrderVO> homePageOrderVOList = new ArrayList<>();
        // 查询订单
        if(identityIdList.contains(IdentityTypeEnum.Head.getIdentityTypeId())){
            //以负责人身份查询订单
            CompanyUserRelation companyUserRelation = companyUserRelationService.selectCompanyUserRelation(systemUserVO.getOnlineUserId());
            responsibleDeliverOrders = deliveryOrderMapper.selectByCompanyId(companyUserRelation.getCompanyId());
            homePageOrderVOList = responsibleDeliverOrders.parallelStream().map(s -> HomePageOrderVO.builder()
                                           .deliveryOrder(s)
                                           .goodsName(orderIdNameMap.get(s.getId()))
                                           .build())
                                           .collect(Collectors.toList());
            List<HomePageOrderVO> homePageOrderVOS = checkStatus(homePageOrderVOList, status);
            homePageOrderVOS.forEach(s -> {
                addCode(s,identityIdList);
            });


            PageHelper.startPage(pageNum,pageSize);
            PageInfo<HomePageOrderVO> homePageOrderVOPageInfo = new PageInfo<>();
            homePageOrderVOPageInfo.setData(homePageOrderVOS);
            return homePageOrderVOPageInfo;
        }
        // 以非负责人身份查询
        otherRoleDeliveryOrders = deliveryOrderMapper.selectAllByIdAndPhone(systemUserVO.getOnlineUserId(),systemUserVO.getPhone());
        if(identityIdList.contains(IdentityTypeEnum.Driver.getIdentityTypeId())){
            //如果是司机
            homePageOrderVOList = otherRoleDeliveryOrders.parallelStream().map(s -> HomePageOrderVO.builder()
                    .deliveryOrder(s)
                    .goodsName(orderIdNameMap.get(s.getId()))
                    .build())
                    .collect(Collectors.toList());
            List<HomePageOrderVO> homePageOrderVOS = checkStatus(homePageOrderVOList, status);
            homePageOrderVOS.forEach(s -> {
                addCode(s,identityIdList);
            });
            PageHelper.startPage(pageNum,pageSize);
            PageInfo<HomePageOrderVO> homePageOrderVOPageInfo = new PageInfo<>();
            homePageOrderVOPageInfo.setData(homePageOrderVOS);
            return homePageOrderVOPageInfo;
        }

        List<HomePageOrderVO> homePageOrderVOS = checkStatus(homePageOrderVOList, status);
        PageHelper.startPage(pageNum,pageSize);
        PageInfo<HomePageOrderVO> homePageOrderVOPageInfo = new PageInfo<>();
        homePageOrderVOPageInfo.setData(homePageOrderVOS);
        return homePageOrderVOPageInfo;
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

    //根据身份和订单状态判断返回code
    public HomePageOrderVO addCode(HomePageOrderVO homePageOrderVO,List<Integer> identityVOList){
        //如果是商户负责人查看
        if(identityVOList.contains(IdentityTypeEnum.Head.getIdentityTypeId())){
            //如果身份有司机
            if(identityVOList.contains(IdentityTypeEnum.Driver.getIdentityTypeId())){
                //如果订单状态为待司机确认
                if(homePageOrderVO.getDeliveryOrder().getStatus()==0){
                    homePageOrderVO.setCode(1);
                }
            }else {
                //如果不是司机
                if(homePageOrderVO.getDeliveryOrder().getStatus()==1){
                    homePageOrderVO.setCode(2);
                }
            }
        }else {
            //如果不是负责人，是司机
            if(identityVOList.contains(IdentityTypeEnum.Driver.getIdentityTypeId())){
                //如果订单状态为待司机确认
                if(homePageOrderVO.getDeliveryOrder().getStatus()==0){
                    homePageOrderVO.setCode(1);
                }
            }
        }
        return homePageOrderVO;
    }

    //根据查询状态返回
    public List<HomePageOrderVO> checkStatus(List<HomePageOrderVO> homePageOrderVOList,Integer staus){
        List<HomePageOrderVO> homePageOrderVOS = new ArrayList<>();

        homePageOrderVOList.forEach(s -> {
            if(staus==1){
                if(s.getDeliveryOrder().getStatus()==0||s.getDeliveryOrder().getStatus()==1||s.getDeliveryOrder().getStatus()==2){
                    homePageOrderVOS.add(s);
                }
            }
            if(staus==2){
                if(s.getDeliveryOrder().getStatus()==3){
                    homePageOrderVOS.add(s);
                }
            }
            if(staus==3){
                if(s.getDeliveryOrder().getStatus()==-1||s.getDeliveryOrder().getStatus()==5||s.getDeliveryOrder().getStatus()==6){
                    homePageOrderVOS.add(s);
                }
            }
        });
        return homePageOrderVOS;
    }
}