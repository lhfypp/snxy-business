package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.OnlineUserMapper;
import com.snxy.business.service.OnlineUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

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
    public Long selectOnlineUserIdBySystemUserId(Long systemUserId) {
        Long onlineUserId = onlineUserMapper.selectOnlineUserIdBySystemUserId(systemUserId);
        return onlineUserId;
    }
}
