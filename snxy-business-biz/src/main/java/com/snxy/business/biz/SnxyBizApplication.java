package com.snxy.business.biz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Created by 24398 on 2018/9/3.
 */
@SpringBootApplication
@EnableFeignClients
public class SnxyBizApplication {
    public static void main(String[] args) {
        SpringApplication.run(SnxyBizApplication.class, args);
    }
}
