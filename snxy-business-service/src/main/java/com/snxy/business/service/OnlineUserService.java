package com.snxy.business.service;

public interface OnlineUserService {

    void updateName(Long systemUserId, String name);

    Long selectOnlineUserIdBySystemUserId(Long systemUserId);
}
