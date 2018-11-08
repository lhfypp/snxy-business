package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.OnlineUserMapper;
import com.snxy.business.service.OnlineUserService;
import com.snxy.common.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OnlineUserServiceImpl implements OnlineUserService {
    @Resource
    private OnlineUserMapper OnlineUserMapper;
    @Override
    //获取 onlineUserId
    public long searchOnlineUserId(String SystemUserId) {
        Long onlineUserId=OnlineUserMapper.selectIdBySystemUserID(SystemUserId);
        if(onlineUserId==null){
            throw new BizException("没有该用户信息");
        }
        return onlineUserId;
    }
}
