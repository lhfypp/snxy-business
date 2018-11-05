package com.snxy.business.dao;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * Created by 24398 on 2018/9/25.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SystemUserMapperTest {

    @Resource
    private SystemUserMapper systemUserMapper;

    @Test
    public void  selectByPrimaryKey(){
        SystemUser systemUser = this.systemUserMapper.selectByPrimaryKey(1L);
        log.info("systemUser --> [{}]",systemUser);

    }


    @Test
    public void  selectByPrimaryKey2(){
        SystemUser systemUser = this.systemUserMapper.selectByPrimaryKey(0L);
        log.info("systemUser --> [{}]",systemUser);

    }



}
