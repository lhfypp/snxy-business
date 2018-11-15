package com.snxy.business.service;

import com.snxy.business.domain.EntranceFeeCategory;

import java.util.List;

public interface EntranceFeeCategoryService
{
    List<EntranceFeeCategory> showAllGoods();

    List<EntranceFeeCategory> selectByCategoryName(String categoryName);
}
