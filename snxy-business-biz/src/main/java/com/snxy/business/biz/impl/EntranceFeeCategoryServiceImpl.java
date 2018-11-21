package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.EntranceFeeCategoryMapper;
import com.snxy.business.domain.EntranceFeeCategory;
import com.snxy.business.service.EntranceFeeCategoryService;
import com.snxy.business.service.vo.Goods;
import com.snxy.common.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class EntranceFeeCategoryServiceImpl implements EntranceFeeCategoryService {
    @Resource
    private EntranceFeeCategoryMapper entranceFeeCategoryMapper;


    @Override
    public List<EntranceFeeCategory> selectByCategoryName(String categoryName) {
        List<EntranceFeeCategory> entranceFeeCategoryList = entranceFeeCategoryMapper.selectByCategoryName(categoryName);
        if(entranceFeeCategoryList==null){
            throw new BizException("系统暂时没有该货品，请重新选择");
        }
        return entranceFeeCategoryList;
    }
}
