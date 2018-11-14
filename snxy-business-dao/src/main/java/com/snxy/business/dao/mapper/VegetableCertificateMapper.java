package com.snxy.business.dao.mapper;

import com.snxy.business.domain.VegetableCertificate;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VegetableCertificateMapper {
    int deleteByPrimaryKey(Long id);

    int insert(VegetableCertificate record);

    int insertSelective(VegetableCertificate record);

    VegetableCertificate selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VegetableCertificate record);

    int updateByPrimaryKey(VegetableCertificate record);

    void insertCertificateList(@Param("vegetableCertificateList") List<VegetableCertificate> vegetableCertificateList);
}