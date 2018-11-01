package com.snxy.business.web;

import com.alibaba.fastjson.JSON;
import com.snxy.business.service.vo.VehicleGpsVo;

import java.util.Date;

public class TestGps {
    public static void main(String[] args) {
        VehicleGpsVo vehicleGpsVo = VehicleGpsVo.builder()
                .deliveryOrderId(1L)
                .collectionTime(new Date())
                .height(11.1f)
                .latitude(22.2f)
                .longitude(33.3f)
                .onlineUserId(8L)
                .build();

        String s = JSON.toJSONString(vehicleGpsVo);
        System.out.println(s);
    }
}
