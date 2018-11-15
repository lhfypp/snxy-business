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
public class VegetableCertificate {
    private Long id;

    private Long deliveryOrderId;

    private String url;

    private Date uploadTime;

    private Integer certificateType;

    private Integer status;

    private Integer xfdCertificate;

    private Date gmtCreate;

    private Date gmtModified;

    private Byte isDelete;


}