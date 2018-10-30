package com.snxy.business.service;

import com.snxy.business.domain.CompanyVegetable;
import com.snxy.business.service.vo.CompanyGoodsVo;
import com.snxy.business.service.vo.GoodsVo;

import java.util.List;

public interface CompanyVegetableService {
    List<GoodsVo> showGoods(Long userCompanyId);
    CompanyVegetable selectCompanyGoodsByGoodsNameAndCompanyId(CompanyGoodsVo companyGoodsVo);
}
