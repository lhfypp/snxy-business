package com.snxy.business.service;



import com.snxy.business.domain.Identy;
import com.snxy.business.domain.UserIdentity;

import java.util.List;

public interface UserIdentityService {
    void setUserIdentity(Long onlineUserId,List<Integer> identities);

    List<Identy> selectUserIdentyByOnlineUserIdList(List<Long> onlineUserIdList);

    void insertUserIdentyList(List<UserIdentity> userIdentityList);

    void updateByOnlineUserId(Long onlineUserId, byte isDelete);
}
