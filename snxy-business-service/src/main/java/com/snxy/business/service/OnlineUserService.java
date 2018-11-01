package com.snxy.business.service;

import com.snxy.business.domain.OnlineUser;
import com.snxy.business.service.vo.ReceiveMessageVo;

import java.util.List;


public interface OnlineUserService {
    boolean newOnlineUser(OnlineUser onlineUser);
    OnlineUser selectById(Long onlineUserId);
    List<String> searchphones(List onlineUserIds);

    List<OnlineUser> selectByOnlineUserIdList(List<Long> onlineUserIdList);

    OnlineUser selectByPhone(String phone);

    void insertOnlineUser(OnlineUser onlineUser1);

    void updateOnlineUser(OnlineUser onlineUser);

    String selectNameByOnlineUserId(Long onlineUserId);

    ReceiveMessageVo selectByName(String name);
}
