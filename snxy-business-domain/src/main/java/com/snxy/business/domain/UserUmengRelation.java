package com.snxy.business.domain;

import lombok.Data;

import java.util.Date;

@Data
public class UserUmengRelation {
    private Long id;

    private Long systemUserId;

    private String phoneType;

    private String deviceToken;

    private Integer validFlag;

    private String remark;

    private Date gmtCreate;

    private Date gmtModified;

    private Byte isDelete;
}