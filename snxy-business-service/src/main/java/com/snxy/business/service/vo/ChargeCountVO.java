package com.snxy.business.service.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChargeCountVO {
    private Long entranceFeeCategoryId;//货品id

    private Long entranceFeeCapacityId;//载重类型id

    private Integer loadStatus;//装车类型 1 满车 2 半车 3 1/4车 4 3/4车
}
