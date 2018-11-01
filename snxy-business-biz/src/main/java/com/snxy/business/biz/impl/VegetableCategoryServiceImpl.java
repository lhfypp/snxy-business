package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.VegetableCategoryMapper;
import com.snxy.business.domain.VegetableCategory;
import com.snxy.business.service.VegetableCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
@Slf4j
public class VegetableCategoryServiceImpl implements VegetableCategoryService {

    @Resource
    private VegetableCategoryMapper vegetableCategoryMapper;

    @Override
    public String selectGoodsCategoryNameByCategoryId(Long vegetableCategoryId) {
        VegetableCategory vegetableCategory = vegetableCategoryMapper.selectByPrimaryKey(vegetableCategoryId);
        return vegetableCategory.getVegetableCategoryName();
    }
}
