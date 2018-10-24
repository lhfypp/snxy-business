package com.snxy.business.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCheckBillVO {
    @NotBlank(message="订单id不能为空")
    private String orderId;
    private String companyName;//商户名称
    private String merchantManager;//商品负责人
    private String nameOfCommodity;//商品名称
    private String theNumber;//商品数量
    private String origin;//产地
    private String platNumber;//来货车牌号
    private String arrevedTime;//确认到达时间
    private String desc;//备注

}
