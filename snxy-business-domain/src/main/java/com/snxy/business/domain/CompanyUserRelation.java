package com.snxy.business.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyUserRelation {
    private Long id;

    private Long companyId;

    private Byte companyType;

    private Long onllineUserId;

    private Integer isResponsible;

    private Date gmtCreate;

    private Date gmtModifed;

    private Byte isDelete;

}