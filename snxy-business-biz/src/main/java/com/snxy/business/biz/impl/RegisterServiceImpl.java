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
    public void chooseIdentity(Long systemUserId, String name, Integer identityId, Long onlineUserId) {
        if (StringUtil.isBlank(name)) {
            if (identityId==null||identityId.equals("")) {
                throw new BizException("您还没有完善信息，请先完善信息。");
            }
            throw new BizException("您还没有输入姓名，请先输入姓名。");
        } else {
            if (identityId==null||identityId.equals("")) {
                throw new BizException("您还没有选择身份，请先选择身份。");
            }
            systemUserService.updateName(systemUserId, name);
            asyRefreshCacheUser.refreshCacheUser(systemUserId);
            log.info("当前线程名   ："+Thread.currentThread().getName());

            onlineUserService.updateName(systemUserId, name);
            UserIdentity userIdentity = UserIdentity.builder()
                    .identityId(identityId)
                    .onlineUserId(onlineUserId)
                    .build();
            userIdentityService.insertIdentity(userIdentity);
        }
    }
}
