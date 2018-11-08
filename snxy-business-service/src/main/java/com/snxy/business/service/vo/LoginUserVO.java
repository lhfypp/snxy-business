package com.snxy.business.service.vo;

import com.snxy.common.util.CheckUtil;
import com.snxy.common.util.StringUtil;
import lombok.Data;

@Data
public class LoginUserVO {

    private String userName;
    private String password;
    private  Integer loginType ; // 1 -- 手机号登陆   2 -- account 账号登陆  3 -- 手机验证码登陆
    private Integer  deviceType; // 1 --- 安卓手机  2 ---- 苹果手机  3 --- 安卓平板  4 --- 苹果平板   5 --- 电脑端
    private  String smsCode;
    public void checkParam(){
        CheckUtil.isTrue(StringUtil.isNotBlank(userName));
        CheckUtil.isTrue(loginType == 1 || loginType == 2 || loginType == 3,"非法的登陆类型");
    }
}
