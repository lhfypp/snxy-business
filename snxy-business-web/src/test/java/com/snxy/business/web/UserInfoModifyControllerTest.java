package com.snxy.business.web;

import com.snxy.business.service.SystemUserService;
import com.snxy.business.service.UserImageService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserInfoModifyControllerTest {
    @Resource
    private UserImageService userImageService;
    @Resource
    private SystemUserService systemUserService;

    //修改姓名
    @Test
    public void modifyName(){
        systemUserService.modifyName("王五",1L);
    }

    //修改手机号
    @Test
    public void smsCode(){
        systemUserService.smsCode(1L,"13261420753");
    }

    @Test
    public void modifyPhone(){
        systemUserService.modifyPhone(1L,"123456");
    }

    //忘记密码
    @Test
    public void passwordReset(){
        systemUserService.passwordReset(1L);
    }

    @Test
    public void passwordCheckSmsCode(){
        systemUserService.checkSmsCode(1L,"123456");
    }

    @Test
    public void passwordResetNew(){
        systemUserService.passwordResetNew(1L,"13261455689");
    }

    //修改密码
    @Test
    public void passwordModify(){
        systemUserService.passwordModify(1L,"13261455689","123456");
    }
}
