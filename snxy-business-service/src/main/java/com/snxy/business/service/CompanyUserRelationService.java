package com.snxy.business.service;

import java.util.List;

public interface CompanyUserRelationService {
    List selectCompanyIsExist(Long onlineUserId);
    List selectByOnlineId(Long id);
    long selectCompanyId(Long onlineUserId);
    List<Long>selectOnlinUserId(Long companyId);
}
