package com.snxy.business.biz.config;

public enum IdentityTypeEnum {
    Head(1,"商户负责人"),
    Merchant(2,"商户"),
    Driver(3,"司机"),
    Agency(4,"经纪人（代办）"),
    Tourist(5,"随便看看");

    private Integer identityTypeId;
    private String identityTypeName;

    IdentityTypeEnum(Integer identityTypeId, String identityTypeName) {
        this.identityTypeId = identityTypeId;
        this.identityTypeName = identityTypeName;
    }

    public Integer getIdentityTypeId() {
        return identityTypeId;
    }

    public String getIdentityTypeName() {
        return identityTypeName;
    }
}
