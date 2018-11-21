package com.snxy.business.service.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QualitySheetVO {
    private String qualitySheerId;//检测单Id
    private String qualityNO;//检测号
    private Date proposeTime;//提交时间
    private String qrcode;//二维码
    private String name;//货物名称
    private Float load;//货物载重
    private String platNumber;//车牌号
    private Integer status;//状态信息
    private Long userId;
    private Integer qualified;

}
