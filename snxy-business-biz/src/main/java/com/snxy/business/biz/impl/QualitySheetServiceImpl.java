package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.QualitySheetMapper;
import com.snxy.business.domain.QualitySheet;
import com.snxy.business.service.QualitySheetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Slf4j
public class QualitySheetServiceImpl implements QualitySheetService {

    @Resource
    private QualitySheetMapper qualitySheetMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(QualitySheet qualitySheet) {
        qualitySheetMapper.insert(qualitySheet);
    }
}
