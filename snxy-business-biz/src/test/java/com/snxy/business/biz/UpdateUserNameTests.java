package com.snxy.business.biz;

import com.snxy.business.service.OnlineUserService;
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
public class UpdateUserNameTests {
    /**
     * 更换系统用户
     */
    @Resource
    private SystemUserService systemUserService;
    @Resource
    private OnlineUserService onlineUserService;
    @Test
    public void updateSystemUserName1(){

        systemUserService.updateName(1L,"小李子");

    }
    /**
     * 更换在线用户姓名
     */
    @Test
    public void updateOnlineUserName2(){
        onlineUserService.updateName(1L,"王超");
    }



}
