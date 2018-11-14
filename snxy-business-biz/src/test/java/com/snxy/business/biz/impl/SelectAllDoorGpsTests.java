package com.snxy.business.biz.impl;

import com.snxy.business.domain.Door;
import com.snxy.business.service.DoorService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SelectAllDoorGpsTests {
    @Resource
    private DoorService doorService;
    @Test
    public void selectAllDoorGps(){
        List<Door> doorList = doorService.selectAllDoorGps();
        for (int i=0;i<doorList.size();i++){
            System.out.print("=======门名称=="+doorList.get(i).getDoorName()+"=纬度="+doorList.get(i).getLatitude()+"==经度=="+doorList.get(i).getLongitude());
        }
    }
}
