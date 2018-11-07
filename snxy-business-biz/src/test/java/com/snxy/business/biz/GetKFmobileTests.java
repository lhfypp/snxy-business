package com.snxy.business.biz;

import com.snxy.business.service.SystemUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class GetKFmobileTests {
    @Resource
    private SystemUserInfoService systemUserInfoService;

    /**
     * 获取客服电话
     */
    @Test
    public void getLFmobile(){
        String mobile = systemUserInfoService.selectMobileBySystemUserId(5L);
       System.out.print("======================="+mobile);
    }
}
