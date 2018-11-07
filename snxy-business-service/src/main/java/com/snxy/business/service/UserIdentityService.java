package com.snxy.business.service;

import com.snxy.business.domain.UserIdentity;

import java.util.List;

public interface UserIdentityService
{
    void insertIdentity(UserIdentity userIdentity);

    void insertIdentityList(List<UserIdentity> userIdentityList);
}
