package com.snxy.business.service;

import com.snxy.business.domain.EntryFee;


public interface EntryFeeService {
    EntryFee selectFeeByOrderNo(String orderNo);

    EntryFee selectByDeliveryOrderId(Long deliveryOrderId);
}
