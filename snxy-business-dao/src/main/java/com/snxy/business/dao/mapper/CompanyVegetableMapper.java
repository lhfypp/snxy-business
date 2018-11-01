package com.snxy.business.dao.mapper;

import com.snxy.business.domain.CompanyVegetable;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CompanyVegetableMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CompanyVegetable record);

    int insertSelective(CompanyVegetable record);

    CompanyVegetable selectByPrimaryKey(Long id);

    List<Long> selectVegetablePriceIdByCompanyId(Long userCompanyId);

    CompanyVegetable selectCompanyGoodsByGoodsNameAndCompanyId(@Param("goodsName") String goodsName, @Param("companyId") Long companyId);

    int updateByPrimaryKeySelective(CompanyVegetable record);

    int updateByPrimaryKey(CompanyVegetable record);

    List<CompanyVegetable> showGoodsList(Long companyId);

    void deleteCompanyGoods(@Param("vegetablePriceId") Long vegetablePriceId, @Param("companyId") Long companyId);
}