package com.snxy.business.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
//修改手机号需要存入redis的数值：新手机号，验证码
public class UserMobileCode {
    private Long userId;
    private String mobile;
    private String smsCode;
}
