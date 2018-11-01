package com.snxy.business.service;

import com.snxy.business.domain.OnlineUser;
import com.snxy.business.service.vo.ReceiveMessageVo;

import java.util.List;



public interface OnlineUserService {
    boolean newOnlineUser(OnlineUser onlineUser);
    OnlineUser selectById(Long onlineUserId);
<<<<<<< HEAD
    List<String> searchphones(List<Long> onlineUserIds);
    OnlineUser selectBySystemUserId(long systemUserId);
=======
    List<String> searchphones(List onlineUserIds);

    List<OnlineUser> selectByOnlineUserIdList(List<Long> onlineUserIdList);

    OnlineUser selectByPhone(String phone);

    void insertOnlineUser(OnlineUser onlineUser1);

    void updateOnlineUser(OnlineUser onlineUser);

    String selectNameByOnlineUserId(Long onlineUserId);

    ReceiveMessageVo selectByName(String name);
>>>>>>> d1aef324a1116338d372624d22129509a4e81ae8
}
