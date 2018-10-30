package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.OnlineUserMapper;
import com.snxy.business.domain.OnlineUser;
import com.snxy.business.service.OnlineUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class OnlineUserServiceImpl implements OnlineUserService {

    @Resource
    private OnlineUserMapper onlineUserMapper;


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
    public List<String> searchphones(List<Long> onlineUserIds) {
        return onlineUserMapper.searchphones(onlineUserIds);
    }
}
