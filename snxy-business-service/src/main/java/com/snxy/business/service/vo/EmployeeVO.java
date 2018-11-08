package com.snxy.business.service.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeVO {
    private String userName;//添加的员工姓名
    private String phone;//手机号
    private Long companyId;
    private boolean beResponsible;//是否为负责人
}
