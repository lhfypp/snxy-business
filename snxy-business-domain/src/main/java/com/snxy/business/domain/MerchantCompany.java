package com.snxy.business.domain;

import java.util.Date;

public class MerchantCompany {
    private Long id;

    private String merchantName;

    private Integer merchantType;

    private Integer operationScale;

    private String operationSope;

    private String socialInfoCode;

    private String corporateCertificationUrl;

    private Byte certificationStatus;

    private Date gmtCreate;

    private Date gmtModified;

    private Byte isDelete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
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

    public Byte getCertificationStatus() {
        return certificationStatus;
    }

    public void setCertificationStatus(Byte certificationStatus) {
        this.certificationStatus = certificationStatus;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }
}