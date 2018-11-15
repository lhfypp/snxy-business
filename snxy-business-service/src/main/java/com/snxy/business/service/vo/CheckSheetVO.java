package com.snxy.business.service.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckSheetVO {
    @NotBlank(message = "订单id不能为空")
    private String deliveryOrderId;//订单id
    @NotBlank(message = "公司名称不能为空")
    private String companyName;//公司名称
    @NotBlank(message = "负责人名称不能为空")
    private String responsebilePerson;//负责人名称
    @NotBlank(message = "产地不能为空")
    private String location;//产地
    @NotBlank(message = "车牌号不能为空")
    private String carPlateNO;//车牌号
    @NotBlank(message = "到货时间不能为空")
    private String arrivalTime;//到货时间
    @NotBlank(message = "商品名称不能为空")
    private String goodName;//商品名称
    private String weight;//重量,可以为空
    private String userId;//用户id
    //缴费人姓名，手机号?
}
