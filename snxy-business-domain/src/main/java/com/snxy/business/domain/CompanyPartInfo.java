package com.snxy.business.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyPartInfo {
    private String companyName;
    private String responsiblePerson;
    private String mobile;
    private String companyId;
}
