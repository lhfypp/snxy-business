package com.snxy.business.dao.mapper;

import com.snxy.business.domain.ShortMessage;

public interface ShortMessageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ShortMessage record);

    int insertSelective(ShortMessage record);

    ShortMessage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ShortMessage record);

    int updateByPrimaryKey(ShortMessage record);
}