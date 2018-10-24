package com.snxy.business.service.vo;

import org.springframework.web.multipart.MultipartFile;

public class ValicatePicture {
    private Integer certificateType;	//1 -- 产地证明 2 -- 检验证明
    private MultipartFile file;

    public Integer getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(Integer certificateType) {
        this.certificateType = certificateType;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
