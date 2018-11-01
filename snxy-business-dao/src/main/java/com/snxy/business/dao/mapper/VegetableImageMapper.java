package com.snxy.business.dao.mapper;

import com.snxy.business.domain.VegetableImage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VegetableImageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(VegetableImage record);

    int insertSelective(VegetableImage record);

    VegetableImage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VegetableImage record);

    int updateByPrimaryKey(VegetableImage record);

    void insertVegetableImageList(@Param("vegetableImageList") List<VegetableImage> vegetableImageList);

    List<VegetableImage> selectByOderId(Long deliveryOrderId);
    List<String>SelectListByOderId(Long deliveryOrderId);
}