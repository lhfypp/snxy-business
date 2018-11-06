package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.OnlineUserMapper;
import com.snxy.business.service.OnlineUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OnlineUserServiceImpl implements OnlineUserService {
    @Resource
    private OnlineUserMapper onlineUserMapper;

    @Override
    public Long selectOnlineIdBySystemId(Long systemUserId) {
        return onlineUserMapper.selectOnlineIdBySystemId(systemUserId);
    }

    @Override
    public void updateNameById(Long onlineUserId, String userName) {
        onlineUserMapper.updateNameById(onlineUserId,userName);
    }

}
