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
public class VegetableDeliveryRelation {
    private Long id;

    private Long deliveryOrderId;

    private Long entranceFeeCategoryId;

    private String categoryName;

    private Integer loadStatus;

    private Date gmtCreate;

    private Date gmtModified;

    private Byte isDelete;


}