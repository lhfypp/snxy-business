package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.CompanyUserRelationMapper;
import com.snxy.business.domain.CompanyUserRelation;
import com.snxy.business.domain.MerchantCompany;
import com.snxy.business.domain.OnlineUser;
import com.snxy.business.service.CompanyUserRelationService;

import com.snxy.business.service.OnlineUserService;
import com.snxy.business.service.UserImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;




import org.springframework.transaction.annotation.Transactional;


import java.util.List;


@Service
@Slf4j
public class CompanyUserRelationServiceImpl implements CompanyUserRelationService {
    @Resource
    private CompanyUserRelationMapper companyUserRelationMapper;
    @Resource
    private UserImageService userImageService;
    @Resource
    private OnlineUserService onlineUserService;

    @Override
    public int insert(CompanyUserRelation record) {
        return companyUserRelationMapper.insert(record);

    }



    /**
     * 根据在线id查询商户我的公司信息
     * @param onlineUserId
     * @return
     */
    @Override
    public MerchantCompany selectBossCompanyByUserId(Long onlineUserId ) {
        return companyUserRelationMapper.selectBossCompanyByUserId(onlineUserId);
    }

    /**
     * 商户删除员工
     * @param onlineUserId
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteCompanyUserRelationByOnlineUserId(Long onlineUserId) {
       companyUserRelationMapper.deleteCompanyUserRelationByOnlineUserId(onlineUserId);
    }

    /**
     * 根据onlineUserId查询员工所属公司的信息
     * @param onlineUserId
     * @return
     */
    @Override
    public MerchantCompany selectEmployCompanyByUserId(Long onlineUserId) {
        return companyUserRelationMapper.selectEmployCompanyByUserId(onlineUserId);
    }

    /**
     * 根据onlineUserId查询companyId
     * @param onlineUserId
     * @return
     */
    @Override
    public List<Long> selectCompanyIdByOnlineUserId(Long onlineUserId) {

        return companyUserRelationMapper.selectCompanyIdByOnlineUserId(onlineUserId);
    }



    @Override
    public CompanyUserRelation selectByOnlineUserId(Long onlineUserId) {
        CompanyUserRelation companyUserRelation = companyUserRelationMapper.selectByOnlineUserId(onlineUserId);
        return companyUserRelation;
    }

    @Override
    public OnlineUser selectUserNameByOlineUserId(Long onlineUserId) {
        OnlineUser onlineUser = onlineUserService.selectByOnlineUserId(onlineUserId);
        return onlineUser;}

    public List<CompanyUserRelation> selectFounderByCompanyId(Long companyId) {
        List<CompanyUserRelation> companyUserRelationList = companyUserRelationMapper.selectFounderByCompanyId(companyId);
        return companyUserRelationList;
    }


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

    public Long selectCompanyRelationforId(Long userId, long companyId) {
        return companyUserRelationMapper.selectByCompanyIdAndUserId(userId,companyId);
    }

    @Override
    public String searchResponsibleByComId(Long companyId) {

        return companyUserRelationMapper.selectResponsibleBycomId(companyId);
    }

    @Override
    public String searchComIdByUseId(Long userId) {
        return null;
    }
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
