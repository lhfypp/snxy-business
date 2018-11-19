package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.EntryFeeMapper;
import com.snxy.business.domain.EntryFee;
import com.snxy.business.service.EntryFeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class EntryFeeServiceImpl implements EntryFeeService {
    @Resource
    private EntryFeeMapper entryFeeMapper;
    @Override
    public EntryFee selectByDeliveryOrderId(Long deliveryOrderId) {
        EntryFee entryFee = entryFeeMapper.selectByDeliveryOrderId(deliveryOrderId);
        return entryFee;
    }
}
