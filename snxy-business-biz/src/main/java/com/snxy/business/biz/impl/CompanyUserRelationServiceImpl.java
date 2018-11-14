package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.CompanyUserRelationMapper;
import com.snxy.business.domain.CompanyUserRelation;
import com.snxy.business.service.CompanyUserRelationService;
import com.snxy.business.service.vo.ChangePrincipleVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class CompanyUserRelationServiceImpl implements CompanyUserRelationService {
    @Resource
    private CompanyUserRelationMapper companyUserRelationMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertCompanyUserRelation(CompanyUserRelation companyUserRelation) {
        companyUserRelationMapper.insert(companyUserRelation);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertCompanyRelation(CompanyUserRelation companyUserRelation) {
        companyUserRelationMapper.insertCompanyRelation(companyUserRelation);
    }

    @Override
    public CompanyUserRelation selectCompanyUserRelation(Long id) {
        CompanyUserRelation companyUserRelation = companyUserRelationMapper.selectByOnlineUserId(id);
        return companyUserRelation;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertCompanyRelationList(List<CompanyUserRelation> companyUserRelationList) {
        companyUserRelationMapper.insertCompanyRelationList(companyUserRelationList);
    }

    @Override
    public List<CompanyUserRelation> selectAllByCompanyId(Long companyId) {
        List<CompanyUserRelation> companyUserRelationList = companyUserRelationMapper.selectAllByCompanyId(companyId);
        return companyUserRelationList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateIsResponsible(List<CompanyUserRelation> companyUserRelationList) {
        companyUserRelationMapper.updateByCompanyUserRelationList(companyUserRelationList);
    }

    @Override
    public List<CompanyUserRelation> selectUserRelationByOnlineUserIdList(List<Long> onlineUserIdList) {
        List<CompanyUserRelation> companyUserRelationList = companyUserRelationMapper.selectUserRelationByOnlineUserIdList(onlineUserIdList);
        return companyUserRelationList;
    }

    @Override
    public CompanyUserRelation selectUserRelationByCompanyId(Long companyId) {
        CompanyUserRelation companyUserRelation = companyUserRelationMapper.selectByCompanyId(companyId);
        return companyUserRelation;
    }
}
