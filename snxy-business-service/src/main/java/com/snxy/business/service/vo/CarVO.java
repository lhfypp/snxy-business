package com.snxy.business.service.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarVO {
    private Long carId;
    private String carPlateNo;//车牌号
    private String carryingCapacity;//载重类型
}
