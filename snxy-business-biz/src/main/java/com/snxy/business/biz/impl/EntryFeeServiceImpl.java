package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.EntryFeeMapper;
import com.snxy.business.domain.EntryFee;
import com.snxy.business.service.EntryFeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Slf4j
public class EntryFeeServiceImpl implements EntryFeeService {

    @Resource
    private EntryFeeMapper entryFeeMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateByOrderNo(EntryFee entryFee) {
        entryFeeMapper.updateByOrderNo(entryFee);
    }
}
