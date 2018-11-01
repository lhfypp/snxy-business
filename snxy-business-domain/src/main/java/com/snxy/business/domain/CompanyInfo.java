
package com.snxy.business.domain;

import lombok.Builder;

@Builder
public class CompanyInfo {
    private Long id;

    private Integer merchantType;//公司类别

    private Integer operationScale;//公司规模

    private String merchantName;//公司名称

    private String operationSope;//公司经营范围

    private String socialInfoCode;//公司信用代码

    private String corporateCertificationUrl;//公司认证图片

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMerchantType() {
        return merchantType;
    }

    public void setMerchantType(Integer merchantType) {
        this.merchantType = merchantType;
    }

    public Integer getOperationScale() {
        return operationScale;
    }

    public void setOperationScale(Integer operationScale) {
        this.operationScale = operationScale;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getOperationSope() {
        return operationSope;
    }

    public void setOperationSope(String operationSope) {
        this.operationSope = operationSope;
    }

    public String getSocialInfoCode() {
        return socialInfoCode;
    }

    public void setSocialInfoCode(String socialInfoCode) {
        this.socialInfoCode = socialInfoCode;
    }

    public String getCorporateCertificationUrl() {
        return corporateCertificationUrl;
    }

    public void setCorporateCertificationUrl(String corporateCertificationUrl) {
        this.corporateCertificationUrl = corporateCertificationUrl;
    }
}
