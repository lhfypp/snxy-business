package com.snxy.business.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehiclePartInfo {
    private Long vehicleId;

    private String plateNumber;
    private String carTypeDesc;
}
