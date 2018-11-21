package com.snxy.business.dao.mapper;

import com.snxy.business.domain.OutFeeOrder;

public interface OutFeeOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OutFeeOrder record);

    int insertSelective(OutFeeOrder record);

    OutFeeOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OutFeeOrder record);

    int updateByPrimaryKey(OutFeeOrder record);
}