package com.snxy.business.service.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeDriversVo {
    private String logisticOrderId;
    private String driverMobile;
    private String driverName;
}
