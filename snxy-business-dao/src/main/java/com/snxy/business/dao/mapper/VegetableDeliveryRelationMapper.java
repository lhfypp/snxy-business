package com.snxy.business.dao.mapper;

import com.snxy.business.domain.GoodsInfo;
import com.snxy.business.domain.VegetableDeliveryRelation;

import java.util.List;

public interface VegetableDeliveryRelationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(VegetableDeliveryRelation record);

    int insertSelective(VegetableDeliveryRelation record);

    VegetableDeliveryRelation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VegetableDeliveryRelation record);

    int updateByPrimaryKey(VegetableDeliveryRelation record);

    List<GoodsInfo> selectByOrderId(Long orderId);
}