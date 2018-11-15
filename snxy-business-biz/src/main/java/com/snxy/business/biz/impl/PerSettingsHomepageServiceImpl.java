package com.snxy.business.biz.impl;


import com.snxy.business.dao.mapper.MerchantCompanyMapper;
import com.snxy.business.domain.CompanyPartInfo;
import com.snxy.business.domain.CompanyUserRelation;
import com.snxy.business.service.CompanyUserRelationService;
import com.snxy.business.service.PerSettingsHomepageService;
import com.snxy.business.service.vo.CompanyVO;
import com.snxy.common.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
@Slf4j
public class PerSettingsHomepageServiceImpl implements PerSettingsHomepageService {
    @Resource
    private MerchantCompanyMapper MerchantCompanyMapper;
    @Resource
    private CompanyUserRelationService companyUserRelationService;
    @Override
    //设置所属商户(搜索)
    public List<CompanyVO>searchCompanyVO(String companyName) {
        //查询出所有的公司
        List<CompanyVO>CompanyVOList=new ArrayList<>();
        List<CompanyPartInfo> companyPartInfoList=MerchantCompanyMapper.selectAllComInfoByComName(companyName);

       for(CompanyPartInfo companyPartInfo:companyPartInfoList){
           CompanyVO companyVO=new CompanyVO();
           BeanUtils.copyProperties(companyPartInfo,companyVO);
           CompanyVOList.add(companyVO);
       }
        return CompanyVOList;
    }
    //选择加入商户
    @Override
    public String saveJoinTheMerchants(long userId, long companyId)   {
        //查询数据库有没有数据
        Long companyUserRelationId=companyUserRelationService.selectCompanyRelationforId(userId,companyId);
        if(companyUserRelationId!=null){
            throw new BizException("加入商户成功,不要重复提交!");
        }
        Date now =new Date();
        //把该用户信息存入company_user_relation表
        CompanyUserRelation CompanyUserRelation=null;
        CompanyUserRelation= CompanyUserRelation.builder()
                .companyId(companyId)
                .onlineUserId(userId)
                .gmtCreate(now)
                .gmtModifed(null)
                .isResponsible(0)
                .isDelete((byte)0)
                .build();
        int result=companyUserRelationService.insert(CompanyUserRelation);
        if(result!=1){
            throw new BizException("选择加入商户失败");
        }
        return "选择加入商户成功";
    }

}
