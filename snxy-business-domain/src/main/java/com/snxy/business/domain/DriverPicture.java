package com.snxy.business.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverPicture {
    private Long onlineUserId;//用户id

    private Date gmtCreate;

    private String name;//姓名

    private String identityNO;//身份证

    private List<Image> filePath;//图片 1 身份证正面 2 身份证反面 3 驾驶证

    private String idFrontUrl;

    private String idBackUrl;

    private String drivingLicenseUrl;

}
