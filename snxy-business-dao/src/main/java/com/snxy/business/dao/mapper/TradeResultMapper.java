package com.snxy.business.dao.mapper;

import com.snxy.business.domain.TradeResult;

public interface TradeResultMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TradeResult record);

    int insertSelective(TradeResult record);

    TradeResult selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TradeResult record);

    int updateByPrimaryKey(TradeResult record);
}