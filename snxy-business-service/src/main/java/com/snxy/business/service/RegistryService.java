package com.snxy.business.service;

/**
 * Created by 24398 on 2018/9/19.
 */
public interface RegistryService {
    void getRegistrySmsCode(String mobile);

    void checkSmsCodeAndRegister(String mobile, String smsCode);

    void changeInitPassword(Long systemUserId, String password);
}
