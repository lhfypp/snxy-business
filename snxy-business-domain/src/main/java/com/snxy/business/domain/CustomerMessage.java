package com.snxy.business.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerMessage {
    private Long id;

    private String content;

    private Long onlineUserId;

    private String messageAnswer;

    private Byte isDelete;

}