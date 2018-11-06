package com.snxy.business.service.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewCompanyVO {
    private Long onlineUserId;
    private String merchantName;//商户公司名称
    private String corporateCertificationUrl;//企业认证图片地址
    private String socialInfoCode;//社会信用代码
}
