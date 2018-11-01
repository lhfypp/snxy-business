package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.VegetablePriceMapper;
import com.snxy.business.domain.VegetablePrice;
import com.snxy.business.service.VegetableCategoryService;
import com.snxy.business.service.VegetablePriceService;
import com.snxy.business.service.vo.VegetablePriceListVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class VegetablePriceServiceImpl implements VegetablePriceService {

    @Resource
    private VegetablePriceMapper vegetablePriceMapper;
    @Resource
    private VegetableCategoryService vegetableCategoryService;
    @Override
    public List<VegetablePrice> selectGoodsVoByPriceIdList(List<Long> vegetablePriceIdList) {
        List<VegetablePrice> vegetablePrices = vegetablePriceMapper.selectByPrimaryKeyList(vegetablePriceIdList);
        return vegetablePrices;
    }

    @Override
    public VegetablePrice selectGoodsPrice(Long vegetablePriceId) {
        VegetablePrice vegetablePrice = vegetablePriceMapper.selectByPrimaryKey(vegetablePriceId);
        return vegetablePrice;
    }
    // 查询菜品价格列表
    public List<VegetablePrice>selectVegetablePrices(VegetablePriceListVo vegetablePriceListVo){
        //查询出大类id
        //查出菜品大类下的所有子类的id
       List<Long> idList= vegetableCategoryService.selectIdsById(Long.parseLong(vegetablePriceListVo.getVegetableCategoryId()));
        //查询出每个子类的详细信息
        return  vegetablePriceMapper.selectByVegetableCategoryid(idList);
    }
}
