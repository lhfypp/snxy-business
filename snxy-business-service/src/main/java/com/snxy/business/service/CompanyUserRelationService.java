package com.snxy.business.service;

import com.snxy.business.domain.CompanyUserRelation;
import com.snxy.business.service.vo.ChangePrincipleVo;
import com.snxy.business.service.vo.CompanyUserListVo;

import java.util.List;

public interface CompanyUserRelationService {
    Long selectCompanyIdByOnlineUserId(Long onlineUserId);
    Long selectByOnlineId(Long id);
    long selectCompanyId(Long onlineUserId);
    List<Long>selectOnlinUserId(Long companyId);

    void changePrinciple(ChangePrincipleVo changePrincipleVo);

    void insertCompany(CompanyUserRelation companyUserRelation);

    List<CompanyUserListVo> showEmployeeList(Long companyId);

    void newEmployee(CompanyUserListVo companyUserListVo);

    void updateCompanyUser(CompanyUserListVo companyUserListVo);

    void deleteEmployee(Long onlineUserId);
}
