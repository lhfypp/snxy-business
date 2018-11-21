package com.snxy.business.service;

import com.snxy.business.service.vo.*;
import com.snxy.common.util.PageInfo;

import java.util.Date;
import java.util.List;

public interface PersonalCenterService {

    CustomerVO myCustomer(Long systemUserId);

    PageInfo<List<tradeVO>> tradeList( Long businessTypeId, Date startTime, Date endTime, Integer pageNum, Integer pageSize);

    TradeInfoVO tradeInfo(String orderNo);

    PersonalVO personalInfo(Long systemUserId);

    TradeAnalysisVO tradeAnalysis(Date  startTime, Date endTime);

    AccountVO selectAccount(Long systemUserId);

}
