package com.snxy.business.biz.impl;

import com.snxy.business.domain.EntranceFeeDetail;
import com.snxy.business.service.DeliveryOrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DeliveryOrderTest {

    @Resource
    private DeliveryOrderService deliveryOrderService;

    @Test
    public void selectByOrderNoTest(){
        String status = deliveryOrderService.selectByOrderNo("123");
        log.info(status);
    }

 /*   @Test
    public void chargeCountTest(){
        EntranceFeeDetail entranceFeeDetail = new EntranceFeeDetail();
        entranceFeeDetail.setEntranceFeeCapacityId(1L);
        entranceFeeDetail.setEntranceFeeCategoryId(1L);
        entranceFeeDetail.setStatus(3L);
        String cost = deliveryOrderService.chargeCount(entranceFeeDetail);
        log.info(cost);
    }*/
}
