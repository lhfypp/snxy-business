package com.snxy.business.dao.mapper;

import com.snxy.business.domain.VegetableImage;

import java.util.List;

public interface VegetableImageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(VegetableImage record);

    int insertSelective(VegetableImage record);

    VegetableImage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VegetableImage record);

    int updateByPrimaryKey(VegetableImage record);

    void insertVegetableImageList(List<VegetableImage> vegetableImageList);

    List<VegetableImage> selectByOderId(Long deliveryOrderId);
}