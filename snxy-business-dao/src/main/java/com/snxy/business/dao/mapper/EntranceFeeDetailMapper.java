package com.snxy.business.dao.mapper;

import com.snxy.business.domain.EntranceFeeDetail;

import java.math.BigDecimal;

public interface EntranceFeeDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(EntranceFeeDetail record);

    int insertSelective(EntranceFeeDetail record);

    EntranceFeeDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(EntranceFeeDetail record);

    int updateByPrimaryKey(EntranceFeeDetail record);

    BigDecimal selectPriceById(Long entranceFeeCapacityId, Long entranceFeeCategoryId);
}