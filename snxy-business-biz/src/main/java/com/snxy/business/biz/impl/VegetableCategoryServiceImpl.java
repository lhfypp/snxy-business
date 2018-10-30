package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.VegetableCategoryMapper;
import com.snxy.business.service.VegetableCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
@Slf4j
public class VegetableCategoryServiceImpl implements VegetableCategoryService {
    @Resource
    private VegetableCategoryMapper  vegetableCategoryMapper;


//    @Override
//    public long selectIdByGoodName(String goodName) {
//        return 0;
//    }

    @Override
    public List<Long> selectIdsById(long id) {
        return vegetableCategoryMapper.selectIdsById(id);
    }
}
