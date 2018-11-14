package com.snxy.business.biz.impl;

import com.snxy.business.service.SystemUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class GetUpdatePwdSmsCodeTests {
    @Resource
    private SystemUserService systemUserService;

    /**
     * 改密码前获取验证码
     */
    @Test
    public void getPwdSmsCode(){
        String smsCode = systemUserService.updatePwdGetSmsCode("13611163210");
        System.out.println("================"+smsCode);
    }

    /**
     * 修改密码
     */
    @Test
    public void updatePWD(){
        systemUserService.updatePwd("13611163210","741634","123");
    }



}
