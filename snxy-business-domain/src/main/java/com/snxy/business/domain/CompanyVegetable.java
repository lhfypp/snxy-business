package com.snxy.business.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyVegetable {
    private Long id;

    private String code;

    private String vegetableName;

    private Long vegetableCategoryId;

    private Long vegetablePriceId;

    private Long companyId;

    private BigDecimal price;

    private Date effectiveStartTime;

    private Date gmtCreate;

    private Date gmtModified;

    private Byte isDelete;
}