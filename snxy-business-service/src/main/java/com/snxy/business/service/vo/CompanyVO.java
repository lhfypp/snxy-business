package com.snxy.business.service.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyVO {
    private String companyName;

    private Long companyId;

    private Boolean certificationStatus;

    private String headName;

    private String headPhone;

}
