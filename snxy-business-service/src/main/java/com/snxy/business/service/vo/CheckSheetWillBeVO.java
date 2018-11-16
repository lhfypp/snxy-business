package com.snxy.business.service.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckSheetWillBeVO {
    private String deliveryOrderId;//订单id
    private String companyName;//公司名称
    private String responsebilePerson;//负责人名称
    private String location;//产地
    private String carPlateNO;//车牌号
    private Date arrivalTime;//到货时间
    private List<GoodPartVo> goodPartVo;//货品信息

}
