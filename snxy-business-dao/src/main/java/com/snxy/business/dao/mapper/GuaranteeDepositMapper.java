package com.snxy.business.dao.mapper;

import com.snxy.business.domain.GuaranteeDeposit;

public interface GuaranteeDepositMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GuaranteeDeposit record);

    int insertSelective(GuaranteeDeposit record);

    GuaranteeDeposit selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GuaranteeDeposit record);

    int updateByPrimaryKey(GuaranteeDeposit record);
}