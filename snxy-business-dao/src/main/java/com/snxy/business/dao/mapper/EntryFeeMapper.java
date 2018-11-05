package com.snxy.business.dao.mapper;

public interface EntryFeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(EntryFee record);

    int insertSelective(EntryFee record);

    EntryFee selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(EntryFee record);

    int updateByPrimaryKey(EntryFee record);
}