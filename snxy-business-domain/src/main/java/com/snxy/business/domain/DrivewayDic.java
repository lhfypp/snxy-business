package com.snxy.business.domain;

import java.util.Date;

public class DrivewayDic {
    private Long id;

    private Long gateDictId;

    private String driveway;

    private String remark;

    private Date gmtCreate;

    private Date gmtModified;

    private Byte isDelete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGateDictId() {
        return gateDictId;
    }

    public void setGateDictId(Long gateDictId) {
        this.gateDictId = gateDictId;
    }

    public String getDriveway() {
        return driveway;
    }

    public void setDriveway(String driveway) {
        this.driveway = driveway;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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