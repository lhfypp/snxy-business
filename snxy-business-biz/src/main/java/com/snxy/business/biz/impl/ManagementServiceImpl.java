package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.DeliveryOrderMapper;
import com.snxy.business.dao.mapper.QualitySheetMapper;
import com.snxy.business.dao.mapper.VegetableDeliveryRelationMapper;
import com.snxy.business.domain.BillInfoDetail;
import com.snxy.business.domain.CreateCheckBillVO;
import com.snxy.business.domain.QualitySheet;
import com.snxy.business.service.ManagementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
@Slf4j
public class ManagementServiceImpl implements ManagementService {
    @Resource
    private QualitySheetMapper qualitySheetMapper;
    @Resource
    DeliveryOrderMapper deliveryOrderMapper;
    @Resource
    VegetableDeliveryRelationMapper  vegetableDeliveryRelationMapper;
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
        QualitySheet qualitySheet=new QualitySheet();
        qualitySheet.setDeliveryOrderId(orderId);//货运单  id
        qualitySheet.setProposer(userid);//提检人Id 货车司机id
        qualitySheet.setCheckerId(userid);//检验员 暂时在平台校验
        qualitySheet.setVehiclePlateNumber(createcCheckBillVO.getPlatNumber());//车牌号
        qualitySheet.setProductionLocation(createcCheckBillVO.getOrigin());//产地
        qualitySheet.setCheckTime(now);//检测时间 当前时间
        qualitySheet.setUrl("");//检测图片url,暂时为空
        qualitySheet.setQrcodeUrl(billInfoDetail.getQrcodeUrl());//检测二维码
        qualitySheet.setQualified(1);//检测结果1.合格，2.不合格
        qualitySheet.setRemark(createcCheckBillVO.getDesc());//备注
        qualitySheet.setWeight(Float.parseFloat(createcCheckBillVO.getTheNumber()));//重量
        //qualitySheet.setVegetableCategoryId();//货品id,暂时不处理
        qualitySheet.setIsDelete((byte)0);//未删除
        qualitySheetMapper.insert(qualitySheet);
    }
    //平台校验司机传过来的检测单是否正确
    public int CheckBill(CreateCheckBillVO createcCheckBillVO){
        BillInfoDetail billInfoDetail= deliveryOrderMapper.selectBydDeliveryOrderId(createcCheckBillVO.getOrderId());//根据订单号与司机姓名进行校验，如果都正确，合格，价格校验 一个产品20


        return 1;
    }
    public static void  get(){
        Date date=new Date();
        DateFormat format = new SimpleDateFormat("yyyyMMdd");
        String time = format.format(date);
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if (hashCodeV < 0) {//有可能是负数
            hashCodeV = -hashCodeV;
        }
        System.out.println(time + String.format("%011d", hashCodeV));
    }

    public static void main(String[] args) {
        get();

    }
}
