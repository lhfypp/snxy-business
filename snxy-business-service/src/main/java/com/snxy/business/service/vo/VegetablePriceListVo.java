package com.snxy.business.service.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VegetablePriceListVo {
    @NotBlank(message="菜品大类id不能为空")
    private  String vegetableCategoryId;
    private String pageSize;
    private String pageNum;

}
