package com.snxy.business.dao.mapper;

import com.snxy.business.domain.CustomerMessage;

public interface CustomerMessageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CustomerMessage record);

    int insertSelective(CustomerMessage record);

    CustomerMessage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CustomerMessage record);

    int updateByPrimaryKey(CustomerMessage record);
}