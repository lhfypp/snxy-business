package com.snxy.business.biz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by 24398 on 2018/9/3.
 */
@SpringBootApplication
@EnableFeignClients

@ComponentScan({"com.snxy.business.biz","com.snxy.business.dao"})
public class SnxyBizApplication {
    public static void main(String[] args) {
        SpringApplication.run(SnxyBizApplication.class, args);
    }
}
