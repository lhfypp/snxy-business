package com.snxy.business.service.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MerchantCompanyVo {
    private Integer merchantCompanyType;
    private String merchantCompanyName;
    private Integer merchantCompanyScale;
    private String merchantCompanyScope;
    private String socialInfoCode;
    private MultipartFile file;
}
