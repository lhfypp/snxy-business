package com.snxy.business.biz.impl;

import com.snxy.business.service.IdentityTypeService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class InsertIdentityTests {
    @Resource
    private IdentityTypeService identityTypeService;
    @Test
    public void insertIdentity(){
        identityTypeService.insertIdentity(4,30L);
    }
}
