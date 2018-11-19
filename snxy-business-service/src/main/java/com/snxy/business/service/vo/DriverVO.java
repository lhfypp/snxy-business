package com.snxy.business.service.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DriverVO {
    //司机姓名
    private String driverName;
    //联系电话
    private String driverMobile;
    //车牌号
    private String carPlateNo;
}
