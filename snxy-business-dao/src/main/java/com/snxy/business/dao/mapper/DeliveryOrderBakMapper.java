package com.snxy.business.dao.mapper;

import com.snxy.business.domain.DeliveryOrderBak;

public interface DeliveryOrderBakMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DeliveryOrderBak record);

    int insertSelective(DeliveryOrderBak record);

    DeliveryOrderBak selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DeliveryOrderBak record);

    int updateByPrimaryKey(DeliveryOrderBak record);
}