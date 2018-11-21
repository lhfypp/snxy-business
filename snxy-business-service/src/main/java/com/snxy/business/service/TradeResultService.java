package com.snxy.business.service;

import com.snxy.business.domain.TradeResult;
import com.snxy.business.service.vo.tradeVO;
import com.snxy.common.util.PageInfo;

import java.util.Date;
import java.util.List;

public interface TradeResultService {
    PageInfo<List<tradeVO>> tradeListAll( Long businessTypeId, Integer pageNum, Integer pageSize);

    PageInfo<List<tradeVO>> tradeListTime( Long businessTypeId, Date startTime, Date endTime, Integer pageNum, Integer pageSize);

    TradeResult selectTradeInfo(String orderNo);

    String selectOrderCount();

    String selectOrderCountTime(Date startTime, Date endTime);

    List<String> selectOrderList(Date startTime, Date endTime);

    String selectEntryCostTotalByOrderList(List<String> orderNoList);

    String selectQualityTotalByOrderListAndTypeId(List<String> orderNoList);

    String selectWeChatByOrderList(List<String> orderNoList);

    String selectAlipayByOrderList(List<String> orderNoList);
}
