package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.VegetableCategoryMapper;
import com.snxy.business.dao.mapper.VegetablePriceMapper;
import com.snxy.business.domain.Vegetable;
import com.snxy.business.service.VegetableCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
@Slf4j
public class VegetableCategoryServiceImpl implements VegetableCategoryService {
    @Resource
    private VegetableCategoryMapper  vegetableCategoryMapper;
    @Resource
    private VegetablePriceMapper vegetablePriceMapper;


//    @Override
//    public long selectIdByGoodName(String goodName) {
//        return 0;
//    }

    @Override
    public List<Long> selectIdsById(long id) {
        return vegetableCategoryMapper.selectIdsById(id);
    }

    //菜价搜索
    @Override
    public List<Vegetable> selectvegetableByName(String vegeatableName) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date());//获取当前时间
        List<Vegetable> list = vegetablePriceMapper.selectvegetableByName(vegeatableName,date);
        return list;
    }
}
