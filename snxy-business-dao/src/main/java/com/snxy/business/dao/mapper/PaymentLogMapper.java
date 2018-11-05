package com.snxy.business.dao.mapper;

public interface PaymentLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PaymentLog record);

    int insertSelective(PaymentLog record);

    PaymentLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PaymentLog record);

    int updateByPrimaryKey(PaymentLog record);
}