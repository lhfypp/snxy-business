package com.snxy.business.dao.mapper;

import com.snxy.business.domain.OutFeeDetail;

public interface OutFeeDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OutFeeDetail record);

    int insertSelective(OutFeeDetail record);

    OutFeeDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OutFeeDetail record);

    int updateByPrimaryKey(OutFeeDetail record);
}