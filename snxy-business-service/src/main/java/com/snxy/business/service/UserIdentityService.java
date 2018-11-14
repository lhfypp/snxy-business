package com.snxy.business.service;

import com.snxy.business.domain.UserIdentity;

import java.util.List;

public interface UserIdentityService
{
    void insertIdentity(UserIdentity userIdentity);

    void insertIdentityList(List<UserIdentity> userIdentityList);

    UserIdentity selectByOnlineUserId(Long onlineUserId,Integer identityId);

    void updateIdentityByOnlineUserId(Long onlineUserId, Integer identityId);
}
