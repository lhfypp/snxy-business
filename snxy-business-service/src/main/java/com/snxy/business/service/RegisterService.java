package com.snxy.business.service;


public interface RegisterService {

    void chooseIdentity(Long systemUserId, String name, Integer identityId, Long onlineUserId);

    void newPassword(String password, Long systemUserId);
}
