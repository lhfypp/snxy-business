package com.snxy.business.service;
import java.util.List;
public interface VegetableCategoryService {
    List<Long> selectIdsById(long id);
    //long selectIdByGoodName(String goodName);
}
