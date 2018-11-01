package com.snxy.business.service;

import com.snxy.business.domain.VegetablePrice;

import java.util.List;

public interface VegetablePriceService {
    List<VegetablePrice> selectGoodsVoByPriceIdList(List<Long> vegetablePriceIdList);

    VegetablePrice selectGoodsPrice(Long vegetablePriceId);
}
