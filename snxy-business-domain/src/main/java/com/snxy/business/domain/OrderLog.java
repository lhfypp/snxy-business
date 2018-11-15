package com.snxy.business.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderLog {
    private Long id;

    private Long deliveryOrderId;

    private Integer status;

    private Long operatorId;

    private String operatorName;

    private String operationDesc;

    private String remark;

    private Date gmtCreate;

    private Date gmtModified;

    private Byte isDelete;

}