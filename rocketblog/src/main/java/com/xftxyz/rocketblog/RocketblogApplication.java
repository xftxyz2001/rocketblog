package com.xftxyz.rocketblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({ "com.xftxyz.rocketblog.mapper" })
public class RocketblogApplication {

	public static void main(String[] args) {
		SpringApplication.run(RocketblogApplication.class, args);
	}

}
