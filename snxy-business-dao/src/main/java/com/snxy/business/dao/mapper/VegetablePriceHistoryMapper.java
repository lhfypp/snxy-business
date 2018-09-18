package com.snxy.business.dao.mapper;

import com.snxy.business.domain.VegetablePriceHistory;

public interface VegetablePriceHistoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(VegetablePriceHistory record);

    int insertSelective(VegetablePriceHistory record);

    VegetablePriceHistory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VegetablePriceHistory record);

    int updateByPrimaryKey(VegetablePriceHistory record);
}