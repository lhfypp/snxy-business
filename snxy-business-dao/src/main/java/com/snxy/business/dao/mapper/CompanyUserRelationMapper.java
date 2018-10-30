package com.snxy.business.dao.mapper;

import com.snxy.business.domain.CompanyUserRelation;

import java.util.List;

public interface    CompanyUserRelationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CompanyUserRelation record);

    int insertSelective(CompanyUserRelation record);

    CompanyUserRelation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CompanyUserRelation record);

    int updateByPrimaryKey(CompanyUserRelation record);

    long selectCompanyId(Long onlineUserId);
    List<Long> selectOnlinUserId(Long companyId);
	List<Long> selectCompanyByOnlineId(Long id);
}