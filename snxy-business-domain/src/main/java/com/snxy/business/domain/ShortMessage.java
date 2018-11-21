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
public class ShortMessage {
    private Long id;

    private String content;

    private String remark;

    private String receiverMobile;

    private Date sendTime;

    private Date gmtCreate;

    private Byte isDelete;

    private Date gmtModified;

}