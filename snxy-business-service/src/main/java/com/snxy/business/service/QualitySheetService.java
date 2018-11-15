package com.snxy.business.service;

import com.snxy.business.domain.QualitySheet;
import com.snxy.common.util.PageInfo;


public interface QualitySheetService {
    QualitySheet qualitySheetByOrderId(Long deliveryOrderId);

    PageInfo<QualitySheet> qualitySheetList(Long onlineUserId);
}
