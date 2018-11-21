package com.snxy.business.service;





import com.snxy.business.domain.OnlineUser;

import java.util.List;

public interface OnlineUserService {
    long searchOnlineUserId(String SystemUserId);

    void updateName(Long systemUserId, String name);

    OnlineUser selectByPhone(String phone);

    void insertOnlineUser(OnlineUser onlineUser);

    List<OnlineUser> selectByPhoneList(List<String> phoneList);

    void insertOnlineUserList(List<OnlineUser> onlineUserList);

    List<OnlineUser> selectByOnlineUserIdList(List<Long> onlineUserIdlist);

    String  getSmsCode(String oldMobile);

    void updateOnlineMobile(Long systemUserId ,String oldMobile,String newMobile,String smsCode);

    OnlineUser selectByOnlineUserId(Long onlineUserId);

    Long selectOnlineUserIdBySystemUserId(Long systemUserId);

}
