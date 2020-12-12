package com.echain.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.echain.BootStrap;
import com.echain.service.common.RedisUtil;

@SpringBootTest(classes= {BootStrap.class})
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration //web
public class AppTest {
	
//	@Value("${system.mycode}")
//	private String code;
//	
//	@Value("${system.mycode2}")
//	private String code1;
//	
//	@Value("${spring.datasource.druid.url}")
//	private String jdbcUrl;
	
	@Value("${mysql.url}")
	private String mysqlUrl;
	
	@Value("${spring.datasource.url:}")
	private String jdbcUrl;
	
	@Value("${spring.profiles.active:}")
	private String profile;
	
	@Autowired
	private RedisUtil redisUtil;
	
	@Test
	public void test111() {
		redisUtil.set("hello", "zhou你好");
		System.out.println(redisUtil.get("hello"));
		System.out.println(jdbcUrl);
		System.out.println(mysqlUrl);
		System.out.println(profile);
	}
	
}
