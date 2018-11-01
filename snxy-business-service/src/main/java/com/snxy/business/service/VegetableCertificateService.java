package com.snxy.business.service;

import com.snxy.business.domain.Valication;
import com.snxy.business.domain.VegetableCertificate;
import java.util.List;

public interface VegetableCertificateService {
    void insertImageList(List<VegetableCertificate> vegetableCertificateList);
    List<Valication> getValications(long orderId);
	List<VegetableCertificate> selectByOrderId(Long deliveryOrderId);
}
