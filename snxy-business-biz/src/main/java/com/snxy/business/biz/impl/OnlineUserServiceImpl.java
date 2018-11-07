package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.OnlineUserMapper;
import com.snxy.business.domain.OnlineUser;
import com.snxy.business.service.OnlineUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class OnlineUserServiceImpl implements OnlineUserService {
    @Resource
    private OnlineUserMapper onlineUserMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateName(Long systemUserId, String name) {
        onlineUserMapper.updateNameBySystemUserId(systemUserId,name);
    }

    @Override
    public OnlineUser selectByPhone(String phone) {
        OnlineUser onlineUser = onlineUserMapper.selectByPhone(phone);
        return onlineUser;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertOnlineUser(OnlineUser onlineUser) {
        onlineUserMapper.insertOnlineUser(onlineUser);
    }

    @Override
    public List<OnlineUser> selectByPhoneList(List<String> phoneList) {
        List<OnlineUser> onlineUserList = onlineUserMapper.selectByPhoneList(phoneList);
        return onlineUserList;
    }

    @Override
    public void insertOnlineUserList(List<OnlineUser> onlineUserList) {
        onlineUserMapper.insertOnlineUserList(onlineUserList);
    }

    @Override
    public List<OnlineUser> selectByOnlineUserIdList(List<Long> onlineUserIdList) {
        List<OnlineUser> onlineUserList = onlineUserMapper.selectByOnlineUserIdList(onlineUserIdList);
        return onlineUserList;
    }
}
