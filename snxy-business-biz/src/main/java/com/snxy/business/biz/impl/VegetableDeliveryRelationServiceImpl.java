package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.VegetableDeliveryRelationMapper;
import com.snxy.business.domain.VegetableDeliveryRelation;
import com.snxy.business.service.VegetableDeliveryRelationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class VegetableDeliveryRelationServiceImpl implements VegetableDeliveryRelationService {
    @Resource
    private VegetableDeliveryRelationMapper vegetableDeliveryRelationMapper;
    @Override
    public List<VegetableDeliveryRelation> searchAllVDRByOrderId(Long id) {

        return vegetableDeliveryRelationMapper.selectVDRByOrderId(id);
    }
}
