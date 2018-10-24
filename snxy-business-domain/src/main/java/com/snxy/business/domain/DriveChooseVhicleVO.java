package com.snxy.business.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class DriveChooseVhicleVO {
    @NotNull(message="订单id不能为空")
    private String orderId;
    @NotNull(message="车类型id不能为空")
    private String vehicleId;
}
