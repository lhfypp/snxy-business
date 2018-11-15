package com.snxy.business.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Version {
    private Long id;

    private String versionNum;

    private Date releaseTime;

    private String url;

    private Long systemUserId;

    private String oprateName;

    private Date gmtCreate;

    private Date gmtModified;

    private Byte isDelete;

}