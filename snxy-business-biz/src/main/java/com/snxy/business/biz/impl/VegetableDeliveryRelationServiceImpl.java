package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.VegetableDeliveryRelationMapper;
import com.snxy.business.domain.Goods;
import com.snxy.business.domain.VegetableDeliveryRelation;
import com.snxy.business.service.VegetableDeliveryRelationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class VegetableDeliveryRelationServiceImpl implements VegetableDeliveryRelationService {

    @Resource
    private VegetableDeliveryRelationMapper vegetableDeliveryRelationMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertGoodList(List<VegetableDeliveryRelation> vegetableDeliveryRelationList) {
        vegetableDeliveryRelationMapper.insertGoodList(vegetableDeliveryRelationList);
    }

    @Override
    public List<Goods> selectAllByOrderId(Long deliveryOrderId) {
        List<Goods> goodsList = vegetableDeliveryRelationMapper.selectAllByOrderId(deliveryOrderId);
        return goodsList;
    }

    @Override
    public List<Goods> searchbyOrderId(long orderId) {
        vegetableDeliveryRelationMapper.selectByOrderId(orderId);
        return null;
    }
    @Override
   public  void updatebyAgent(List<Goods> goods){

    }
}
