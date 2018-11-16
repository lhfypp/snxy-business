package com.snxy.business.biz.impl;

import com.github.pagehelper.PageHelper;
import com.snxy.business.dao.mapper.CompanyUserRelationMapper;
import com.snxy.business.dao.mapper.QualitySheetMapper;
import com.snxy.business.domain.CompanyUserRelation;
import com.snxy.business.domain.QualitySheet;
import com.snxy.business.domain.VegetableDeliveryRelation;
import com.snxy.business.service.QualitySheetService;
import com.snxy.common.util.PageInfo;


import com.snxy.business.biz.Util.JudgIdentityUtil;
import com.snxy.business.domain.DeliveryOrder;
import com.snxy.business.service.*;
import com.snxy.business.service.vo.*;
import com.snxy.common.exception.BizException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service
@Slf4j
public class QualitySheetServiceImpl implements QualitySheetService {

    @Resource
    private QualitySheetMapper qualitySheetMapper;
    @Resource
    private CompanyUserRelationMapper companyUserRelationMapper;
    @Resource
    private GuaranteeDepositService guaranteeDepositService;
    @Resource
    private DeliveryOrderService deliveryOrderService;
    @Resource
    private CompanyUserRelationService CompanyUserRelationService;
    @Resource
    private VegetableDeliveryRelationService vegetableDeliveryRelationService;
    @Resource
    private MerchantCompanyService merchantCompanyService;
    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    //检测单详情
    @Override
    public QualitySheet qualitySheetByOrderId(Long deliveryOrderId) {
        QualitySheet qualitySheet = qualitySheetMapper.selectByOrderId(deliveryOrderId);
        return qualitySheet;
    }

    //查看质量检测单
    @Override
    public PageInfo<QualitySheet> qualitySheetList(Long onlineUserId) {
        //获取公司id
        CompanyUserRelation companyUserRelation = companyUserRelationMapper.selectByOnlineUserId(onlineUserId);
        Long companyId = companyUserRelation.getCompanyId();
        //查看质量检测单
        PageHelper.startPage(1,10);
        List<QualitySheet> qualitySheetList = qualitySheetMapper.selectByCompanyId(companyId);
        PageInfo<QualitySheet> qualitySheetPageInfo = new PageInfo<>();
        qualitySheetPageInfo.setData(qualitySheetList);
        return qualitySheetPageInfo;
    }


    //创建检测单
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String createQualitySheet(CheckSheetVO checkSheetVO,Long userId) {
        //用手机号查出用户id
        Integer lastQualityNo = (Integer) redisTemplate.opsForValue().get("qualityNo");
        String sixNumber;
        if (lastQualityNo == null || lastQualityNo.equals("")) {
            redisTemplate.opsForValue().set("qualityNo", 1);
            sixNumber= String.format("%06d", 1);
        } else {
            redisTemplate.opsForValue().set("qualityNo", lastQualityNo + 1);
            sixNumber= String.format("%06d",lastQualityNo + 1 );
        }
        Date now=new Date();
        SimpleDateFormat sf=new SimpleDateFormat("yyyyMMddhhmmss");
        //生成检测单号
        StringBuffer sb=new StringBuffer();
        sb.append("JC");
        sb.append(sf.format(now));
        sb.append(sixNumber);
        String weightResource=checkSheetVO.getWeight();
        Float   weightTarget;
        if( weightResource!=null&&!"".equals(weightResource)){
            weightTarget=Float.parseFloat(weightResource);
        }else{
            weightTarget=null;
        }
        QualitySheet qualitySheet= QualitySheet.builder()
                    .deliveryOrderId(Long.parseLong(checkSheetVO.getDeliveryOrderId()))
                    .vehiclePlateNumber(checkSheetVO.getCarPlateNO())
                    .categoryName(checkSheetVO.getGoodName())
                    .checkTime(null)//检测时间
                    .gmtCreate(now)
                    .gmtModified(null)//修改时间
                    .code(sb.toString())
                    .isDelete((byte)0)
                    .productionLocation(checkSheetVO.getLocation())
                    .qrcodeUrl(null)//二维码地址
                    .remark(null)//备注
                    .status(1)
                    .vegetableCategoryId(null)//货品id,无这张表
                    .weight(weightTarget)
                    .qualified(null)//是否合格
                    .useId(userId)
                    .build();
        int result=qualitySheetMapper.insert(qualitySheet);
        if(1!=result){
            throw new BizException("创建检测单失败");
        }
        return "创建检测单成功";
    }
    //查询出待检测单(分页)
    @Override
    public PageInfo searchWillBeQualitySheet(SystemUserVO systemUserVO,String searchName) {

        long userId=1;
        List<QualitySheetVO> qualitySheetVOList=new ArrayList<>();
        String judgIdentityStr= JudgIdentityUtil.judgIdentity(systemUserVO);
        if(judgIdentityStr.contains("1")){//查出商户负责人的所有质量检测单
            //用户查出公司id
           String  companyId= CompanyUserRelationService.searchComIdByUseId(userId);
            //查出所有的质量检测单(已写)
            PageHelper.startPage(1,10);

        }else if(judgIdentityStr.contains("2")||judgIdentityStr.contains("3")){
            //查询出所有的与其相关待检测单(用在线用户查)
            PageHelper.startPage(1,10);
           qualitySheetMapper.selectQualitySheetList(userId, searchName).forEach(qualitySheet ->qualitySheetVOList.add(QualitySheetVO.builder()
                   .qualitySheerId(qualitySheet.getId().toString())
                   .platNumber(qualitySheet.getVehiclePlateNumber())
                   .status(qualitySheet.getStatus())
                   .qualityNO(qualitySheet.getCode())
                   .qrcode(qualitySheet.getQrcodeUrl())
                   .name(qualitySheet.getCategoryName())
                   .load(qualitySheet.getWeight())
                   .userId(qualitySheet.getUseId())//用于判断是否可以删除
                   .proposeTime(qualitySheet.getGmtCreate())
                   .build()
            ));
        }
        PageInfo<QualitySheetVO> pageInfo;
        pageInfo=new PageInfo<>();
        pageInfo.setData(qualitySheetVOList);
        return pageInfo;
    }
    // 查询出所有的检测单
    @Override
    public PageInfo searchQualitySheet(SystemUserVO systemUserVO,String searchName){
        long userId=1;
        List<QualitySheetVO> qualitySheetVOList=new ArrayList<>();
        List<QualitySheet> qualitySheetList=new ArrayList<>();
        PageHelper.startPage(1,10);
        qualitySheetList.addAll(qualitySheetMapper.selectAllQualitySheetList(userId,searchName));
        qualitySheetList.forEach(qualitySheet ->qualitySheetVOList.add(QualitySheetVO.builder()
                .qualitySheerId(qualitySheet.getId().toString())
                .platNumber(qualitySheet.getVehiclePlateNumber())
                .status(qualitySheet.getStatus())
                .qualityNO(qualitySheet.getCode())
                .qrcode(qualitySheet.getQrcodeUrl())
                .name(qualitySheet.getCategoryName())
                .load(qualitySheet.getWeight())
                .userId(qualitySheet.getUseId())//用于判断是否可以删除
                .proposeTime(qualitySheet.getGmtCreate())
                .build()
        ));
        PageInfo<QualitySheetVO> pageInfo;
        pageInfo=new PageInfo<>();
        pageInfo.setData(qualitySheetVOList);
        return pageInfo;
    }

