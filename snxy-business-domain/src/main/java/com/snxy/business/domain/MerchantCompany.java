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
public class MerchantCompany {
    private Long id;

    private String merchantName;

    private Integer merchantType;

    private Integer operationScale;

    private String operationSope;

    private String socialInfoCode;

    private String corporateCertificationUrl;

    private Byte certificationStatus;

    private Date gmtCreate;

    private Date gmtModified;

    private Byte isDelete;

}