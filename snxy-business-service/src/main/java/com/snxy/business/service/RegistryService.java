package com.snxy.business.service;

import com.snxy.business.domain.IdentityType;
import com.snxy.business.domain.UserIdentity;
import com.snxy.user.agent.service.vo.LoginUserVO;
import com.snxy.user.agent.service.vo.SystemUserVO;

import java.util.List;

/**
 * Created by 24398 on 2018/9/19.
 */
public interface RegistryService {
    void getRegistrySmsCode(String mobile);

    void checkSmsCodeAndRegister(String mobile, String smsCode);

    void changeInitPassword(Long systemUserId, String password);

    void saveIdentityAndName(Long onlineUserId, String name, List<Integer> integers);

    List<IdentityType> listIdentity();


    SystemUserVO getToken(LoginUserVO loginUserVO);
}
