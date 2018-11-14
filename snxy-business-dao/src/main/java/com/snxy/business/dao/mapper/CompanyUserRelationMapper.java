package com.snxy.business.dao.mapper;

import com.snxy.business.domain.CompanyUserRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CompanyUserRelationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CompanyUserRelation record);

    int insertSelective(CompanyUserRelation record);

    CompanyUserRelation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CompanyUserRelation record);

    int updateByPrimaryKey(CompanyUserRelation record);

    void insertCompanyRelation(CompanyUserRelation companyUserRelation);

    CompanyUserRelation selectByOnlineUserId(Long id);

    void insertCompanyRelationList(List<CompanyUserRelation> list);

    List<CompanyUserRelation> selectAllByCompanyId(Long companyId);

    void updateByCompanyUserRelationList(@Param("companyUserRelationList") List<CompanyUserRelation> companyUserRelationList);

    List<CompanyUserRelation> selectUserRelationByOnlineUserIdList(@Param("onlineUserIdList") List<Long> onlineUserIdList);

    CompanyUserRelation selectByCompanyId(Long companyId);
}