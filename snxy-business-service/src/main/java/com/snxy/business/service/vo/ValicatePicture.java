package com.snxy.business.service.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValicatePicture {
    private Integer certificateType;	//1 -- 产地证明 2 -- 检验证明
    private MultipartFile file;
}
