package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.CompanyUserRelationMapper;
import com.snxy.business.domain.CompanyUserRelation;
import com.snxy.business.service.CompanyUserRelationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class CompanyUserRelationServiceImpl implements CompanyUserRelationService {
    @Resource
    private CompanyUserRelationMapper companyUserRelationMapper;
    @Override
    public int insert(CompanyUserRelation record) {
       return  companyUserRelationMapper.insert(record);
    }
}
