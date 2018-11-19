package com.snxy.business.biz.impl;

import com.github.pagehelper.PageHelper;
import com.snxy.business.dao.mapper.VegetableMapper;
import com.snxy.business.domain.EntranceFeeCategory;
import com.snxy.business.domain.Vegetable;
import com.snxy.business.service.VegetableService;
import com.snxy.business.service.vo.Goods;
import com.snxy.common.exception.BizException;
import com.snxy.common.util.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VegetableServiceImpl implements VegetableService {
    @Resource
    private VegetableMapper vegetableMapper;

    @Override
    public List<Vegetable> selectAll() {
        List<Vegetable> vegetableList = vegetableMapper.selectAll();
        return vegetableList;
    }

    @Override
    public PageInfo<Goods> selectAllGoodsByCategoryId(Long vegetableCategoryId,Integer pageNum,Integer pageSize) {
        List<Vegetable> vegetableList = vegetableMapper.selectByCategoryId(vegetableCategoryId);
        List<Goods> goodsList = vegetableList.parallelStream().map(s -> Goods.builder()
                                     .goodsImg(s.getImageUrl())
                                     .goodsName(s.getVegetableName())
                                     .goodsId(s.getId())
                                     .build())
                                     .collect(Collectors.toList());
        PageHelper.startPage(pageNum,pageSize);
        PageInfo<Goods> goodsPageInfo = new PageInfo<>();
        goodsPageInfo.setData(goodsList);

        return goodsPageInfo;
    }

    @Override
    public List<Goods> selectByVegetableName(String vegetableName) {
        List<Vegetable> vegetableList = vegetableMapper.selectByVegetableName(vegetableName);
        if(vegetableList==null){
            throw new BizException("系统暂时没有该货品，是否添加");
        }
        List<Goods> goodsList = vegetableList.parallelStream().map(s -> Goods.builder()
                                  .goodsId(s.getId())
                                  .goodsName(s.getVegetableName())
                                  .goodsImg(s.getImageUrl())
                                  .build())
                                  .collect(Collectors.toList());
        return goodsList;
    }
}
