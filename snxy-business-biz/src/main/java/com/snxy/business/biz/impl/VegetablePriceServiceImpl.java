package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.VegetablePriceMapper;
import com.snxy.business.domain.VegetablePrice;
import com.snxy.business.service.VegetablePriceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class VegetablePriceServiceImpl implements VegetablePriceService {

    @Resource
    private VegetablePriceMapper vegetablePriceMapper;

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
}
