package com.snxy.business.biz;

import com.snxy.business.service.UserImageService;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class updateUserImageTests {

    @Resource
    private UserImageService userImageService;

    /*@Test
    public void updateUserImage1(){
        userImageService.updateImageById(1L,);
    }*/
}
