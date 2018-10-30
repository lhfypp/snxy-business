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
public class VehicleGpsVo {
    private Long deliveryOrderId;
    //经度
    private float longitude;
    //纬度
    private float latitude;
    //高度
    private float height;
    private Long onlineUserId;
    //收集时间
    private Date collectionTime;
}
