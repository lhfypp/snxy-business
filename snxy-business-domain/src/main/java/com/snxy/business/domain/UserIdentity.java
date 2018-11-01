package com.snxy.business.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserIdentity {
    private Long id;

    private Long onlineUserId;

    private Integer identityId;

    private Byte isDelete;


}