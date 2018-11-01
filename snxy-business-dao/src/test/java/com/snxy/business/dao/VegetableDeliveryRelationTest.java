package com.snxy.business.dao;


import com.snxy.business.dao.mapper.VegetableDeliveryRelationMapper;
import com.snxy.business.domain.Goods;
import com.snxy.business.domain.VegetableDeliveryRelation;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class VegetableDeliveryRelationTest {

    @Resource
    private VegetableDeliveryRelationMapper vegetableDeliveryRelationMapper;


    @Test
    public void insertTest(){
        VegetableDeliveryRelation vegetableDeliveryRelation = new VegetableDeliveryRelation();
            vegetableDeliveryRelation.setGoodsWeight(100);
            vegetableDeliveryRelation.setDeliveryOrderId(12L);
            vegetableDeliveryRelation.setGoodsName("测试");
            vegetableDeliveryRelation.setGoodsId(2L);
            vegetableDeliveryRelation.setGoodsPrice(new BigDecimal("15.4"));
            vegetableDeliveryRelation.setIsDelete((byte)0);

        vegetableDeliveryRelationMapper.insert(vegetableDeliveryRelation);



    }

    @Test
    public void insertGoodListTest(){
        VegetableDeliveryRelation vegetableDeliveryRelation = VegetableDeliveryRelation.builder()
                .goodsId(1L)
                .deliveryOrderId(1L)
                .goodsName("火龙果")
                .goodsPrice(new BigDecimal(15.5))
                .goodsWeight(20)
                .build();
        VegetableDeliveryRelation vegetableDeliveryRelation2 = VegetableDeliveryRelation.builder()
                .goodsId(2L)
                .deliveryOrderId(2L)
                .goodsName("猕猴桃")
                .goodsPrice(new BigDecimal(5.5))
                .goodsWeight(50)
                .build();
        List<VegetableDeliveryRelation> goodsList = new ArrayList<>();
        goodsList.add(vegetableDeliveryRelation);
      //  goodsList.add(vegetableDeliveryRelation2);

        vegetableDeliveryRelationMapper.insertGoodList(goodsList);

    }



}
