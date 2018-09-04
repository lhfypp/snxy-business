package com.snxy.business.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringCloudApplication
@ComponentScan({"com.snxy.business.web","com.snxy.business.biz","com.snxy.business.dao"})
public class SnxyWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SnxyWebApplication.class, args);
	}
}
