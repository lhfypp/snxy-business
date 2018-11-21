package com.snxy.business.service.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ValicatePictureUrlVO {
    private Integer certificateType;	//1 -- 产地证明 2 -- 检验证明
    private String url;
}
