package com.echain.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.echain.BootStrap;
import com.echain.domain.sys.SysUser;
import com.echain.service.common.RedisUtil;
import com.echain.service.sys.SysUserService;

@SpringBootTest(classes= {BootStrap.class})
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration //web
public class AppTest {
	
	@Value("${mysql.url}")
	private String mysqlUrl;
	
	@Value("${spring.datasource.url:}")
	private String jdbcUrl;
	
	@Value("${spring.profiles.active:}")
	private String profile;
	
	@Value("${abc:aabbccc:aabbccc}")
	private String values;
	
	@Autowired
	private RedisUtil redisUtil;
	
	@Autowired
	private SysUserService sysUserService;

	
	@Test
	public void test111() {
		redisUtil.set("hello", "zhou你好");
		System.out.println(redisUtil.get("hello"));
		
		String listKey = "REDIS_LOGGER_OPERATE_INFO";
		
//		redisUtil.lLeftPush(listKey, "hello");
		System.out.println(redisUtil.lRightPop(listKey));
		
		System.out.println(jdbcUrl);
		System.out.println(mysqlUrl);
		System.out.println(profile);
		System.out.println(values);
		
		SysUser user = new SysUser();
		user.setId(2L);
		user.setName("test001");
		sysUserService.updateById(user);
		
	}
}
