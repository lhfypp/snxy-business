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
public class DeliveryOrderVo {
    //建单人姓名
    private String creator;
    //订单号
    private String orderNo;
    //发货信息
    private SenderVO senderVO;
    //收获信息
    private ReceiverVO receiverVO;
    //司机信息
    private DriverVO driverVO;
    //发货时间
    private Date sendTime;
    //发货公司(下单人公司)
    private String receiverCompany;
    //产地证明,检测证明图片
    private List<ValicatePictureVO> certificates;
    //货品信息
    private List<GoodsVO> goodsVOList;

}
