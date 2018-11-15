package com.snxy.business.service.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MailVO {
    private String name;
    private String phone;
    private byte responsibleType;//0为未加入任何公司，1为加入商户负责人的公司，2为加入别的公司
}
