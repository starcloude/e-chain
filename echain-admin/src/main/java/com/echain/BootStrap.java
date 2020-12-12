package com.echain;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ServletComponentScan
@MapperScan("com.echain.dao")
@EnableAsync
@EnableScheduling
public class BootStrap extends SpringBootServletInitializer{

	public static void main(String[] args) {
//		System.setProperty("spring.devtools.restart.enabled", "false");
//		SpringApplication springApplication = new SpringApplication(BootStrap.class);
//		springApplication.setBannerMode(Banner.Mode.OFF);
//		springApplication.run(args);
		SpringApplication.run(BootStrap.class, args);
	}
}
