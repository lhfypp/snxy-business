package com.snxy.business.dao.mapper;

import com.snxy.business.domain.VegetableCertificate;

public interface VegetableCertificateMapper {
    int deleteByPrimaryKey(Long id);

    int insert(VegetableCertificate record);

    int insertSelective(VegetableCertificate record);

    VegetableCertificate selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VegetableCertificate record);

    int updateByPrimaryKey(VegetableCertificate record);
}