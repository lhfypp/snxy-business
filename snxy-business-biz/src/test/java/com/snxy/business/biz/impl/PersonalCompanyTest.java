package com.snxy.business.biz.impl;

import com.snxy.business.domain.DeliveryOrder;
import com.snxy.business.service.DeliveryOrderService;
import com.snxy.common.util.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PersonalCompanyTest {

    @Resource
    private DeliveryOrderService deliveryOrderService;

    @Test
    public void personalCompanyTest(){
        PageInfo<DeliveryOrder> list = deliveryOrderService.selectByCreatorId(1L,2L);
       /* for (DeliveryOrder deliveryOrder:list){
            System.out.println(deliveryOrder.getCreatorId());
        }*/
    }
}