    @Override
    public int deleteQualitySheetById(String qualitySheetId) {
        if(1!=qualitySheetMapper.deleteQualitySheetById(qualitySheetId)) {
            throw new BizException("删除检测单失败!");
        }
        return 1;
    }

    //创建待检测单，每隔 5分钟(是否写在支付接口中?)
    @Scheduled(cron = "0 0/5 * * * *  ")
    public String createQualitySheet() {
        log.info("创建待检测单开始");
        List<DeliveryOrder>deliveryOrderList=new ArrayList<>();
        List<CheckSheetWillBeVO>CheckSheetWillBeVOList=new ArrayList<>();
        List<GoodPartVo> GoodPartVoList=new ArrayList<>();
        //查询出所有的已付费没有生成待检测单的单子
        guaranteeDepositService
                .searchOrderIds()
                    .forEach((OrderId)->deliveryOrderList
                        .add(deliveryOrderService
                                .searchDeliveryOrderById(OrderId)));
        if(deliveryOrderList.size()==0){
            log.info("当前没有需要变成待检测单的单子");
            return null;
        }
        for(DeliveryOrder deliveryOrder:deliveryOrderList){
           for(VegetableDeliveryRelation vegetableDeliveryRelation: vegetableDeliveryRelationService.searchAllVDRByOrderId(deliveryOrder.getId())){
               GoodPartVoList.add( GoodPartVo
                       .builder()
                       .weight(null)//载重
                       .goodName(vegetableDeliveryRelation.getVegetableName())
                       .build());
            }
            CheckSheetWillBeVOList.add(CheckSheetWillBeVO
                    .builder()
                    .carPlateNO(deliveryOrder.getDriverPlateNumber())
                    .arrivalTime(deliveryOrder.getActualArrivalTime())
                    .deliveryOrderId(deliveryOrder.getId().toString())
                    .location(deliveryOrder.getStartAddr())
                    .companyName(merchantCompanyService.searchNameById(deliveryOrder.getReceiverCompanyId()))//用收货人公司id，查出公司名字
                    .responsebilePerson(CompanyUserRelationService.searchResponsibleByComId(deliveryOrder.getReceiverCompanyId()))//用收货人公司Id查出负责人
                    .goodPartVo(GoodPartVoList)
                    .build());
            GoodPartVoList=new ArrayList<>();
            // 把guarantee_deposit表is_generate_quality值为1
           // guaranteeDepositService.updateIsGenerateQuality(deliveryOrder.getId());
        }
      //创建检测单
       if(CheckSheetWillBeVOList.size()==0){
           log.info("创建待检测单失败");
       }
        for(CheckSheetWillBeVO checkSheetWillBeVO:CheckSheetWillBeVOList){
            for(GoodPartVo goodPartVo:checkSheetWillBeVO.getGoodPartVo()) {
                Date arrivalTime=checkSheetWillBeVO.getArrivalTime();
                String arrivalStr="";
                if(arrivalTime!=null){
                    arrivalStr=arrivalTime.toString();
                }
                CheckSheetVO checkSheetVO=CheckSheetVO.builder()
                            .arrivalTime(arrivalStr)
                            .carPlateNO(checkSheetWillBeVO.getCarPlateNO())
                            .companyName(checkSheetWillBeVO.getCompanyName())
                            .deliveryOrderId(checkSheetWillBeVO.getDeliveryOrderId().toString())
                            .goodName(goodPartVo.getGoodName())
                            .location(null)
                            .responsebilePerson(checkSheetWillBeVO.getResponsebilePerson())
                            .userId(null)
                            .weight(goodPartVo.getWeight())
                             .build();
                createQualitySheet(checkSheetVO,null);
            }
        }



        log.info("创建待检测单成功");
      return null;
    }

}
