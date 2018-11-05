package com.snxy.business.domain;

public class UserIdentity {
    private Long id;

    private Byte onlineUserId;

    private Integer identityId;

    private Byte isDelete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte getOnlineUserId() {
        return onlineUserId;
    }

    public void setOnlineUserId(Byte onlineUserId) {
        this.onlineUserId = onlineUserId;
    }

    public Integer getIdentityId() {
        return identityId;
    }

    public void setIdentityId(Integer identityId) {
        this.identityId = identityId;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }
}