package com.snxy.business.biz.impl;

import com.snxy.business.service.SystemUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UpdatePersonalPwdTests {

    @Resource
    private SystemUserService systemUserService;
    @Test
    @Transactional(rollbackFor = Exception.class)
    public void updatePersonalPwd(){
        systemUserService.updatePersonalPassWord("234","123",45L);
    }
}
