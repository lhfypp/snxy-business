package com.snxy.business.domain;

import lombok.Data;

@Data
public class IdentityType {
    private Integer id;

    private Byte identityType;

    private String identityName;

    private String remark;

    private Byte isDelete;


}