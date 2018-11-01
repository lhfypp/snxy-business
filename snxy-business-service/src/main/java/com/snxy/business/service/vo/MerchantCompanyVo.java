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
    private Long onlineUserId;
    private Byte merchantType;
    private String merchantName;
    private Integer operationScale;
    private String operationSope;
    private String socialInfoCode;
    private MultipartFile file;
}
