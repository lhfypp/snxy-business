package com.snxy.business.service;

import com.snxy.business.domain.OnlineUser;

import java.util.List;


public interface OnlineUserService {
    boolean newOnlineUser(OnlineUser onlineUser);
    OnlineUser selectById(Long onlineUserId);
    List<String> searchphones(List<Long> onlineUserIds);

}
