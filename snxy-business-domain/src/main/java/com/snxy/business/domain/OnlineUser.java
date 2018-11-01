package com.snxy.business.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OnlineUser {
    private Long id;

    private Long systemUserId;

    private String name;

    private String phone;

    private Byte isDelete;

}