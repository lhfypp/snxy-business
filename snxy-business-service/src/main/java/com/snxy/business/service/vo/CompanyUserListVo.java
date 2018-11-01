package com.snxy.business.service.vo;

import com.snxy.business.domain.Identy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyUserListVo {
    private Long onlineUserId;
    private Long companyId;
    private Byte companyType;
    private List<Identy> identyList;
    private String name;
    private String phone;
}
