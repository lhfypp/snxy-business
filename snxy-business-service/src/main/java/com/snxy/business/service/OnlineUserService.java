package com.snxy.business.service;

import com.snxy.business.domain.OnlineUser;

import java.util.List;

public interface OnlineUserService {

    void updateName(Long systemUserId, String name);

    OnlineUser selectByPhone(String phone);

    void insertOnlineUser(OnlineUser onlineUser);

    List<OnlineUser> selectByPhoneList(List<String> phoneList);

    void insertOnlineUserList(List<OnlineUser> onlineUserList);

    List<OnlineUser> selectByOnlineUserIdList(List<Long> onlineUserIdlist);

//    更换手机号前获取验证码
    String  getSmsCode(String oldMobile);
//    更换在线用户手机号
    void updateOnlineMobile(Long systemUserId ,String oldMobile,String newMobile,String smsCode);

    OnlineUser selectByOnlineUserId(Long onlineUserId);
}
