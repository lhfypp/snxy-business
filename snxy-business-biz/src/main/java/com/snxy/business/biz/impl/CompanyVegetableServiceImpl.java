package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.CompanyVegetableMapper;
import com.snxy.business.domain.CompanyVegetable;
import com.snxy.business.domain.VegetablePrice;
import com.snxy.business.service.CompanyVegetableService;
import com.snxy.business.service.VegetableCategoryService;
import com.snxy.business.service.VegetablePriceService;
import com.snxy.business.service.vo.AddCompanyGoodsVo;
import com.snxy.business.service.vo.CompanyGoodsVo;
import com.snxy.business.service.vo.GoodsListVo;
import com.snxy.business.service.vo.GoodsVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
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
            goodsVo.setVegetablePriceName(vegetablePriceList.get(i).getVegetableName());
            goodsVoList.add(goodsVo);
        }
        return goodsVoList;
    }

    @Override
    public CompanyVegetable selectCompanyGoodsByGoodsNameAndCompanyId(CompanyGoodsVo companyGoodsVo) {
        CompanyVegetable companyVegetable = companyVegetableMapper.selectCompanyGoodsByGoodsNameAndCompanyId(companyGoodsVo.getGoodsName(),companyGoodsVo.getCompanyId());
        return companyVegetable;
    }

    @Override
    public List<GoodsListVo> showGoodsList(Long companyId) {
        List<CompanyVegetable> companyVegetableList = companyVegetableMapper.showGoodsList(companyId);

        List<GoodsListVo> goodsListVoList = new ArrayList<>();
        for (int i = 0; i < companyVegetableList.size(); i++) {
            GoodsListVo goodsListVo = new GoodsListVo();
            BeanUtils.copyProperties(companyVegetableList.get(i),goodsListVo);
            goodsListVoList.add(goodsListVo);
        }
        return goodsListVoList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addCompanyGoods(AddCompanyGoodsVo addCompanyGoodsVo) {
        VegetablePrice vegetablePrice = vegetablePriceService.selectGoodsPrice(addCompanyGoodsVo.getVegetablePriceId());
        CompanyVegetable companyVegetable = CompanyVegetable.builder()
                .companyId(addCompanyGoodsVo.getCompanyId())
                .vegetableCategoryId(vegetablePrice.getVegetableCategoryId())
                .vegetableName(vegetablePrice.getVegetableName())
                .gmtCreate(new Date())
                .isDelete((byte)0)
                .price(vegetablePrice.getPrice())
                .vegetablePriceId(addCompanyGoodsVo.getVegetablePriceId())
                .build();
        companyVegetableMapper.insert(companyVegetable);
    }

    @Override
    public void deleteCompanyGoods(AddCompanyGoodsVo addCompanyGoodsVo) {
        companyVegetableMapper.deleteCompanyGoods(addCompanyGoodsVo.getVegetablePriceId(),addCompanyGoodsVo.getCompanyId());
    }
}
