package com.snxy.business.service.vo;

import com.snxy.business.domain.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverPictureVO {
    private Long onlineUserId;//用户id

    private Date gmtCreate;

    private String name;//姓名

    private String identityNO;//身份证

    private List<MultipartFile> file;

    private List<Image> filePath;//图片 1 身份证正面 2 身份证反面 3 驾驶证

    private String idFrontUrl;

    private String idBackUrl;

    private String drivingLicenseUrl;

    private String nation;//国家

    private String address;//地址

    private String bornDate;//出生日期

    private String gender;//性别

    private String driverLicenseNo;//驾驶证号

    private String expirationDate;

    private String issueAuthority;

    private String issueDate;

    private String effectiveStartDate;//驾驶证时间

    private String initialLicenseDate;

    private String driveType;//车类型

    private String validityTime;

}
