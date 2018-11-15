package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.DeliveryOrderMapper;
import com.snxy.business.domain.DeliveryOrder;
import com.snxy.business.service.DeliveryOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class DeliveryOrderServiceImpl implements DeliveryOrderService {
    @Resource
    private DeliveryOrderMapper deliveryOrderMapper;
    @Override
    public DeliveryOrder searchDeliveryOrderById(Long id) {
        return deliveryOrderMapper.selectByPrimaryKey(id);
    }
}
