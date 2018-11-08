package com.snxy.business.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserIdentity {
    private Long id;

    private Long onlineUserId;

    private Integer identityId;

    private Byte isDelete;

}