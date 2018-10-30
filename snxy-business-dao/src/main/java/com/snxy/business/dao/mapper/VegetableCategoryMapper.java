package com.snxy.business.dao.mapper;

import com.snxy.business.domain.VegetableCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VegetableCategoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(VegetableCategory record);

    int insertSelective(VegetableCategory record);

    VegetableCategory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VegetableCategory record);

    int updateByPrimaryKey(VegetableCategory record);

    //long selectIdByGoodName(@Param("goodName") String goodName);
    List<Long> selectIdsById(@Param("id") long id);
}