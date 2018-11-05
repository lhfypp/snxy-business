package com.snxy.business.domain;

import java.math.BigDecimal;
import java.util.Date;

public class GuaranteeDeposit {
    private Long id;

    private Long deliveryOrderId;

    private Long onlineUserId;

    private BigDecimal guaranteeDeposit;

    private Integer status;

    private String remark;

    private Date payTime;

    private Integer payMethod;

    private Date refundTime;

    private Date gmtCreate;

    private Date gmtModified;

    private Byte isDelete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDeliveryOrderId() {
        return deliveryOrderId;
    }

    public void setDeliveryOrderId(Long deliveryOrderId) {
        this.deliveryOrderId = deliveryOrderId;
    }

    public Long getOnlineUserId() {
        return onlineUserId;
    }

    public void setOnlineUserId(Long onlineUserId) {
        this.onlineUserId = onlineUserId;
    }

    public BigDecimal getGuaranteeDeposit() {
        return guaranteeDeposit;
    }

    public void setGuaranteeDeposit(BigDecimal guaranteeDeposit) {
        this.guaranteeDeposit = guaranteeDeposit;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Integer getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(Integer payMethod) {
        this.payMethod = payMethod;
    }

    public Date getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
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