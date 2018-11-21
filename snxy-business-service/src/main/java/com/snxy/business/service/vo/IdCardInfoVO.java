package com.snxy.business.service.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
//身份证信息
public class IdCardInfoVO {
    private String name;

    private String nation;

    private String address;

    private String idNo;

    private String bornDate;

    private String gender;

    private String expirationDate;

    private String issueAuthority;

    private String issueDate;

    private String effectiveStartDate;

    private String driverLicenseNo;

    private String country;

    private String initialLicenseDate;

    private String driveType;

    private String validityTime;
    
}
