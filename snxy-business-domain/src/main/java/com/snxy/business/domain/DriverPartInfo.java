package com.snxy.business.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverPartInfo {
    private String driverName;
    private String driverMobile;
    private String plateNumber;
    private String carTypeDesc;
}
