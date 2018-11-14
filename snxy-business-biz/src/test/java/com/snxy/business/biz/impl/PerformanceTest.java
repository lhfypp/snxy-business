package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.OnlineUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PerformanceTest {

    @Test
    public void test1(){
        long startTime = System.currentTimeMillis();
        List<String> stringList=new ArrayList<>();
        for(int i=0;i<10000000;i++){
            stringList.add(String.valueOf(i));
        }
        for(String s:stringList){
            System.out.println(s);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("运行时间:" + (endTime - startTime) + "ms");
    }
    @Test
    public void test2(){

        long startTime= System.currentTimeMillis();
        List<String> stringList=new ArrayList<>();
        for(int i=0;i<10000000;i++){
            stringList.add(String.valueOf(i));
        }
        stringList.forEach((s)-> System.out.println(s));
        long endTime = System.currentTimeMillis();
        System.out.println("运行时间:" + (endTime - startTime) + "ms");

    }

}
