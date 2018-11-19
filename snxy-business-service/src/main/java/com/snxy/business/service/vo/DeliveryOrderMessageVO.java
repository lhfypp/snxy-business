package com.snxy.business.service.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryOrderMessageVO {
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
    //发货公司(下单人公司)
    private String receiverCompany;
    //货品
    private List<GoodsVO> goodsVOS;
    //产地证明，质检单的图片地址
    private List<ValicatePictureUrlVO> valicatePictureVOS;
    //车辆载重吨数
    private String tonnage;
    //实际进门费用
    private BigDecimal actualFee;
    //预计进门费用
    private BigDecimal estFee;
    //押金
    private BigDecimal deposit;
    //检测费
    private BigDecimal checkFee;
    //总费用
    private BigDecimal totalFee;
    //装货状态
    private Integer loadStatus;
}
