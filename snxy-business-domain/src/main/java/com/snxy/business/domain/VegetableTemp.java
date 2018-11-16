package com.snxy.business.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VegetableTemp {
    private Long id;

    private String vegetableName;

    private Long createSourceId;

    private Integer sourceType;

    private Integer status;

    private Date gmtCreate;

    private Date gmtModified;

    private Byte isDelete;


}