package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.OnlineUserMapper;
import com.snxy.business.domain.OnlineUser;
import com.snxy.business.service.CompanyUserRelationService;
import com.snxy.business.service.MerchantCompanyService;
import com.snxy.business.service.OnlineUserService;
import com.snxy.business.service.vo.MerchantCompanyVo;
import com.snxy.business.service.vo.ReceiveMessageVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class OnlineUserServiceImpl implements OnlineUserService {

    @Resource
    private OnlineUserMapper onlineUserMapper;
    @Resource
    private CompanyUserRelationService companyUserRelationService;
    @Resource
    private MerchantCompanyService merchantCompanyService;


    @Override
    public boolean newOnlineUser(OnlineUser onlineUser) {
        int n =  this.onlineUserMapper.insertSelective(onlineUser);
        if(n == 1){
            return  true;
        }
        return false;
    }

    @Override
    public OnlineUser selectById(Long onlineUserId) {
        OnlineUser onlineUser = this.onlineUserMapper.selectByPrimaryKey(onlineUserId);
        return onlineUser;
    }

    @Override
    public List<String> searchphones(List onlineUserIds) {
        return onlineUserMapper.searchphones(onlineUserIds);
    }

    @Override
<<<<<<< HEAD
    public OnlineUser selectBySystemUserId(long systemUserId) {
        return onlineUserMapper.selectBySystemUserId(systemUserId);
=======
    public List<OnlineUser> selectByOnlineUserIdList(List<Long> onlineUserIdList) {
        List<OnlineUser> onlineUserList = onlineUserMapper.selectByOnlineUserIdList(onlineUserIdList);
        return onlineUserList;
    }

    @Override
    public OnlineUser selectByPhone(String phone) {
        OnlineUser onlineUser = onlineUserMapper.selectByPhone(phone);
        return onlineUser;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertOnlineUser(OnlineUser onlineUser1) {
        onlineUserMapper.insert(onlineUser1);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateOnlineUser(OnlineUser onlineUser) {
        onlineUserMapper.updateByPrimaryKey(onlineUser);
    }

    @Override
    public String selectNameByOnlineUserId(Long onlineUserId) {
        OnlineUser onlineUser = onlineUserMapper.selectByPrimaryKey(onlineUserId);
        return onlineUser.getName();
    }

    @Override
    public ReceiveMessageVo selectByName(String name) {
        ReceiveMessageVo receiveMessageVo = new ReceiveMessageVo();
        OnlineUser onlineUser = onlineUserMapper.selectByName(name);
        BeanUtils.copyProperties(onlineUser,receiveMessageVo);
        Long onlineUserId = onlineUser.getId();
        long companyId = companyUserRelationService.selectCompanyId(onlineUserId);
        MerchantCompanyVo merchantCompanyVo = merchantCompanyService.selectByPrimaryKey(companyId);
        receiveMessageVo.setCompanyName(merchantCompanyVo.getMerchantName());

        return receiveMessageVo;
>>>>>>> d1aef324a1116338d372624d22129509a4e81ae8
    }
}
