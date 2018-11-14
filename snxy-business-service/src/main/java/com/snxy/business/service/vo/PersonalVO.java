package com.snxy.business.service.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonalVO {
    private String userName;//员工姓名
    private String phone;//手机号
    private String url;//用户头像
    private String identityName;//身份名称
}
