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
public class MerchantCompany {
    private Long id;

    private String merchantName;

    private String socialInfoCode;

    private String corporateCertificationUrl;

    private Byte certificationStatus;

    private Date gmtCreate;

    private Date gmtModified;

    private Byte isDelete;

}