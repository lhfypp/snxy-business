package com.snxy.business.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.snxy.business.domain.Goods;
import com.snxy.business.service.vo.DeliveryOrderVo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        Goods goods1 = Goods.builder()
                .goodsId(1L)
                .goodsName("火龙果")
                .goodsPrice(new BigDecimal(15.5))
                .goodsWeight(20)
                .build();
        Goods goods2 = Goods.builder()
                .goodsId(2L)
                .goodsName("猕猴桃")
                .goodsPrice(new BigDecimal(5.5))
                .goodsWeight(50)
                .build();
        List<Goods> goodsList = new ArrayList<>();
        goodsList.add(goods1);
        goodsList.add(goods2);

        DeliveryOrderVo deliveryOrderVo = DeliveryOrderVo.builder()
        .orderNo("FH20181025162513000005")
        .senderName("张三")
        .senderMobile("11111111111")
        .startAddr("北京")
        .receiverName("李四")
        .receiverMobile("22222222222")
        .receiverCompany("腾讯")
        .endAddr("新发地")
        .goodsList(goodsList)
        .truckTypeId(2)
        .deliveryFee(new BigDecimal(66.6))
        .driverName("王五")
        .driverMobile("333333333333")
        .distance(666.6f)
        .sendTime(new Date())
        .estArrivalTime(new Date())
        .build();

        String s = JSON.toJSONString(deliveryOrderVo);
        System.out.println(s);
    }
}
