package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.ShortMessageMapper;
import com.snxy.business.domain.ShortMessage;
import com.snxy.business.service.ShortMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Slf4j
public class ShortMessageServiceImpl implements ShortMessageService {
    @Resource
    private ShortMessageMapper shortMessageMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertMessage(ShortMessage shortMessage) {
        shortMessageMapper.insertSelective(shortMessage);
    }
}
