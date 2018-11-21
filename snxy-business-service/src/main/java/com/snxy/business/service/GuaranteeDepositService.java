package com.snxy.business.service;

import com.snxy.business.domain.GuaranteeDeposit;

import java.util.List;

public interface GuaranteeDepositService {
    List<Long> searchOrderIds();
    int updateIsGenerateQuality(Long orderId);

    GuaranteeDeposit selectGuaranteeByOrderNo(String orderNo);

    GuaranteeDeposit selectByDeliveryOrderId(Long id);
}
