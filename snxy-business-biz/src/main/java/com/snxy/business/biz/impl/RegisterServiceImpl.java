package com.snxy.business.biz.impl;

import com.snxy.business.service.OnlineUserService;
import com.snxy.business.service.RegisterService;
import com.snxy.business.service.SystemUserService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

public class RegisterServiceImpl implements RegisterService {
    @Resource
    private OnlineUserService onlineUserService;
    @Resource
    private SystemUserService systemUserService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String chooseIdentity(Long systemUserId, String name, Integer identityId) {
        if (name.equals("") || name == null) {
            if (identityId.equals("") || identityId == null) {
                return "您还没有完善信息，请先完善信息。";
            }
            return "您还没有输入姓名，请先输入姓名。";
        }else {
            if (identityId.equals("") || identityId == null) {
                return "您还没有选择身份，请先选择身份。";
            }
            systemUserService.updateName(systemUserId, name);
            //onlineUserService.updateName();
            return "";
        }
    }
}
