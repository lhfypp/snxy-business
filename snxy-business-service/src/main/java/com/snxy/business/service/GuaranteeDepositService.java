package com.snxy.business.service;

import java.util.List;

public interface GuaranteeDepositService {
    List<Long> searchOrderIds();
    int updateIsGenerateQuality(Long orderId);
}
