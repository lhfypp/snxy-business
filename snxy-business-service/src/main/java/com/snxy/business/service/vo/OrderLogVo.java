package com.snxy.business.service.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderLogVo {

    private Long deliveryOrderId;

    private Integer status;

    private Long operatorId;

    private String operatorName;

    private String operationDesc;

    private String remark;

    private Byte isDelete;
}
