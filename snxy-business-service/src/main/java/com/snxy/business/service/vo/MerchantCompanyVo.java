package com.snxy.business.service.vo;

import org.springframework.web.multipart.MultipartFile;

public class MerchantCompanyVo {
    private Integer merchantCompanyType;
    private String merchantCompanyName;
    private Integer merchantCompanyScale;
    private String merchantCompanyScope;
    private String socialInfoCode;
    private MultipartFile file;

    public Integer getMerchantCompanyType() {
        return merchantCompanyType;
    }

    public void setMerchantCompanyType(Integer merchantCompanyType) {
        this.merchantCompanyType = merchantCompanyType;
    }

    public String getMerchantCompanyName() {
        return merchantCompanyName;
    }

    public void setMerchantCompanyName(String merchantCompanyName) {
        this.merchantCompanyName = merchantCompanyName;
    }

    public Integer getMerchantCompanyScale() {
        return merchantCompanyScale;
    }

    public void setMerchantCompanyScale(Integer merchantCompanyScale) {
        this.merchantCompanyScale = merchantCompanyScale;
    }

    public String getMerchantCompanyScope() {
        return merchantCompanyScope;
    }

    public void setMerchantCompanyScope(String merchantCompanyScope) {
        this.merchantCompanyScope = merchantCompanyScope;
    }

    public String getSocialInfoCode() {
        return socialInfoCode;
    }

    public void setSocialInfoCode(String socialInfoCode) {
        this.socialInfoCode = socialInfoCode;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
