package com.snxy.business.service;

import com.snxy.business.domain.SystemUser;

import java.util.Date;
import java.util.List;

public interface SystemUserService {

    void updateName(Long systemUserId, String name);

    void insertSystemUser(SystemUser systemUser);

    void insertSystemUserList(List<SystemUser> systemUserList);
//修改系统用户的手机号
    void updateSystemMobile(Long systemUserId, String newMobile);
}
