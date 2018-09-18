package com.snxy.business.dao.mapper;

import com.snxy.business.domain.EntryFee;

public interface EntryFeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(EntryFee record);

    int insertSelective(EntryFee record);

    EntryFee selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(EntryFee record);

    int updateByPrimaryKey(EntryFee record);
}