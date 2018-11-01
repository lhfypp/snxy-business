package com.snxy.business.service.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.validator.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeDriversVo {
    @NotBlank(message="订单id不能为空")
    private String logisticOrderId;
    private String driverMobile;
    private String driverName;
}
