package com.snxy.business.domain;

public class IdentityType {
    private Integer id;

    private Byte identyType;

    private String identyName;

    private String remark;

    private Byte isDelete;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Byte getIdentyType() {
        return identyType;
    }

    public void setIdentyType(Byte identyType) {
        this.identyType = identyType;
    }

    public String getIdentyName() {
        return identyName;
    }

    public void setIdentyName(String identyName) {
        this.identyName = identyName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }
}