package com.snxy.business.biz.impl;

import com.alibaba.druid.sql.dialect.mysql.ast.MysqlForeignKey;
import com.snxy.business.dao.mapper.CompanyUserRelationMapper;
import com.snxy.business.domain.*;
import com.snxy.business.service.CompanyUserRelationService;
import com.snxy.business.service.OnlineUserService;
import com.snxy.business.service.UserIdentityService;
import com.snxy.business.service.vo.ChangePrincipleVo;
import com.snxy.business.service.vo.CompanyUserListVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
@Slf4j
public class CompanyUserRelationServiceImpl implements CompanyUserRelationService {

    @Resource
    private CompanyUserRelationMapper companyUserRelationMapper;
    @Resource
    private OnlineUserService onlineUserService;
    @Resource
    private UserIdentityService userIdentityService;

    @Override
    public Long selectCompanyIdByOnlineUserId(Long onlineUserId) {
        Long companyId = companyUserRelationMapper.selectCompanyByOnlineId(onlineUserId);
        return companyId;
    }

    @Override
    public Long selectByOnlineId(Long id) {
        Long companyId = companyUserRelationMapper.selectCompanyByOnlineId(id);
        return companyId;
    }

    @Override
    public long selectCompanyId(Long onlineUserId) {
        return companyUserRelationMapper.selectCompanyId(onlineUserId);
    }

    @Override
    public List<Long> selectOnlinUserId(Long companyId) {
        return companyUserRelationMapper.selectOnlinUserId(companyId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void changePrinciple(ChangePrincipleVo changePrincipleVo) {
        companyUserRelationMapper.changePrinciple(changePrincipleVo.getCompanyId(),changePrincipleVo.getOnlineUserId(),changePrincipleVo.getIsResponsible());
    }

    @Override
    public void insertCompany(CompanyUserRelation companyUserRelation) {
        companyUserRelationMapper.insert(companyUserRelation);
    }

    @Override
    public List<CompanyUserListVo> showEmployeeList(Long companyId) {
        List<CompanyUserListVo> companyUserListVoList = new ArrayList<>();
        List<Long> onlineUserIdList = companyUserRelationMapper.selectOnlinUserId(companyId);
        List<OnlineUser> onlineUserList = onlineUserService.selectByOnlineUserIdList(onlineUserIdList);
        List<Identy> identyList = userIdentityService.selectUserIdentyByOnlineUserIdList(onlineUserIdList);

        for (int i = 0; i < onlineUserIdList.size(); i++) {
            List<Identy> identies = new ArrayList<>();
            for (int i1 = 0; i1 < identyList.size(); i1++) {
                if(onlineUserIdList.get(i).equals(identyList.get(i1).getOnlineUserId())){
                    identies.add(identyList.get(i1));
                }
            }
            CompanyUserListVo companyUserListVo = CompanyUserListVo.builder()
                    .identyList(identies)
                    .onlineUserId(onlineUserIdList.get(i))
                    .build();
            companyUserListVoList.add(companyUserListVo);
        }

        for (int i = 0; i < onlineUserList.size(); i++) {
            for (int i1 = 0; i1 < companyUserListVoList.size(); i1++) {
                if(onlineUserList.get(i).getId().equals(companyUserListVoList.get(i1).getOnlineUserId())){
                    companyUserListVoList.get(i1).setName(onlineUserList.get(i).getName());
                    companyUserListVoList.get(i1).setPhone(onlineUserList.get(i).getPhone());
                }
            }
        }

        return companyUserListVoList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void newEmployee(CompanyUserListVo companyUserListVo) {
        OnlineUser onlineUser = onlineUserService.selectByPhone(companyUserListVo.getPhone());
        if(onlineUser==null){
            OnlineUser onlineUser1 = OnlineUser.builder()
                    .name(companyUserListVo.getName())
                    .phone(companyUserListVo.getPhone())
                    .isDelete((byte)0)
                    .build();
            onlineUserService.insertOnlineUser(onlineUser1);

            insertNewEmployee(companyUserListVo,onlineUser1.getId());
        }else {
            insertNewEmployee(companyUserListVo,onlineUser.getId());
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateCompanyUser(CompanyUserListVo companyUserListVo) {
        CompanyUserRelation companyUserRelation = CompanyUserRelation.builder()
                .onllineUserId(companyUserListVo.getOnlineUserId())
                .companyType(companyUserListVo.getCompanyType())
                .companyId(companyUserListVo.getCompanyId())
                .gmtModifed(new Date())
                .build();
        companyUserRelationMapper.updateByOnlineUserId(companyUserRelation);

        //用户信息更改onlineUser表的信息更改
        OnlineUser onlineUser = OnlineUser.builder()
                .id(companyUserListVo.getOnlineUserId())
                .name(companyUserListVo.getName())
                .phone(companyUserListVo.getPhone())
                .build();
        onlineUserService.updateOnlineUser(onlineUser);

        List<Identy> identyList = companyUserListVo.getIdentyList();
        List<UserIdentity> userIdentityList = new ArrayList<>();
        for (int i = 0; i < identyList.size(); i++) {
            UserIdentity userIdentity = new UserIdentity();
            BeanUtils.copyProperties(identyList.get(i),userIdentity);
            userIdentity.setOnlineUserId(companyUserListVo.getOnlineUserId());
            userIdentityList.add(userIdentity);
        }
        System.out.println(userIdentityList);
        userIdentityService.updateByOnlineUserId(companyUserListVo.getOnlineUserId(),(byte)1);
        userIdentityService.insertUserIdentyList(userIdentityList);
    }

    @Override
    public void deleteEmployee(Long onlineUserId) {
        companyUserRelationMapper.deleteByOnlineUserId(onlineUserId,(byte)1);
        userIdentityService.updateByOnlineUserId(onlineUserId,(byte)1);
    }

    /*
    * 新建员工时，进行批量插入的方法抽取
    * */
    public void insertNewEmployee(CompanyUserListVo companyUserListVo,Long onlineUserId){
        CompanyUserRelation companyUserRelation = CompanyUserRelation.builder()
                .onllineUserId(onlineUserId)
                .gmtCreate(new Date())
                .isDelete((byte)0)
                .companyId(companyUserListVo.getCompanyId())
                .companyType(companyUserListVo.getCompanyType())
                .build();
        companyUserRelationMapper.insert(companyUserRelation);

        List<Identy> identyList = companyUserListVo.getIdentyList();
        List<UserIdentity> userIdentityList = new ArrayList<>();
        for (int i = 0; i < identyList.size(); i++) {
            UserIdentity userIdentity = new UserIdentity();
            userIdentity.setIdentityId(identyList.get(i).getIdentityId());
            userIdentity.setOnlineUserId(onlineUserId);
            userIdentity.setIsDelete((byte)0);
            userIdentityList.add(userIdentity);
        }
        userIdentityService.insertUserIdentyList(userIdentityList);
    }






}
