package com.xftxyz.rocketblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@MapperScan({ "com.xftxyz.rocketblog.mapper" })
public class RocketblogApplication {

	public static ConfigurableApplicationContext ac;

	public static void main(String[] args) {
		ac = SpringApplication.run(RocketblogApplication.class, args);
	}

}
