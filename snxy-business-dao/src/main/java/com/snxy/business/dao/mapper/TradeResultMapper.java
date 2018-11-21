package com.snxy.business.dao.mapper;

import com.snxy.business.domain.TradeResult;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface TradeResultMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TradeResult record);

    int insertSelective(TradeResult record);

    TradeResult selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TradeResult record);

    int updateByPrimaryKey(TradeResult record);

    Long searchTradeNum(@Param("businessTypeId") Long businessTypeId);

    List<TradeResult> tradeListAll(@Param("businessTypeId") Long businessTypeId);

    List<TradeResult> tradeListTime(@Param("businessTypeId")Long businessTypeId,@Param("startTime") Date startTime,@Param("endTime") Date endTime);

    TradeResult selectTradeInfo(@Param("orderNo")String orderNo);

    String selectOrderCount();

    String selectOrderCountTime(@Param("startTime")Date startTime, @Param("endTime")Date endTime);

    List<String> selectOrderList(@Param("startTime") Date startTime, @Param("endTime")Date endTime);

    String selectQualityTotalByOrderListAndTypeId(@Param("orderNoList")List<String> orderNoList );

    String selectEntryCostTotalByOrderList(@Param("orderNoList")List<String> orderNoList);

    String selectWeChatByOrderList(@Param("orderNoList")List<String> orderNoList);

    String selectAlipayByOrderList(@Param("orderNoList")List<String> orderNoList);
}