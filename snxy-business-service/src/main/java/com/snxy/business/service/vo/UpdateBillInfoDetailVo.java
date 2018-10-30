package com.snxy.business.service.vo;

import com.snxy.business.domain.Goods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBillInfoDetailVo {
    @NotNull(message="订单id不能为空")
    private  long deliveryOrderId;
    private String  deliveryOrderNo;
    @NotNull(message="发货时间不能为空")
    private Date sendTime;
    @NotNull(message="预计到达时间不能为空")
    private Date estArrivalTime;
    @NotBlank(message="发货人姓名不能为空")
    private String sender;
    @NotBlank(message="发货人电话不能为空")
    private String senderMobile;
    @NotBlank(message="发货地址不能为空")
    private String sendLocation;
    @NotBlank(message="收货人姓名不能为空")
    private String receiver;
    @NotBlank(message = "收货人电话不能为空")
    private String receiverMobile;
    @NotBlank(message="收货地址不能为空")
    private String receiverLocation;
    private String driverName;
    private String driveMobile;
    @NotBlank(message = "车类型不能为空")
    private long truckTypeId;
    @NotBlank(message="运费不能为空")
    private BigDecimal deliveryFee;
    private List<ValicatePicture> pictures;
    private List<Goods>goods;
    private List<MultipartFile> files;

}
