package com.snxy.business.dao.mapper;

import com.snxy.business.domain.Vegetable;
import com.snxy.business.domain.VegetablePrice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VegetablePriceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(VegetablePrice record);

    int insertSelective(VegetablePrice record);

    VegetablePrice selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VegetablePrice record);

    int updateByPrimaryKey(VegetablePrice record);

    List<VegetablePrice> selectByPrimaryKeyList(List<Long> vegetablePriceIdList);

    List<VegetablePrice>selectByVegetableCategoryid(@Param("vegetablePriceCategoryIdList") List<Long> vegetablePriceCategoryIdList);

    List<Vegetable> selectvegetableByName(@Param("vegeatableName") String vegeatableName, @Param("date") String date);

}