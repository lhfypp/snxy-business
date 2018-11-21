package com.snxy.business.service.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonalVO {
    private String userName;//员工姓名
    private String phone;//手机号
    private List<String> identityNameList;//身份名称
}
