package com.snxy.business.service.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleVO {

    private Long driverInfoId;//司机id

    private String plateNumber;//车牌

    private Long entranceFeeCapacityId;//车类型

    private String vehicleDrivingLicenseNumber;//行驶证编号

    private String tonnage;//吨位

    private MultipartFile file;//驾驶证图片
}
