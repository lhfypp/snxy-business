package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.OrderLogMapper;
import com.snxy.business.domain.OrderLog;
import com.snxy.business.service.OrderLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderLogServiceImpl implements OrderLogService {
    @Resource
    private OrderLogMapper orderLogMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertLog(OrderLog orderLog) {
        orderLogMapper.insertSelective(orderLog);
    }
}
