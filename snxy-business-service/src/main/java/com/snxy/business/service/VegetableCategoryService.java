package com.snxy.business.service;

public interface VegetableCategoryService {
    void checkProductionCertificate(Long productionCertificate, Integer qualitied,Long orderNo);

    void checkQualityCertificate(Long qualityCertificateId, Integer qualitied,Long orderNo);
}
