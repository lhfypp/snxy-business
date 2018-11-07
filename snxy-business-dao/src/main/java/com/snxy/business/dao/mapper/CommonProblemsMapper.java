package com.snxy.business.dao.mapper;

import com.snxy.business.domain.CommonProblems;

public interface CommonProblemsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CommonProblems record);

    int insertSelective(CommonProblems record);

    CommonProblems selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CommonProblems record);

    int updateByPrimaryKey(CommonProblems record);
}