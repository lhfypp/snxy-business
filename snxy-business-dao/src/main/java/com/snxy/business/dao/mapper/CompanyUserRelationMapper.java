package com.snxy.business.dao.mapper;

import com.snxy.business.domain.CompanyUserRelation;


import com.snxy.business.domain.MerchantCompany;
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

    void insertCompanyRelationList(List<CompanyUserRelation> companyUserRelationList);

    List<CompanyUserRelation> selectAllByCompanyId(Long companyId);

    void updateByCompanyUserRelationList(@Param("companyUserRelationList") List<CompanyUserRelation> companyUserRelationList);

    List<CompanyUserRelation> selectUserRelationByOnlineUserIdList(@Param("onlineUserIdList") List<Long> onlineUserIdList);

    CompanyUserRelation selectByCompanyId(Long companyId);

    MerchantCompany selectBossCompanyByUserId(@Param("onlineUserId") Long onlineUserId );

    List<Long> selectCompanyIdByOnlineUserId(Long onlineUserId);

    void deleteCompanyUserRelationByOnlineUserId(Long onlineUserId);

    MerchantCompany selectEmployCompanyByUserId(Long onlineUserId);

    String selectUserNameByOlineUserId(Long onlineUserId);

    String selectPhoneByOnlineUserId(Long onlineUserId);
}