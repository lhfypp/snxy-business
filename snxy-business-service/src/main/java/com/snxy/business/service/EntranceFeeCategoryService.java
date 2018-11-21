package com.snxy.business.service;

import com.snxy.business.domain.EntranceFeeCategory;
import com.snxy.business.service.vo.Goods;

import java.util.List;

public interface EntranceFeeCategoryService
{
    List<EntranceFeeCategory> selectByCategoryName(String categoryName);
}
