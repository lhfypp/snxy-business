package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.IdentityTypeMapper;
import com.snxy.business.dao.mapper.SystemUserMapper;
import com.snxy.business.domain.IdentityType;
import com.snxy.business.domain.SystemUser;

import com.snxy.business.service.IdentityTypeService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

/**
 * Created by 24398 on 2018/9/25.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class IdentityTypeServiceTest {

    @Resource
    private IdentityTypeService identityTypeService;
    @Resource
    private IdentityTypeMapper identityTypeMapper;
    @Resource
    private SystemUserMapper systemUserMapper;


    @Test
    public void listAllTest1(){
        List<IdentityType> identityTypeList = this.identityTypeService.listAll(true);
        log.info("identityTypeList ----> : [{}]",identityTypeList);
     //  List<IdentityType> identityTypeList = this.identityTypeService.listAll(true);
     //   log.info("identityTypeList ----> : [{}]",identityTypeList);
    }

    @Test
    public void listAllTest2(){
      List<IdentityType> identityTypes = this.identityTypeMapper.listAll((byte) 1);
      log.info("identityTypes  ---> [{}]",identityTypes);
     // List<IdentityType> identityTypes = this.identityTypeMapper.listAll((byte) 1);
     // log.info("identityTypes  ---> [{}]",identityTypes);
    }

    @Test
     public void listAllTest3(){
        List<IdentityType> identityTypes = this.identityTypeMapper.listAll((byte) 0);
        log.info("identityTypes ----> : [{}]",identityTypes);
       // List<IdentityType> identityTypes = this.identityTypeMapper.listAll((byte) 0);
     //  log.info("identityTypes ----> : [{}]",identityTypes);
     }


    @Test
    public void systemUserMapperTest(){
       // SystemUser systemUser = this.systemUserMapper.selectByPrimaryKey(1L);
       // log.info("systemUser ---> [{}]",systemUser);
        SystemUser systemUser = this.systemUserMapper.selectByPrimaryKey(1L);
        log.info("systemUser ---> [{}]",systemUser);
    }




}
