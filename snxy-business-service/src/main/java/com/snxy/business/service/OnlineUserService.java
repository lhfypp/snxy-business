package com.snxy.business.service;

public interface OnlineUserService {

    Long selectOnlineIdBySystemId(Long systemUserId);

    void updateNameById(Long onlineUserId, String userName);
}
