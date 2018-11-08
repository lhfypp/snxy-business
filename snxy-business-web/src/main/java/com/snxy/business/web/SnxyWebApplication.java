package com.snxy.business.web;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringCloudApplication
@EnableEurekaClient
@ComponentScan({"com.snxy.business.web","com.snxy.business.biz","com.snxy.business.dao"})
@EnableScheduling
@EnableAsync
public class SnxyWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SnxyWebApplication.class, args);
	}
}
