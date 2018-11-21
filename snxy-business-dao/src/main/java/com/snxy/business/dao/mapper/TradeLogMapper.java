package com.snxy.business.dao.mapper;

import com.snxy.business.domain.TradeLog;

public interface TradeLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TradeLog record);

    int insertSelective(TradeLog record);

    TradeLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TradeLog record);

    int updateByPrimaryKey(TradeLog record);
}