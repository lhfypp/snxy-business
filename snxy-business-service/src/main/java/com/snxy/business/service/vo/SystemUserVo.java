package com.snxy.business.service.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SystemUserVo {
    private String token;
    private String name;

    private Long systemUserId;
    private List<UserIdentityVo> identityTypes;
}
