package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.CompanyVegetableMapper;
import com.snxy.business.domain.CompanyVegetable;
import com.snxy.business.service.CompanyVegetableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class CompanyVegetableServiceImpl implements CompanyVegetableService {

    @Resource
    private CompanyVegetableMapper companyVegetableMapper;

    @Override
    public List<CompanyVegetable> showGoods(Long userCompanyId) {
        List<CompanyVegetable> companyVegetableList = companyVegetableMapper.selectByCompanyId(userCompanyId);
        return companyVegetableList;
    }

    @Override
    public CompanyVegetable selectByGoodsName(String goodsName) {
        CompanyVegetable companyVegetable = companyVegetableMapper.selectByGoodsName(goodsName);
        return companyVegetable;
    }
}
