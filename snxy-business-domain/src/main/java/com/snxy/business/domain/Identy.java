package com.snxy.business.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Identy {

    private Long onlineUserId;

    private Integer identityId;

    private String identyName;

}
