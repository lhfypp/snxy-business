package com.snxy.business.service;

import com.snxy.business.domain.CompanyUserRelation;
import com.snxy.business.domain.MerchantCompany;
import com.snxy.business.domain.OnlineUser;


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

    Long selectCompanyRelationforId(Long userId,long companyId);
    String searchResponsibleByComId(Long companyId);
    String searchComIdByUseId(Long userId);


    MerchantCompany selectBossCompanyByUserId(Long onlineUserId);

    void deleteCompanyUserRelationByOnlineUserId(Long onlineUserId);

    MerchantCompany selectEmployCompanyByUserId(Long onlineUserId);

    List<Long> selectCompanyIdByOnlineUserId(Long onlineUserId);

    CompanyUserRelation selectByOnlineUserId(Long onlineUserId);

    OnlineUser selectUserNameByOlineUserId(Long onlineUserId);

}
