package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.VegetableDeliveryRelationMapper;
import com.snxy.business.domain.VegetableDeliveryRelation;
import com.snxy.business.service.VegetableDeliveryRelationService;
import com.snxy.business.service.vo.GoodsVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VegetableDeliveryRelationServiceImpl implements VegetableDeliveryRelationService {
    @Resource
    private VegetableDeliveryRelationMapper vegetableDeliveryRelationMapper;

    @Override
    public List<VegetableDeliveryRelation> searchAllVDRByOrderId(Long id) {

        return vegetableDeliveryRelationMapper.selectVDRByOrderId(id);

    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertGoodsVOList(List<GoodsVO> goodsVOList,Long deliveryOrderId) {
        List<VegetableDeliveryRelation> vegetableDeliveryRelationList = goodsVOList.parallelStream().map(s -> VegetableDeliveryRelation.builder()
                                      .deliveryOrderId(deliveryOrderId)
                                      .vegetableName(s.getCategoryName())
                                      .vegetableId(s.getEntranceFeeCategoryId())
                                      .gmtCreate(new Date())
                                      .loadStatus(s.getLoadStatus())
                                      .build())
                                      .collect(Collectors.toList());
        vegetableDeliveryRelationMapper.insertGoodsVOList(vegetableDeliveryRelationList);

    }
}
