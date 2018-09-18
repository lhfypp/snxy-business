package com.snxy.business.dao.mapper;

import com.snxy.business.domain.CurrOrderReceiver;

public interface CurrOrderReceiverMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CurrOrderReceiver record);

    int insertSelective(CurrOrderReceiver record);

    CurrOrderReceiver selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CurrOrderReceiver record);

    int updateByPrimaryKey(CurrOrderReceiver record);
}