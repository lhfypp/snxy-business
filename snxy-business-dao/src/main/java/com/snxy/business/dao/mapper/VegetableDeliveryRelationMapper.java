package com.snxy.business.dao.mapper;

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

    List<VegetableDeliveryRelation> selectVDRByOrderId(Long orderId);


    void insertGoodsVOList(@Param("vegetableDeliveryRelationList") List<VegetableDeliveryRelation> vegetableDeliveryRelationList);

}