package com.snxy.business.dao.mapper;

public interface QualitySheetMapper {
    int deleteByPrimaryKey(Long id);

    int insert(QualitySheet record);

    int insertSelective(QualitySheet record);

    QualitySheet selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(QualitySheet record);

    int updateByPrimaryKey(QualitySheet record);
}