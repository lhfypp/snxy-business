package com.snxy.business.service.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangePrincipleVo {
    private Long onlineUserId;
    private Integer companyId;
    private Integer isResponsible;//0负责人，1不是负责人
}
