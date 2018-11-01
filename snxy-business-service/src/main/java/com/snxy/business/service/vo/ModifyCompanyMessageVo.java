package com.snxy.business.service.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModifyCompanyMessageVo {
    private Long id;
    private Integer merchantType;
    private String merchantName;
    private Integer operationScale;
    private String operationSope;
    private String socialInfoCode;
}
