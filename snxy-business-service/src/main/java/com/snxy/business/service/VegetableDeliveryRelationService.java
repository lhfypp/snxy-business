package com.snxy.business.service;

import com.snxy.business.domain.VegetableDeliveryRelation;

import java.util.List;

public interface VegetableDeliveryRelationService {
    List<VegetableDeliveryRelation> searchAllVDRByOrderId(Long id);
}
