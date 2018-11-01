package com.snxy.business.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VegetableImage {
    private Long id;

    private Long deliveryOrderId;

    private String url;

    private Date uploadTime;

    private Integer type;

    private Byte isDelete;
}