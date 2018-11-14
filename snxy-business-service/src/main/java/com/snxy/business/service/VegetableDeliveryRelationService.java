package com.snxy.business.service;

import com.snxy.business.service.vo.GoodsVO;

import java.util.List;

public interface VegetableDeliveryRelationService {
    void insertGoodsVOList(List<GoodsVO> goodsVOList,Long deliveryOrderId);
}
