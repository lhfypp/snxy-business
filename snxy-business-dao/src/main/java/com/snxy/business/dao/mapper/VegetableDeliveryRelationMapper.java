package com.snxy.business.dao.mapper;

public interface VegetableDeliveryRelationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(VegetableDeliveryRelation record);

    int insertSelective(VegetableDeliveryRelation record);

    VegetableDeliveryRelation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VegetableDeliveryRelation record);

    int updateByPrimaryKey(VegetableDeliveryRelation record);
}