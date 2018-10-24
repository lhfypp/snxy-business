package com.snxy.business.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsertCurrentOrder {
    private long deliveryOrderId;
    private String driverMobile;
    private String driverName;
    private long vehicleId;


}
