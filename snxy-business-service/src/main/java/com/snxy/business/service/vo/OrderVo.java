package com.snxy.business.service.vo;

import com.snxy.business.domain.Goods;
import com.snxy.business.domain.VegetableCertificate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderVo {
    //订单号
    private String orderNo;
    //发货人姓名
    private String senderName;
    //发货人电话
    private String senderMobile;
    //发货地址
    private String startAddr;
    //收货人姓名
    private String receiverName;
    //收货人电话
    private String receiverMobile;
    //收货人公司
    private String receiverCompany;
    //收货地址
    private String endAddr;
    //货品
    private List<Goods> goodsList;
    //货车类型
    private Integer truckTypeId;
    //运费
    private BigDecimal deliveryFee;
    //司机姓名
    private String driverName;
    //联系电话
    private String driverMobile;
    //距离
    private float distance;
    //发货时间
    private Date sendTime;
    //预计到达时间
    private Date estArrivalTime;
    //货物图片地址集合
    private List<String> urlList;
    //产地证明,检测证明图片
    private List<VegetableCertificate> vegetableCertificateList;
}
