package com.snxy.business.domain;

import lombok.Builder;

@Builder
public class DirvingImage {

    private String fileaPath;

    private Integer type;

    public String getFileaPath() {
        return fileaPath;
    }

    public void setFileaPath(String fileaPath) {
        this.fileaPath = fileaPath;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
