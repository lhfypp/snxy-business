package com.snxy.business.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InsertCurrentOrder {
    private long deliveryOrderId;
    private String driverMobile;
    private String driverName;
    private long vehicleId;


}
