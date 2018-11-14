package com.snxy.business.biz.impl;

import com.snxy.business.biz.asy.AsyRefreshCacheUser;
import com.snxy.business.biz.feign.UserAgentService;
import com.snxy.business.domain.UserIdentity;
import com.snxy.business.service.OnlineUserService;
import com.snxy.business.service.RegisterService;
import com.snxy.business.service.SystemUserService;
import com.snxy.business.service.UserIdentityService;
import com.snxy.common.exception.BizException;
import com.snxy.common.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service
@Slf4j
public class RegisterServiceImpl implements RegisterService {
    @Resource
    private OnlineUserService onlineUserService;
    @Resource
    private SystemUserService systemUserService;
    @Resource
    private UserIdentityService userIdentityService;
    @Resource
    private AsyRefreshCacheUser asyRefreshCacheUser;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void chooseIdentity(Long systemUserId, String name, Integer identityId, Long onlineUserId){
        if(identityId!=5){
            if (name!=null&&!name.equals("")) {
                systemUserService.updateName(systemUserId, name);

                asyRefreshCacheUser.refreshCacheUser(systemUserId);
                log.info("当前线程名   ："+Thread.currentThread().getName());
                onlineUserService.updateName(systemUserId, name);
            }else {
                throw new BizException("您还没有输入姓名，请先输入姓名。");
            }
        }
        UserIdentity userIdentity = UserIdentity.builder()
                    .identityId(identityId)
                    .onlineUserId(onlineUserId)
                    .build();

        UserIdentity userIdentity1 = userIdentityService.selectByOnlineUserId(userIdentity.getOnlineUserId(),userIdentity.getIdentityId());
        if(userIdentity1!=null){
            throw new BizException("已添加，请勿重复添加");
        }
        userIdentityService.insertIdentity(userIdentity);
    }
}
