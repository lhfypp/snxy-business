package com.snxy.business.service;

import com.snxy.business.domain.SystemUser;

import java.util.Date;
import java.util.List;

public interface SystemUserService {

    void updateName(Long systemUserId, String name);

    void insertSystemUser(SystemUser systemUser);

    void insertSystemUserList(List<SystemUser> systemUserList);

    void updateSystemMobile(Long systemUserId, String newMobile);

    SystemUser selectByMobile(String phone);

    void updatePersonalPassWord(String oldPwd, String newPwd, Long systemUserId);

    void updatePassword(String password, Long systemUserId);

    void updatePersonalMobile(Long systemUserId, String newMobile, String smsCode);

    void updateRegisterPWD(Long systemUserId, String newPwd);

    void isTruePwd(Long systemUserId,String password);

    String getSmsCode(String newMobile);
}
