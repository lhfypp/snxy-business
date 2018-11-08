package com.snxy.business.biz;

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
public class UpdateSystemUserName {
    /**
     * 更换系统用户
     */
    @Resource
    private SystemUserService systemUserService;
    @Test
    public void updateSystemUserName1(){
        systemUserService.updateName(1L,"小李子");
    }
}
