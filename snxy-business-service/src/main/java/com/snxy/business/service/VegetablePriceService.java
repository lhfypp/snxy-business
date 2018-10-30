package com.snxy.business.service;

import com.snxy.business.domain.VegetablePrice;
import com.snxy.business.service.vo.VegetablePriceListVo;

import java.util.List;

public interface VegetablePriceService {
    List<VegetablePrice> selectGoodsVoByPriceIdList(List<Long> vegetablePriceIdList);
    List<VegetablePrice>selectVegetablePrices(VegetablePriceListVo vegetablePriceListVo);

}
