package com.snxy.business.service.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;


import javax.validation.constraints.NotNull;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class DriverBasicInfoVo {
   @NotBlank(message="司机驾驶证不能为空")

    private String drivingLicenseNumber;//驾驶证
    @NotBlank(message="档案号不能为空")
    private String fileNumber;//档案号
    private String qualificationCertificateNumber;//就业资格证
    private String file;//驾驶证图片


}
