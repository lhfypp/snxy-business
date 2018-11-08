package com.snxy.business.service;

import com.snxy.business.domain.CompanyUserRelation;







import java.util.List;

public interface CompanyUserRelationService {
    void insertCompanyUserRelation(CompanyUserRelation companyUserRelation);

    void insertCompanyRelation(CompanyUserRelation companyUserRelation);

    CompanyUserRelation selectCompanyUserRelation(Long id);

    void insertCompanyRelationList(List<CompanyUserRelation> companyUserRelationList);

    List<CompanyUserRelation> selectAllByCompanyId(Long companyId);

    void updateIsResponsible(List<CompanyUserRelation> companyUserRelationList);
    int insert(CompanyUserRelation record);
}
