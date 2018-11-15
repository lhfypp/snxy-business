package com.snxy.business.biz.impl;

import com.github.pagehelper.PageHelper;
import com.snxy.business.dao.mapper.CompanyUserRelationMapper;
import com.snxy.business.dao.mapper.DeliveryOrderMapper;
import com.snxy.business.dao.mapper.EntranceFeeDetailMapper;
import com.snxy.business.domain.CompanyUserRelation;
import com.snxy.business.domain.DeliveryOrder;
import com.snxy.business.domain.EntranceFeeDetail;
import com.snxy.business.service.DeliveryOrderService;
import com.snxy.common.util.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
public class DeliveryOrderServiceImpl implements DeliveryOrderService {

    @Resource
    private DeliveryOrderMapper deliveryOrderMapper;
    @Resource
    private CompanyUserRelationMapper companyUserRelationMapper;
    @Resource
    private EntranceFeeDetailMapper entranceFeeDetailMapper;

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

    //计算收费
    @Override
    public String chargeCount(EntranceFeeDetail entranceFeeDetail) {
        String cost  ="";
        BigDecimal num = new BigDecimal("0.5");
        BigDecimal num2 = new BigDecimal("3");
        BigDecimal price = entranceFeeDetailMapper.selectPriceById(entranceFeeDetail);
        if (price.equals(null)){
            return "价格不存在";
        }else {
            if (1 == entranceFeeDetail.getStatus()){
                cost = "当前收费为："+price;
            }else if (2 == entranceFeeDetail.getStatus()){
                cost = "当前收费为："+price.multiply(num);
            }else if (3 == entranceFeeDetail.getStatus()){
                cost = "当前收费为："+price.divide(num2,2,BigDecimal.ROUND_HALF_UP);
            }
            return cost;
        }
    }
}