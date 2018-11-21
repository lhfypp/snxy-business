package com.snxy.business.biz.impl;

import com.snxy.business.domain.*;
import com.snxy.business.service.*;
import com.snxy.business.service.vo.*;
import com.snxy.common.util.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PersonalCenterServiceImpl implements PersonalCenterService {

    @Resource
    private CustomerMessageService customerMessageService;
    @Resource
    private CommonProblemsService commonProblemsService;
    @Resource
    private SystemUserInfoService systemUserInfoService;
    @Resource
    private TradeResultService tradeResultService;
    @Resource
    private DeliveryOrderService deliveryOrderService;
    @Resource
    private VehicleService vehicleService;
    @Resource
    private EntryFeeService entryFeeService;
    @Resource
    private GuaranteeDepositService guaranteeDepositService;
    @Resource
    private CompanyUserRelationService companyUserRelationService;
    @Resource
    private OnlineUserService onlineUserService;
    @Resource
    private UserIdentityService userIdentityService;
    @Resource
    private IdentityTypeService identityTypeService;
    @Resource
    private VegetableDeliveryRelationService vegetableDeliveryRelationService;
    @Resource
    private SystemUserService systemUserService;



    @Override
    public CustomerVO myCustomer(Long systemUserId) {

        List<CustomerMessage> customerMessageList = customerMessageService.selectAllCustomerMessage();
        List<CommonProblems> problemsList = commonProblemsService.selectAllCommonProblems();
        String mobile = systemUserInfoService.selectMobileBySystemUserId(systemUserId);
        CustomerVO customerVO = CustomerVO.builder()
                .customerMessageList(customerMessageList)
                .commonProblemsList(problemsList)
                .mobile(mobile)
                .build();
        return customerVO;
    }

    @Override
    public PageInfo<List<tradeVO>> tradeList( Long businessTypeId, Date startTime, Date endTime, Integer pageNum, Integer pageSize) {

        //如果用户没有传入支付渠道方式，默认支付宝
        if (businessTypeId==null){
            businessTypeId=2L;
        }
        //如果交易的时间段没提供，就默认全查
        if (startTime==null||endTime==null){
            PageInfo<List<tradeVO>> pageInfo = tradeResultService.tradeListAll( businessTypeId,pageNum, pageSize);
        }
        //有起止时间的交易记录
        PageInfo<List<tradeVO>> pageInfo =  tradeResultService.tradeListTime( businessTypeId,startTime,endTime,pageNum,pageSize);

        return pageInfo;
    }

    @Override
    public TradeInfoVO tradeInfo(String orderNo) {
        TradeResult tradeResult = tradeResultService.selectTradeInfo(orderNo);

        DeliveryOrder deliveryOrder = deliveryOrderService.selectDriverNumByOrderNo(orderNo);
        //根据货运单里的车牌号去vehicle表中查询吨位
        Vehicle vehicle = vehicleService.selectTonnageByDriverNum(deliveryOrder.getDriverPlateNumber());
        //根据货运单号查询进门费
        EntryFee entryFee = entryFeeService.selectFeeByOrderNo(orderNo);
        //根据货运单号查询押金
       GuaranteeDeposit guaranteeDeposit =  guaranteeDepositService.selectGuaranteeByOrderNo(orderNo);


        TradeInfoVO.OrderInfoVO orderInfoVO  = TradeInfoVO.OrderInfoVO.builder()
                .vegetableName(tradeResult.getBody())
                .loadStatus(deliveryOrder.getLoadStatus())
                .tonnage(vehicle.getTonnage())
                .build();
        TradeInfoVO.PayInfoVO payInfoVO = TradeInfoVO.PayInfoVO.builder()
                .actualFee(entryFee.getActualFee())
                .guaranteeDeposit(guaranteeDeposit.getGuaranteeDeposit())
                .testFee(new BigDecimal(20))
                .build();

        TradeInfoVO tradeInfoVO = TradeInfoVO.builder()
                .driverNum(deliveryOrder.getDriverPlateNumber())
                .orderInfoVO(orderInfoVO)
                .payInfoVO(payInfoVO)
                .tradeMethod(tradeResult.getTradeMethod())
                .tradeTime(tradeResult.getTradeTime())
                .tradeNo(tradeResult.getOutTradeNo())
                .totalFee(tradeResult.getTotalFee())
                .refundFee(tradeResult.getRefundFee())
                .build();

        return tradeInfoVO;
    }

    @Override
    public PersonalVO personalInfo(Long systemUserId) {
        Long onlineUserId = onlineUserService.selectOnlineUserIdBySystemUserId(systemUserId);
        // 根据company_user_relation和online_user两张表中onlineUserId相等，得到userName
        OnlineUser onlineUser = companyUserRelationService.selectUserNameByOlineUserId(onlineUserId);
        //通过onlineUserId查询identityId
        List<Integer> identityIdList = userIdentityService.selectIdentityIdByOnlineUserId(onlineUserId);
        //通过identityIdList查询用户身份
          List<String> identityNameList = identityTypeService.selectIdNameByIdList(identityIdList);

          PersonalVO personalVO = PersonalVO.builder()
                  .identityNameList(identityNameList)
                  .phone(onlineUser.getPhone())
                  .userName(onlineUser.getUserName())
                  .build();
          return personalVO;
    }

    @Override
    public TradeAnalysisVO tradeAnalysis(Date startTime, Date endTime) {
        //没给起止时间，默认全查
        if (startTime==null||endTime==null){
            String  orderCount = tradeResultService.selectOrderCount();
        }
        //进门订单数总计,查询这段时间内有多少订单
       String  orderCount = tradeResultService.selectOrderCountTime(startTime,endTime);
        //进门费总计
      List<String> orderNoList = tradeResultService.selectOrderList(startTime,endTime);
      log.info("orderNoList ---->: [{}]",orderNoList);
        int size = orderNoList.size();
        System.out.println("========="+size+"=========");
        System.out.println("========="+orderCount+"=========");
        //根据订单号批量查进门费
        String entryFeeTotal = tradeResultService.selectEntryCostTotalByOrderList(orderNoList);
      //微信收费总计 channel=1
         String weChatTotal = tradeResultService.selectWeChatByOrderList(orderNoList);
        //查询支付宝收费channel=2
          String alpayTotal = tradeResultService.selectAlipayByOrderList(orderNoList);
        //检测费共计business_type_id=5
       String qualityTotal = tradeResultService.selectQualityTotalByOrderListAndTypeId(orderNoList);
        //进出门货物公斤共计，通过订单号找货运单号
       List<Long> deliveryIdList = deliveryOrderService.selectDeliveryIdByOrderList(orderNoList);
       //去vegetableDeliveryRelation表中查出货物名称和重量
        List<VegetableDeliveryRelation> vegetableDeliveryRelationList = vegetableDeliveryRelationService.selectNameWeightByDeliveryIdList(deliveryIdList);

       List<VegetableVO> vegetableVOList = vegetableDeliveryRelationList.parallelStream().map(s -> VegetableVO.builder()
                                 .vegetableName(s.getVegetableName())
                                 .vegetableWeight(s.getVegetableWeight())
                                 .build()).collect(Collectors.toList());
       //货物总重量
        BigDecimal weightTotal = new BigDecimal(0);
        for (int i=0;i<vegetableDeliveryRelationList.size();i++){
            BigDecimal vegetableWeight = vegetableDeliveryRelationList.get(i).getVegetableWeight();
            weightTotal.add(vegetableWeight);
        }

        TradeAnalysisVO tradeAnalysisVO = TradeAnalysisVO.builder()
                .alipayTotal(alpayTotal)
                .weChatTotal(weChatTotal)
                .entryCostTotal(entryFeeTotal)
                .entryOrderTotal(orderCount)
                .vegetableWeightTotal(weightTotal)
                .qualityTotal(Integer.parseInt(qualityTotal))
                .vegetableVOList(vegetableVOList)
                .build();

        return  tradeAnalysisVO;

    }

    @Override
    public AccountVO selectAccount(Long systemUserId) {
           SystemUser systemUser = systemUserService.selectAccount(systemUserId);
        AccountVO accountVO = AccountVO.builder()
                .account(systemUser.getAccount())
                .mobile(systemUser.getMobile())
                .build();
        return accountVO;
    }
}
