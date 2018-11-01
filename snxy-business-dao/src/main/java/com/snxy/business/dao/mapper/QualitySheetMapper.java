package com.snxy.business.dao.mapper;

import com.snxy.business.domain.CheckBillInfo;
import com.snxy.business.domain.QualitySheet;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QualitySheetMapper {
    int deleteByPrimaryKey(Long id);

    int insert(QualitySheet record);

    int insertSelective(QualitySheet record);

    QualitySheet selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(QualitySheet record);

    int updateByPrimaryKey(QualitySheet record);

    List<CheckBillInfo> selectCheckBillList(@Param("currList") List<Long> currList);

    CheckBillInfo selectCheckBillById(String deliveryOrderId);

    List<CheckBillInfo> selectCheckBillByName(@Param("vegetableCategoryName") String vegetableCategoryName);

}