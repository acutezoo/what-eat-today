package com.pighome.eat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(scanBasePackages = "com.pighome.eat")
@MapperScan(basePackages = "com.pighome.eat.dao")
@EnableTransactionManagement
public class WhatEatTodayApplication {

	public static void main(String[] args) {
		SpringApplication.run(WhatEatTodayApplication.class, args);
	}

}
