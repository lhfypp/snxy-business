package com.snxy.business.biz;

import com.snxy.business.service.OnlineUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class GetSmsCodeTests {
    @Resource
    private OnlineUserService onlineUserService;

    /**
     * 测试更改手机号前获取验证码
     */
    @Test
    public void getSmsCode() {
        String smsCode = onlineUserService.getSmsCode("13611163216");

        System.out.println("=============================="+smsCode);
    }
    /**
     * 测试更改在线用户手机号
     */
    @Test
    public void updateOnlineMobileT(){
        onlineUserService.updateOnlineMobile(11L,"13611163216","13611163215","630176");
        System.out.println("==============================");
    }
}
