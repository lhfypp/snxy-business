package com.snxy.business.service;

import com.snxy.business.domain.CompanyUserRelation;
import com.snxy.business.domain.MerchantCompany;
import com.snxy.business.service.vo.PersonalVO;


import java.util.List;

public interface CompanyUserRelationService {
    void insertCompanyUserRelation(CompanyUserRelation companyUserRelation);

    void insertCompanyRelation(CompanyUserRelation companyUserRelation);

    CompanyUserRelation selectCompanyUserRelation(Long id);

    void insertCompanyRelationList(List<CompanyUserRelation> companyUserRelationList);

    List<CompanyUserRelation> selectAllByCompanyId(Long companyId);

    void updateIsResponsible(List<CompanyUserRelation> companyUserRelationList);

    List<CompanyUserRelation> selectUserRelationByOnlineUserIdList(List<Long> onlineUserIdList);

    CompanyUserRelation selectUserRelationByCompanyId(Long companyId);

    int insert(CompanyUserRelation record);

    MerchantCompany selectBossCompanyByUserId(Long onlineUserId);

    void deleteCompanyUserRelationByOnlineUserId(Long onlineUserId);

    MerchantCompany selectEmployCompanyByUserId(Long onlineUserId);

    List<Long> selectCompanyIdByOnlineUserId(Long onlineUserId);

    PersonalVO selectPersonalByOnlineUserId(Long systemUserId);




}
