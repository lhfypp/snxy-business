package com.snxy.business.dao.mapper;

public interface DeptMapper {
    int deleteByPrimaryKey(Integer sid);

    int insert(Dept record);

    int insertSelective(Dept record);

    Dept selectByPrimaryKey(Integer sid);

    int updateByPrimaryKeySelective(Dept record);

    int updateByPrimaryKey(Dept record);
}