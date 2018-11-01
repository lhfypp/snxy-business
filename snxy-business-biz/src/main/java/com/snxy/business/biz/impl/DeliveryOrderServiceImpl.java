package com.snxy.business.biz.impl;

import com.snxy.business.biz.feign.FileService;
import com.snxy.business.biz.util.JudgIdentityUtil;
import com.snxy.business.dao.mapper.*;
import com.snxy.business.domain.*;
import com.snxy.business.service.*;
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
    private SystemUserInfoService SystemUserInfoService;
    @Resource
    private VegetableDeliveryRelationService vegetableDeliveryRelationService;
    @Resource
    private VehicleGpsRecordService vehicleGpsRecordService;
    @Resource
    private FileService fileService;
    @Resource
    private VegetableCertificateService vegetableCertificateService;
    @Resource
    private VegetableImageService  vegetableImageService;
    @Resource
    private CurrOrderReceiverService currOrderReceiverService;
    @Resource
    private DeliveryOrderMapper deliveryOrderMapper;
    @Resource
    private VehicleService vehicleService;
	@Resource
    private RedisTemplate<String,Object> redisTemplate;
	@Resource
    private SystemUserService systemUserService;
	@Resource
    private EntryFeeService entryFeeService;
    @Resource
    private OnlineUserService onlineUserService;
	@Resource
    private CompanyUserRelationService companyUserRelationService;
	@Resource
    private MerchantCompanyService merchantCompanyService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String saveDeliveryOrder(DeliveryOrderVo deliveryOrderVo) {
        //订单信息DeliveryOrder
        DeliveryOrder deliveryOrder = new DeliveryOrder();
        BeanUtils.copyProperties(deliveryOrderVo,deliveryOrder);
        deliveryOrderMapper.insertSelective(deliveryOrder);
        Long id = deliveryOrder.getId();

        //货品信息VegetableDeliveryRelation
        List<Goods> goodsList = deliveryOrderVo.getGoodsList();
        List<VegetableDeliveryRelation> vegetableDeliveryRelationList = new ArrayList<>();
        for (int i = 0; i < goodsList.size(); i++) {
            VegetableDeliveryRelation vegetableDeliveryRelation = new VegetableDeliveryRelation();
            vegetableDeliveryRelation.setDeliveryOrderId(id);
            BeanUtils.copyProperties(goodsList.get(i),vegetableDeliveryRelation);
            vegetableDeliveryRelationList.add(vegetableDeliveryRelation);
        }
        vegetableDeliveryRelationService.insertGoodList(vegetableDeliveryRelationList);

        //货物照片上传
        List<MultipartFile> goodsImage = deliveryOrderVo.getFile();
        if(goodsImage!=null){
            List<VegetableImage> vegetableImageList = new ArrayList<>();
            for (int i = 0; i < goodsImage.size(); i++) {
                ResultData<String> upload = fileService.upload(goodsImage.get(i));
                if (!upload.isResult()) {
                    throw new BizException(upload.getMsg());
                }
                String goodsImgUrl = upload.getData();
                VegetableImage vegetableImage = VegetableImage.builder()
                        .deliveryOrderId(id)
                        .type(1)
                        .uploadTime(new Date())
                        .url(goodsImgUrl).build();
                vegetableImageList.add(vegetableImage);
            }
            vegetableImageService.insertVegetableImageList(vegetableImageList);
        }

        //产地证明上传VegetableCertificate
        List<ValicatePicture> certificates = deliveryOrderVo.getCertificates();
        if(certificates!=null){
            List<VegetableCertificate> vegetableCertificateList = new ArrayList<>();
            for (int i = 0; i < certificates.size(); i++) {
                //如果是1，就是产地证明
                ResultData<String> upload = fileService.upload(certificates.get(i).getFile());
                if (!upload.isResult()) {
                    throw new BizException(upload.getMsg());
                }
                String data = upload.getData();
                VegetableCertificate vegetableCertificate = VegetableCertificate.builder()
                        .deliveryOrderId(id)
                        .uploadTime(new Date())
                        .url(data).build();
                if (certificates.get(i).getCertificateType().equals(1)) {
                    vegetableCertificate.setCertificateType(1);
                }
                if (certificates.get(i).getCertificateType().equals(2)) {
                    vegetableCertificate.setCertificateType(2);
                }
                vegetableCertificateList.add(vegetableCertificate);
            }
            vegetableCertificateService.insertImageList(vegetableCertificateList);
        }

        //远程调用自动计算进门费用接口，计算出预计的进门费用插入entryfree表

        //发布订单对司机手机号进行判断是否注册
        SystemUser systemUser = systemUserService.selectByMobile(deliveryOrderVo.getDriverMobile());
        if (systemUser==null){
            //当查询不到司机的手机号注册信息时给司机手机号发送app下载了短信链接
            System.out.println("给司机发送APP下载短信链接");
        }else {
            //如果司机已经注册则app推送订单消息
            System.out.println("通过APP给司机推送消息");
        }
        return "发布成功";
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
    public List<DriverOrderVo> selectDriverOrder(String driverMobile) {
        List<String> statusList = new ArrayList();
        statusList.add("待接单");
        statusList.add("取消");
        statusList.add("已接单");
        statusList.add("装货完成");
        statusList.add("运输中");
        statusList.add("检测中");
        statusList.add("合格关闭");
        statusList.add("不合格关闭");
        statusList.add("被遣离");
        List orderIdList = currOrderReceiverService.selectOrderIdByDriverMobile(driverMobile);
        List<DriverOrderVo> driverOrderVoList = new ArrayList<>();
        List<DeliveryOrder> deliveryOrderList = deliveryOrderMapper.selectDriverOrderByOderId(orderIdList);
        for (int i = 0; i < deliveryOrderList.size(); i++) {
            DriverOrderVo driverOrderVo = new DriverOrderVo();
            BeanUtils.copyProperties(deliveryOrderList.get(i),driverOrderVo);

            driverOrderVo.setStatusDes(statusList.get(deliveryOrderList.get(i).getStatus()));
            driverOrderVoList.add(driverOrderVo);
        }

        return driverOrderVoList;
    }

    @Override
    public DeliveryOrder selectOrderByOrderId(Long orderId) {
        return deliveryOrderMapper.selectByPrimaryKey(orderId);
    }

    //查询订单信息详情
    @Override
    public BillInfoDetail searchDeliverOrderinfo(Long deliveryOrderId) {
        //查询出货物信息的货物id，重量 名称，价格
        List<Goods>goods=vegetableDeliveryRelationService.selectAllByOrderId(deliveryOrderId);
        // 查询出valications下的url，certificateType
        List<Valication> valications=vegetableCertificateService.getValications(deliveryOrderId);
        //查询出GPSLocation的信息
        List<GPSLocation>gPSLocations=vehicleGpsRecordService.selectLocationGPS(deliveryOrderId);
        //查询出所有的图片
        List<String>images=vegetableImageService.searchImages(deliveryOrderId);
        //查询出司机信息
        DriverPartInfo driverPartInfo=currOrderReceiverService.selectDriverPartInfo(deliveryOrderId);
        //查询出订单表信息
        DeliveryOrder DeliveryOrder =deliveryOrderMapper.selectBydDeliveryOrderId(deliveryOrderId);
        BillInfoDetail billInfoDetail=BillInfoDetail.builder()
                .driverPartInfo(driverPartInfo)
                .deliveryOrder(DeliveryOrder)
                .goods(goods)
                .gPSLocations(gPSLocations)
                .images(images)
                .valications(valications)
                .build();
        return billInfoDetail;
    }

    @Override
    public PageInfo<BillInfo> searchDeliveryOrderByPage(String orderStatus, String searchName,SystemUserVo systemUserVO) {
        List<String> sendPhones=new ArrayList<String>();
        OnlineUser  onlineUser=onlineUserService.selectBySystemUserId(systemUserVO.getSystemUserId());
        String userPhone="";
        String onlineUserID="";
       if(onlineUser!=null) {
            userPhone = onlineUser.getPhone();
            onlineUserID = onlineUser.getId().toString();
       }
       String JudgIdentity= JudgIdentityUtil.judgIdentity(systemUserVO);
       if(JudgIdentity.contains("2")&&!JudgIdentity.contains("1")) {
           sendPhones.add(userPhone);
       }
       if(JudgIdentity.contains("1")) {
           sendPhones = SystemUserInfoService.searchPhones(onlineUserID);
       }


        PageHelper.startPage(1,10);

        List<BillInfo> listBillInfo=deliveryOrderMapper.searchDeliveryOrder(sendPhones, orderStatus, searchName);
        PageInfo<BillInfo> pageInfo = new PageInfo<BillInfo>(listBillInfo);
        return pageInfo;
    }


    @Override
    public OrderNoVo createDeliveryOrder(Long onlineUserId) {
        OrderNoVo orderNoVo = new OrderNoVo();
        String orderNo = this.getOrderNo();
        orderNoVo.setOrderNo(orderNo);
        orderNoVo.setOnlineUserId(onlineUserId);

        String name = onlineUserService.selectNameByOnlineUserId(onlineUserId);
        orderNoVo.setName(name);
        Long companyId = companyUserRelationService.selectCompanyIdByOnlineUserId(onlineUserId);
        MerchantCompanyVo merchantCompanyVo = merchantCompanyService.selectByPrimaryKey(companyId);
        orderNoVo.setMerchantName(merchantCompanyVo.getMerchantName());
        return orderNoVo;
    }

    @Override
    public void checkQualityCertificate(Long qualityCertificateId, Integer qualitied, Long orderNo) {

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

        currOrderReceiverService.changeDriver(Long.parseLong(orderId),driverName,drivePhone);
    }

    @Override
    public void updateOrder(UpdateBillInfoDetailVo billInfoDetail){
        UpdateBillInfoDetail updateBillInfoDetail=new UpdateBillInfoDetail();
        //更新订单表
        deliveryOrderMapper.updateOrder(updateBillInfoDetail);
        //更新当前接单对象表 实体类 DriverPartInfo
        DriverPartInfo driverPartInfo=new DriverPartInfo();
        currOrderReceiverService.updateByAgent(driverPartInfo);//待改进
        //更新vegetable_certificate表
        List<String>url=new ArrayList<String>();
        long orderId=1;
        vegetableCertificateService.updatebyAgent(orderId,url);//待改进
        //更新vegetable_delivery_relation表
        List<Goods>goods=new ArrayList<>();
        vegetableDeliveryRelationService.updatebyAgent(goods);//待改进
        //更新vegetable_image表
        VegetableImage vegetableImage=null;
        vegetableImageService.updateByAgent(vegetableImage);//待改进

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateEndLoading(Long deliveryOrderId,Integer status) {
        //调用APP消息推送接口，向商户推送消息司机已装货完毕
        deliveryOrderMapper.updateEndLoading(deliveryOrderId,status);
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

        vegetableImageService.insertVegetableImageList(vegetableImageList);

        //装车情况上传
        deliveryOrderMapper.updateLoadStatus(adminChangeOrderVo.getDeliveryOrderId(),adminChangeOrderVo.getLoadStatus());

        //进门费用修改
        EntryFee entryFee = new EntryFee();
        entryFee.setActualFee(adminChangeOrderVo.getEntryFee());
        entryFee.setRemark(adminChangeOrderVo.getRemark());
        entryFee.setDeliveryOrderId(adminChangeOrderVo.getDeliveryOrderId());
        entryFeeService.updateByOrderNo(entryFee);
    }

    @Override
    public Map<String ,Object> getVehiclesForDriver(String orderId) {
        //用在线用户id进行查询该用户的所有车辆信息（driverinfo，vehicle表）
        long onlineUserId=1;
        List<VehiclePartInfo> VehiclePartInfoList=vehicleService.searchVehiclePartInfo(onlineUserId);
        Map<String ,Object>vehicleInfosMap=new HashMap<>();
        vehicleInfosMap.put("orderId",orderId);
        vehicleInfosMap.put("vehiclePartInfo",VehiclePartInfoList);
        return vehicleInfosMap;
    }

    @Override
    public OrderVo selectOrderMessageBydeliveryOrderId(Long deliveryOrderId) {
        OrderVo orderVo = new OrderVo();
        DeliveryOrder deliveryOrder = deliveryOrderMapper.selectByPrimaryKey(deliveryOrderId);
        BeanUtils.copyProperties(deliveryOrder, orderVo);

        List<Goods> goodsList = vegetableDeliveryRelationService.selectAllByOrderId(deliveryOrderId);
        orderVo.setGoodsList(goodsList);
        CurrOrderReceiver currOrderReceiver = currOrderReceiverService.selectDriverMessageByOrderId(deliveryOrderId);
        BeanUtils.copyProperties(currOrderReceiver, orderVo);

        List<VegetableImage> vegetableImageList = vegetableImageService.selectByOderId(deliveryOrderId);
        List<String> urlList = new ArrayList<>();
        for (int i = 0; i < vegetableImageList.size(); i++) {
            VegetableImage vegetableImage = vegetableImageList.get(i);
            String url = vegetableImage.getUrl();
            urlList.add(url);
        }
        orderVo.setUrlList(urlList);

        List<VegetableCertificate> vegetableCertificateList = vegetableCertificateService.selectByOrderId(deliveryOrderId);

        orderVo.setVegetableCertificateList(vegetableCertificateList);

        return orderVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void checkProductionCertificate(Integer qualitied,Long orderNo) {

        deliveryOrderMapper.updateLocationCertificate(orderNo,qualitied);

        if(qualitied==0){
            deliveryOrderMapper.cancelOrderByOrderId(orderNo,7);
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void checkQualityCertificate(Integer qualitied,Long orderNo) {
        deliveryOrderMapper.updateQualityCertificate(orderNo,qualitied);
        if(qualitied==0){
            deliveryOrderMapper.cancelOrderByOrderId(orderNo,7);
        }
    }

    @Override
    public void updateCurrOrderReceiver(String OrderId, String vehicleId) {
        currOrderReceiverService.updateCurrOrderReceiver(OrderId,vehicleId);
    }

    @Override
    public void tranferOrder(String orderId, String driveMobile, String driverName) {
        currOrderReceiverService.tranferOrder(Long.parseLong(orderId),driveMobile,driverName);
    }

}
