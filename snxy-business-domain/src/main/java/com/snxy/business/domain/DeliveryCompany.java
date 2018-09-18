package com.snxy.business.domain;

import java.util.Date;

public class DeliveryCompany {
    private Long id;

    private String deliveryCompanyName;

    private String operationScale;

    private String socialInfoCode;

    private String corporateCertificationUrl;

    private Integer certificationStatus;

    private Date gmtCreate;

    private Date gmtModified;

    private Byte isDelete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeliveryCompanyName() {
        return deliveryCompanyName;
    }

    public void setDeliveryCompanyName(String deliveryCompanyName) {
        this.deliveryCompanyName = deliveryCompanyName;
    }

    public String getOperationScale() {
        return operationScale;
    }

    public void setOperationScale(String operationScale) {
        this.operationScale = operationScale;
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

    public Integer getCertificationStatus() {
        return certificationStatus;
    }

    public void setCertificationStatus(Integer certificationStatus) {
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