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
public class Message {
    private Long id;

    private String title;

    private String content;

    private String remark;

    private Long sender;

    private Integer hasPicture;

    private String url;

    private Integer importanceLevel;

    private Integer isTop;

    private Date gmtCreate;

    private Date gmtModified;

    private Byte isDelete;


}