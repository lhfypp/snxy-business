package com.snxy.business.service;

public interface OnlineUserService {

    void updateName(Long systemUserId, String name);

    Long selectOnlineUserIdBySystemUserId(Long systemUserId);

//    更换手机号前获取验证码
    String  getSmsCode(String oldMobile);
//    更换在线用户手机号
    void updateOnlineMobile(Long systemUserId ,String oldMobile,String newMobile,String smsCode);
}
