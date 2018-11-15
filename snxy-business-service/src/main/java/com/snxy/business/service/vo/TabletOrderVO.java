package com.snxy.business.service.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TabletOrderVO {
    private String carType;//车类型
    //进门单号？上传还是手录入？
    //操作人员
    //品类
    //车牌号
    //数量
    //产地证明(多个?)
    //检测证明(多个？)
    //进门费
    // 押金
    //检测费
    // 总金额
    //缴费人姓名
    //手机号
    //货物照片（多个？）
    //备注？
}
