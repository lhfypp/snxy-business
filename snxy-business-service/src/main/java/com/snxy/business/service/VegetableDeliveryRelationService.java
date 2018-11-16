package com.snxy.business.service;

import com.snxy.business.domain.VegetableDeliveryRelation;
import com.snxy.business.service.vo.Goods;

import com.snxy.business.service.vo.GoodsVO;
import com.snxy.business.service.vo.SystemUserVO;


import java.util.List;

public interface VegetableDeliveryRelationService {

    List<VegetableDeliveryRelation> searchAllVDRByOrderId(Long id);

    void insertGoodsVOList(List<GoodsVO> goodsVOList,Long deliveryOrderId);

    List<Goods> selectByDeliveryId(SystemUserVO systemUserVO);

    List<VegetableDeliveryRelation> selectGoodsByDeliveryId(Long deliveryOrderId);
}
