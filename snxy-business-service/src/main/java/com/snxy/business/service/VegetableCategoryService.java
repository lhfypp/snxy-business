package com.snxy.business.service;

import com.snxy.business.domain.Vegetable;

import java.util.List;

public interface VegetableCategoryService {
    //long selectIdByGoodName(String goodName);
    List<Long> selectIdsById(long id);

    List<Vegetable> selectvegetableByName(String vegeatableName);
}
