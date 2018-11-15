package com.snxy.business.biz.impl;

import com.github.pagehelper.PageHelper;
import com.snxy.business.dao.mapper.CompanyUserRelationMapper;
import com.snxy.business.dao.mapper.QualitySheetMapper;
import com.snxy.business.domain.CompanyUserRelation;
import com.snxy.business.domain.QualitySheet;
import com.snxy.business.service.QualitySheetService;
import com.snxy.common.util.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class QualitySheetServiceImpl implements QualitySheetService {

    @Resource
    private QualitySheetMapper qualitySheetMapper;
    @Resource
    private CompanyUserRelationMapper companyUserRelationMapper;

    //检测单详情
    @Override
    public QualitySheet qualitySheetByOrderId(Long deliveryOrderId) {
        QualitySheet qualitySheet = qualitySheetMapper.selectByOrderId(deliveryOrderId);
        if ("1".equals(qualitySheet.getStatus())){
            qualitySheet.setStatus("检测中");
        }else if ("2".equals(qualitySheet.getStatus())){
            qualitySheet.setStatus("已完成");
        }
        return qualitySheet;
    }

    //查看质量检测单
    @Override
    public PageInfo<QualitySheet> qualitySheetList(Long onlineUserId) {
        //获取公司id
        CompanyUserRelation companyUserRelation = companyUserRelationMapper.selectByOnlineUserId(onlineUserId);
        Long companyId = companyUserRelation.getCompanyId();
        //查看质量检测单
        PageHelper.startPage(1,10);
        List<QualitySheet> qualitySheetList = qualitySheetMapper.selectByCompanyId(companyId);
        PageInfo<QualitySheet> qualitySheetPageInfo = new PageInfo<>();
        qualitySheetPageInfo.setData(qualitySheetList);
        return qualitySheetPageInfo;
    }
}
