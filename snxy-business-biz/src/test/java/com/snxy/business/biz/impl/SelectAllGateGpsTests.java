package com.snxy.business.biz.impl;

import com.snxy.business.domain.Gate;
import com.snxy.business.service.GateService;
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
public class SelectAllGateGpsTests {
    @Resource
    private GateService gateService;
    @Test
    public void selectAllGateGps(){
        List<Gate> gateList = gateService.selectAllDoorGps();
        for (int i=0;i<gateList.size();i++){
            System.out.print("=======门名称=="+gateList.get(i).getGateName()+"=纬度="+gateList.get(i).getLatitude()+"==经度=="+gateList.get(i).getLongitude());
        }
    }
}
