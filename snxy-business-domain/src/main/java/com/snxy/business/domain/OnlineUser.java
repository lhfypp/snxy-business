package com.snxy.business.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OnlineUser {
    private Long id;

    private Long systemUserId;

    private String userName;

    private String phone;

    private Byte isDelete;
}