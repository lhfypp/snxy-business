package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.CurrOrderReceiverMapper;
import com.snxy.business.dao.mapper.DeliveryOrderMapper;
import com.snxy.business.dao.mapper.QualitySheetMapper;
import com.snxy.business.domain.BillInfoDetail;
import com.snxy.business.domain.CheckBillInfo;
import com.snxy.business.domain.CreateCheckBillVO;
import com.snxy.business.domain.QualitySheet;
import com.snxy.business.service.ManagementService;
import com.snxy.business.service.QualitySheetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


import java.util.Date;
import java.util.List;


@Service
@Slf4j
public class ManagementServiceImpl implements ManagementService {
    @Resource
    private QualitySheetService qualitySheetService;
    @Resource
    DeliveryOrderMapper deliveryOrderMapper;
    @Resource
    private QualitySheetMapper qualitySheetMapper;
    @Resource
    private CurrOrderReceiverMapper currOrderReceiverMapper;

    @Override
    public void save(CreateCheckBillVO createcCheckBillVO) {

       long orderId= Long.parseLong(createcCheckBillVO.getOrderId());
       BillInfoDetail billInfoDetail= deliveryOrderMapper.selectBydDeliveryOrderId(createcCheckBillVO.getOrderId());
       int goodNumber=billInfoDetail.getGoods().size();//货物的数量
       // vegetableDeliveryRelationMapper
        Date now=new Date();//日期格式待定
       // SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd hh:mm:ss");
        //sdf.parse(sdf.format(now));
        Long userid=null;
        QualitySheet qualitySheet=
                     QualitySheet.builder()
                                 .deliveryOrderId(orderId)
                                 .proposer(userid)//提检人Id 货车司机id
                                 .checkerId(userid)//检验员 暂时在平台校验
                                 .vehiclePlateNumber(createcCheckBillVO.getPlatNumber())//车牌号
                                 .productionLocation(createcCheckBillVO.getOrigin())//产地
                                 .checkTime(now)//检测时间 当前时间
                                 .url("")//检测图片url,暂时为空
                                 .qrcodeUrl(billInfoDetail.getDeliveryOrder().getQrcodeUrl())//二维码
                                 .qualified(1)//检测结果1.合格，2.不合格
                                 .remark(createcCheckBillVO.getDesc())//备注
                                 .weight(Float.parseFloat(createcCheckBillVO.getTheNumber()))//重量
                                 //.vegetableCategoryId()//货品id，暂时不处理
                                 .isDelete((byte)0)//未删除
                                 .build();

        qualitySheetService.insert(qualitySheet);
    }
    //平台校验司机传过来的检测单是否正确
    public int CheckBill(CreateCheckBillVO createcCheckBillVO){
        BillInfoDetail billInfoDetail= deliveryOrderMapper.selectBydDeliveryOrderId(createcCheckBillVO.getOrderId());//根据订单号与司机姓名进行校验，如果都正确，合格，价格校验 一个产品20
        return 1;
    }

    //根据手机号查询检测单列表
    @Override
    public List<CheckBillInfo> selectCheckBillList(String driverMobile) {
        List<Long> list = currOrderReceiverMapper.selectDriverMobile(driverMobile);
        List<Long> currList = currOrderReceiverMapper.selectCurrList(list);
        List<CheckBillInfo> listCheckBillInfo = qualitySheetMapper.selectCheckBillList(currList);
        return listCheckBillInfo;
    }

    //检测单详情
    @Override
    public CheckBillInfo selectCheckBillByUserId(String deliveryOrderId) {
        CheckBillInfo checkBillInfo = qualitySheetMapper.selectCheckBillById(deliveryOrderId);
        return checkBillInfo;
    }

    //模糊查询
    @Override
    public List selectVague(String vegetableCategoryName) {
        List<CheckBillInfo> listByName = qualitySheetMapper.selectCheckBillByName(vegetableCategoryName);
        return listByName;
    }
}
