package com.snxy.business.dao.mapper;

import com.snxy.business.domain.DeliveryCompany;

public interface DeliveryCompanyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DeliveryCompany record);

    int insertSelective(DeliveryCompany record);

    DeliveryCompany selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DeliveryCompany record);

    int updateByPrimaryKey(DeliveryCompany record);
}