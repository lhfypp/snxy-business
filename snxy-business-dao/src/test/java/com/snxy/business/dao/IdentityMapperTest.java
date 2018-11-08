package com.snxy.business.dao;

import com.snxy.business.dao.mapper.IdentityTypeMapper;
import com.snxy.business.domain.IdentityType;
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
public class IdentityMapperTest {

   @Resource
   private IdentityTypeMapper identityTypeMapper;


    @Test
    public void listAllTest1(){
        List<IdentityType> identityTypes = this.identityTypeMapper.listAll((byte) 1);

        log.info("identityTypes --》 [{}]",identityTypes);
    }
}

