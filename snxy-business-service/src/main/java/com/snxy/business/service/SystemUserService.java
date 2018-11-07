package com.snxy.business.service;

public interface SystemUserService {

    void updateName(Long systemUserId, String name);
//修改系统用户的手机号
    void updateSystemMobile(Long systemUserId, String newMobile);
}
