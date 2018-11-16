package com.snxy.business.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VegetableDeliveryRelation {
    private Long id;

    private Long deliveryOrderId;

    private Long vegetableId;

    private String vegetableName;

    private Integer loadStatus;

    private Date gmtCreate;

    private Date gmtModified;

    private Byte isDelete;

    private BigDecimal vegetablePrice;

    private BigDecimal vegetableWeight;

}