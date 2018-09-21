package com.snxy.business.service;


import java.util.List;

public interface UserIdentityService {
    void setUserIdentity(Long onlineUserId,List<Integer> identities);

}
