package com.snxy.business.service;

import com.snxy.business.domain.TruckType;
import com.snxy.business.service.vo.TruckVo;

import java.util.List;

public interface TruckTypeService {
    List<TruckVo> selectTruckType();
    TruckType selectTruckTypeByTruckId(long id);
}
