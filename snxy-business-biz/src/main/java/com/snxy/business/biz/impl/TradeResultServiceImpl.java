package com.snxy.business.biz.impl;

import com.github.pagehelper.PageHelper;
import com.snxy.business.dao.mapper.TradeResultMapper;
import com.snxy.business.domain.TradeResult;
import com.snxy.business.service.DeliveryOrderService;
import com.snxy.business.service.TradeResultService;
import com.snxy.business.service.vo.tradeVO;
import com.snxy.common.exception.BizException;
import com.snxy.common.util.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TradeResultServiceImpl implements TradeResultService {
    @Resource
    private TradeResultMapper tradeResultMapper;
    @Resource
    private DeliveryOrderService deliveryOrderService;

    @Override
    public PageInfo<List<tradeVO>> tradeListAll(Long businessTypeId, Integer pageNum, Integer pageSize) {
        //查询交易条数
        Long total = tradeResultMapper.searchTradeNum(businessTypeId);
        // 分页查询交易记录
        PageHelper.startPage(pageNum,pageSize);
        //带条件（businessTypeId）查询货运单里面车牌号
//        tradeResultMapper.selectTradeNoByTypeId(businessTypeId);
        //没有起止时间的交易记录
       List<TradeResult> tradeResults = tradeResultMapper.tradeListAll(businessTypeId);
        // 组装
        List<tradeVO> tradeVOS = tradeResults.parallelStream().map(tradeResult -> tradeVO.builder()
                .businessTypeId(tradeResult.getBusinessTypeId())
                .vegetableName(tradeResult.getBody())
//                .driverNumberList(driverNum)
                .tradeMethod(tradeResult.getTradeMethod())
                .totalFee(tradeResult.getTotalFee())
                .tradeTime(tradeResult.getTradeTime())
                .build()).collect(Collectors.toList());

        PageInfo<List<tradeVO>> pageInfo = new PageInfo(pageNum,pageSize,total,tradeResults);
        return pageInfo;
    }



    public PageInfo<List<tradeVO>> tradeListTime(  Long businessTypeId, Date startTime, Date endTime, Integer pageNum, Integer pageSize) {
        //查询交易条数
        Long total = tradeResultMapper.searchTradeNum(businessTypeId);
        // 分页查询交易记录
        PageHelper.startPage(pageNum,pageSize);
        //查询货运单里面车牌号
        //有起止时间的交易记录
        List<TradeResult> tradeResults = tradeResultMapper.tradeListTime(businessTypeId,startTime,endTime);
        // 组装
        List<tradeVO> tradeVOS = tradeResults.parallelStream().map(tradeResult -> tradeVO.builder()
                .businessTypeId(tradeResult.getBusinessTypeId())
                .vegetableName(tradeResult.getBody())
//                .driverNumberList(driverNumList)
                .tradeMethod(tradeResult.getTradeMethod())
                .totalFee(tradeResult.getTotalFee())
                .tradeTime(tradeResult.getTradeTime())
                .build()).collect(Collectors.toList());

        PageInfo<List<tradeVO>> pageInfo = new PageInfo(pageNum,pageSize,total,tradeResults);
        return pageInfo;
    }

    @Override
    public TradeResult selectTradeInfo(String orderNo) {
        if (orderNo==null){
            throw new BizException("货运单号不能为空");
        }
      TradeResult tradeResult = tradeResultMapper.selectTradeInfo(orderNo);
        if (tradeResult==null){
            throw new BizException("该货运单号不存在");
        }
        return tradeResult;
    }

    @Override
    public String selectOrderCount() {
        String orderCount = tradeResultMapper.selectOrderCount();
        return orderCount;
    }

    @Override
    public String selectOrderCountTime(Date startTime, Date endTime) {
        String orderCount = tradeResultMapper.selectOrderCountTime(startTime,endTime);
        return orderCount;
    }

    @Override
    public List<String> selectOrderList(Date startTime, Date endTime) {
        List<String> orderList = tradeResultMapper.selectOrderList(startTime,endTime);
        return orderList;
    }

    @Override
    public String selectEntryCostTotalByOrderList(List<String> orderNoList) {

        return  tradeResultMapper.selectEntryCostTotalByOrderList(orderNoList);
    }

    @Override
    public String selectQualityTotalByOrderListAndTypeId(List<String> orderNoList) {
        return tradeResultMapper.selectQualityTotalByOrderListAndTypeId(orderNoList);
    }

    @Override
    public String selectWeChatByOrderList(List<String> orderNoList) {

        return tradeResultMapper.selectWeChatByOrderList(orderNoList);
    }

    @Override
    public String selectAlipayByOrderList(List<String> orderNoList) {

        return tradeResultMapper.selectAlipayByOrderList(orderNoList);
    }

}
