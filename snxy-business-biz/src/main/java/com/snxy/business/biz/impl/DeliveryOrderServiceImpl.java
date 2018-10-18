package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.DeliveryOrderMapper;
import com.snxy.business.dao.mapper.SystemUserInfoMapper;
import com.snxy.business.domain.BillInfo;
import com.snxy.business.domain.DeliveryOrder;
import com.snxy.business.service.DeliveryOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class DeliveryOrderServiceImpl implements DeliveryOrderService {
    @Autowired
    private DeliveryOrderMapper deliveryOrderMapper;
    @Autowired
    private SystemUserInfoMapper systemUserInfoMapper;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createDeliveryOrder(DeliveryOrder deliveryOrder) {

    }

    @Override
    public List<BillInfo> searchDeliveryOrder(Long useId, String orderStatus, String serchName) {

        //用于存放商户或者代办所有的手机信息
        List<String>sendPhones=new ArrayList<String>();
        sendPhones= systemUserInfoMapper.searchPhones(useId);
        return  deliveryOrderMapper.searchDeliveryOrder(sendPhones,orderStatus,serchName);
    }
}
