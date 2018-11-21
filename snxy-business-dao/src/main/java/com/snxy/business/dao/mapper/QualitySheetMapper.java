package com.snxy.business.dao.mapper;

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

    QualitySheet selectByOrderId(Long deliveryOrderId);

    List<QualitySheet> selectByCompanyId(@Param("companyId") String companyId);

    List<QualitySheet> selectQualitySheetList(@Param("useId") Long useId,@Param("searchName") String searchName);

    List<QualitySheet> selectAllQualitySheetList(@Param("useId") Long useId, @Param("searchName") String searchName);

    List<QualitySheet> selectAllQualitySheetListPart(@Param("useId") Long useId, @Param("searchName") String searchName);

    int deleteQualitySheetById(String  qualitySheetId);

}