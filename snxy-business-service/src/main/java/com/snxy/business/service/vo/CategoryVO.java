package com.snxy.business.service.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryVO {
    private Long id;
    private Long parentId;
    private String vegetableCategoryName;
    private List<CategoryVO> childCategory;
}
