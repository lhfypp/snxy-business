package com.snxy.business.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserIdentity {
    private Long id;

    private Long onlineUserId;

    private Integer identityId;

    private Byte isDelete;


}