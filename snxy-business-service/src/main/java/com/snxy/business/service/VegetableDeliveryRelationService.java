package com.snxy.business.service;

import com.snxy.business.domain.Goods;
import com.snxy.business.domain.VegetableDeliveryRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VegetableDeliveryRelationService {
	void insertGoodList(List<VegetableDeliveryRelation> vegetableDeliveryRelationList);
    List<Goods> selectAllByOrderId(Long deliveryOrderId);
    List<Goods> searchbyOrderId(long orderId);
}
