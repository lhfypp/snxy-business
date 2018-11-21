package com.snxy.business.service;

import com.snxy.business.domain.Vegetable;
import com.snxy.business.service.vo.Goods;
import com.snxy.common.util.PageInfo;

import java.util.List;

public interface VegetableService {
    List<Vegetable> selectAll();

    PageInfo<Goods> selectAllGoodsByCategoryId(Long vegetableCategoryId);

    List<Goods> selectByVegetableName(String vegetableName);
}
