package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.EntryFeeMapper;
import com.snxy.business.domain.EntryFee;
import com.snxy.business.service.EntryFeeService;
import com.snxy.common.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class EntryFeeServiceImpl implements EntryFeeService {
    @Resource
    private EntryFeeMapper entryFeeMapper;
    @Override
    public EntryFee selectFeeByOrderNo(String orderNo) {
        if (orderNo==null){
            throw new BizException("货运单号不能为空");
        }
        EntryFee entryFee = entryFeeMapper.selectFeeByOrderNo(orderNo);
        return entryFee;
    }

}
