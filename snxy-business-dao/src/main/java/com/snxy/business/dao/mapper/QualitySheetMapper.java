package com.snxy.business.dao.mapper;

import com.snxy.business.domain.QualitySheet;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QualitySheetMapper {
    int deleteByPrimaryKey(Long id);

    int insert(QualitySheet record);

    int insertSelective(QualitySheet record);

    QualitySheet selectByPrimaryKey(Long id,String code);

    int updateByPrimaryKeySelective(QualitySheet record);

    int updateByPrimaryKey(QualitySheet record);

    QualitySheet selectByOrderId(Long deliveryOrderId);

    List<QualitySheet> selectByCompanyId(@Param("companyId") Long companyId);
}