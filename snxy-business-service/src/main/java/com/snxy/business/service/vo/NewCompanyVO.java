package com.snxy.business.service.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewCompanyVO {
    private String merchantName;//商户公司名称
    //private String corporateCertificationUrl;//企业认证图片地址
    private String socialInfoCode;//社会信用代码
    private String headName;//负责人姓名
    private String headPhone;//负责人电话
}
