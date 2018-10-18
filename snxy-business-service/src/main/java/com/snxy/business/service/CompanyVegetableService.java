package com.snxy.business.service;

import com.snxy.business.domain.CompanyVegetable;

import java.util.List;

public interface CompanyVegetableService {
    List<CompanyVegetable> showGoods(Long userCompanyId);

    CompanyVegetable selectByGoodsName(String goodsName);
}
