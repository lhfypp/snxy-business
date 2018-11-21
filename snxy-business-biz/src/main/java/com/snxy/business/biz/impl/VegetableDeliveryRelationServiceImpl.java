package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.VegetableDeliveryRelationMapper;
import com.snxy.business.domain.*;
import com.snxy.business.service.*;
import com.snxy.business.service.vo.Goods;
import com.snxy.business.service.vo.GoodsVO;
import com.snxy.business.service.vo.SystemUserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VegetableDeliveryRelationServiceImpl implements VegetableDeliveryRelationService {
    @Resource
    private VegetableDeliveryRelationMapper vegetableDeliveryRelationMapper;
    @Resource
    private CompanyUserRelationService companyUserRelationService;
    @Resource
    private DeliveryOrderService deliveryOrderService;
    @Resource
    private VegetableService vegetableService;

    @Override
    public List<VegetableDeliveryRelation> searchAllVDRByOrderId(Long id) {

        return vegetableDeliveryRelationMapper.selectVDRByOrderId(id);

    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertGoodsVOList(List<GoodsVO> goodsVOList,Long deliveryOrderId) {
        List<VegetableDeliveryRelation> vegetableDeliveryRelationList = goodsVOList.parallelStream().map(s -> VegetableDeliveryRelation.builder()
                                      .deliveryOrderId(deliveryOrderId)
                                      .vegetableName(s.getVegetableName())
                                      .vegetableId(s.getVegetableId())
                                      .gmtCreate(new Date())
                                      .loadStatus(s.getLoadStatus())
                                      .vegetablePrice(s.getVegetablePrice())
                                      .vegetableWeight(s.getVegetableWeight())
                                      .build())
                                      .collect(Collectors.toList());
        vegetableDeliveryRelationMapper.insertGoodsVOList(vegetableDeliveryRelationList);

    }

    @Override
    public List<Goods> selectByDeliveryId(SystemUserVO systemUserVO) {
        CompanyUserRelation companyUserRelation = companyUserRelationService.selectByOnlineUserId(systemUserVO.getOnlineUserId());
        Long companyId = companyUserRelation.getCompanyId();
        List<DeliveryOrder> deliveryOrderList = deliveryOrderService.selectByCompanyId(companyId);
        List<Long> deliveryOrderIdList = deliveryOrderList.parallelStream().map(DeliveryOrder::getId).collect(Collectors.toList());
        List<VegetableDeliveryRelation> vegetableDeliveryRelationList = vegetableDeliveryRelationMapper.selectByDeliveryOrderIdList(deliveryOrderIdList);
        List<Vegetable> vegetableList = vegetableService.selectAll();
        Map<Long,String> idUrlMap = vegetableList.parallelStream().collect(Collectors.toMap(Vegetable::getId,Vegetable::getImageUrl));

        List<Goods> goodsList = vegetableDeliveryRelationList.parallelStream().map(s -> Goods.builder()
                                     .goodsId(s.getVegetableId())
                                     .goodsName(s.getVegetableName())
                                     .goodsImg(idUrlMap.get(s.getVegetableId()))
                                     .build())
                                     .collect(Collectors.toList());
        return goodsList;
    }

    @Override
    public List<VegetableDeliveryRelation> selectGoodsByDeliveryId(Long deliveryOrderId) {
        List<VegetableDeliveryRelation> vegetableDeliveryRelationList = vegetableDeliveryRelationMapper.selectGoodsByDeliveryOrderId(deliveryOrderId);
        return vegetableDeliveryRelationList;
    }

    @Override
    public List<VegetableDeliveryRelation> selectNameWeightByDeliveryIdList(List<Long> deliveryIdList) {

        return vegetableDeliveryRelationMapper.selectNameWeightByDeliveryIdList(deliveryIdList);
    }

    public List<VegetableDeliveryRelation> selectAll() {
        List<VegetableDeliveryRelation> vegetableDeliveryRelationList = vegetableDeliveryRelationMapper.selectAll();
        return vegetableDeliveryRelationList;
    }
}
