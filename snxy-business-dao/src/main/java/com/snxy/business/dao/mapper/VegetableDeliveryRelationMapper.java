package com.snxy.business.dao.mapper;

import com.snxy.business.domain.Goods;
import com.snxy.business.domain.GoodsInfo;
import com.snxy.business.domain.VegetableDeliveryRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VegetableDeliveryRelationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(VegetableDeliveryRelation record);

    int insertSelective(VegetableDeliveryRelation record);

    VegetableDeliveryRelation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VegetableDeliveryRelation record);

    int updateByPrimaryKey(VegetableDeliveryRelation record);

    List<GoodsInfo> selectByOrderId(Long orderId);

    void insertGoodList(@Param("vegetableDeliveryRelationList")List<VegetableDeliveryRelation> vegetableDeliveryRelationList);

    List<Goods> selectAllByOrderId(Long deliveryOrderId);
}