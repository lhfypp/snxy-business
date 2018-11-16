package com.snxy.business.service.vo;

import com.snxy.business.domain.DeliveryOrder;
import com.snxy.business.domain.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HomePageOrderVO {
    private DeliveryOrder deliveryOrder;
    private Integer code;//1司机确认发货 2商户确认订单
    private String goodsName;
}
