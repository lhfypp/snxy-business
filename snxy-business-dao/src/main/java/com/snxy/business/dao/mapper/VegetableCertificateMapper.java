package com.snxy.business.dao.mapper;

import com.snxy.business.domain.Valication;
import com.snxy.business.domain.VegetableCertificate;

import java.util.List;

public interface VegetableCertificateMapper {
    int deleteByPrimaryKey(Long id);

    int insert(VegetableCertificate record);

    int insertSelective(VegetableCertificate record);

    VegetableCertificate selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VegetableCertificate record);

    int updateByPrimaryKey(VegetableCertificate record);

    void insertImageList(List<VegetableCertificate> vegetableCertificateList);

    List<VegetableCertificate> selectByOrderId(Long deliveryOrderId);
    List<Valication> selectListByOrderId(Long deliveryOrderId);
}