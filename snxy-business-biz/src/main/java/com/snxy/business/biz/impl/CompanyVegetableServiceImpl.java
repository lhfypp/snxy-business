package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.CompanyVegetableMapper;
import com.snxy.business.domain.CompanyVegetable;
import com.snxy.business.domain.VegetablePrice;
import com.snxy.business.service.CompanyVegetableService;
import com.snxy.business.service.VegetablePriceService;
import com.snxy.business.service.vo.CompanyGoodsVo;
import com.snxy.business.service.vo.GoodsVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CompanyVegetableServiceImpl implements CompanyVegetableService {

    @Resource
    private CompanyVegetableMapper companyVegetableMapper;

    @Resource
    private VegetablePriceService vegetablePriceService;

    @Override
    public List<GoodsVo> showGoods(Long userCompanyId) {
        List<Long> vegetablePriceIdList = companyVegetableMapper.selectVegetablePriceIdByCompanyId(userCompanyId);
        List<VegetablePrice> vegetablePriceList = vegetablePriceService.selectGoodsVoByPriceIdList(vegetablePriceIdList);

        List<GoodsVo> goodsVoList = new ArrayList<>();
        for (int i = 0; i < vegetablePriceList.size(); i++) {
            GoodsVo goodsVo = new GoodsVo();
            goodsVo.setVegetablePriceId(vegetablePriceList.get(i).getId());
            goodsVo.setVegetableName(vegetablePriceList.get(i).getVegetableName());
            goodsVoList.add(goodsVo);
        }
        return goodsVoList;
    }

    @Override
    public CompanyVegetable selectCompanyGoodsByGoodsNameAndCompanyId(CompanyGoodsVo companyGoodsVo) {
        CompanyVegetable companyVegetable = companyVegetableMapper.selectCompanyGoodsByGoodsNameAndCompanyId(companyGoodsVo.getGoodsName(),companyGoodsVo.getCompanyId());
        return companyVegetable;
    }
}
