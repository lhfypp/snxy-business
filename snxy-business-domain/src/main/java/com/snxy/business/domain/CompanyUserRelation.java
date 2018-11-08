package com.snxy.business.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
<<<<<<< HEAD
import lombok.NoArgsConstructor;

import java.util.Date;
=======
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
>>>>>>> 0715307c3886f405043faeee6e33e8c66c8ff20e
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyUserRelation {
    private Long id;

    private Long companyId;

    private Long onlineUserId;

    private Integer isResponsible;//当为0时是负责人，当为1时不是负责人

    private Date gmtCreate;

    private Date gmtModifed;

    private Byte isDelete;

}