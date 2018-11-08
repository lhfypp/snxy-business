package com.snxy.business.domain;

import lombok.Builder;

public class Image {
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

    public Image(String fileaPath, Integer type) {
        this.fileaPath = fileaPath;
        this.type = type;
    }
}
