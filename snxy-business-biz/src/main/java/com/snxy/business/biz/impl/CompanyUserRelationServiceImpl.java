package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.CompanyUserRelationMapper;
import com.snxy.business.service.CompanyUserRelationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
@Slf4j
public class CompanyUserRelationServiceImpl implements CompanyUserRelationService {

    @Resource
    private CompanyUserRelationMapper companyUserRelationMapper;

    @Override
    public List selectCompanyIsExist(Long onlineUserId) {
        List<Long> longs = companyUserRelationMapper.selectCompanyByOnlineId(onlineUserId);
        return longs;
    }

    @Override
    public List selectByOnlineId(Long id) {
        List<Long> list = companyUserRelationMapper.selectCompanyByOnlineId(id);
        return list;
    }

    @Override
    public long selectCompanyId(Long onlineUserId) {
        return companyUserRelationMapper.selectCompanyId(onlineUserId);
    }

    @Override
    public List<Long> selectOnlinUserId(Long companyId) {
        return companyUserRelationMapper.selectOnlinUserId(companyId);
    }
}
