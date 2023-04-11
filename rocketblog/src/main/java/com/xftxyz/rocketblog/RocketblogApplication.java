package com.xftxyz.rocketblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.xftxyz.rocketblog.component.MessageServer;

@SpringBootApplication
@MapperScan({ "com.xftxyz.rocketblog.mapper" })
public class RocketblogApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ac = SpringApplication.run(RocketblogApplication.class, args);
		MessageServer.init(ac);
	}

}
