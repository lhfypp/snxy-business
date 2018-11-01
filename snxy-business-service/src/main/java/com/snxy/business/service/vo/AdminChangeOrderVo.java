package com.snxy.business.service.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminChangeOrderVo {
    private Long deliveryOrderId;
    private List<MultipartFile> files;//图片地址集合
    private Integer loadStatus;
    private BigDecimal entryFee;
    private String remark;//备注
}
