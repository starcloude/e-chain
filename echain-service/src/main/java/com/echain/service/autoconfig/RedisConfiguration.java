package com.echain.service.autoconfig;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.echain.service.common.RedisUtil;

@Configuration
@EnableCaching
public class RedisConfiguration extends CachingConfigurerSupport {

	@Bean(name = "redisTemplate")
	public StringRedisTemplate redisTemplate(RedisConnectionFactory connectionFactory) {
		StringRedisTemplate redisTemplate = new StringRedisTemplate();
		redisTemplate.setConnectionFactory(connectionFactory);
//		redisTemplate.setValueSerializer(new StringRedisSerializer());
//		redisTemplate.setKeySerializer(new StringRedisSerializer());
		return redisTemplate;
	}

	@Bean(name = "redisUtil")
	public RedisUtil redisUtil(StringRedisTemplate redisTemplate) {
		RedisUtil redisUtil = new RedisUtil();
		redisUtil.setRedisTemplate(redisTemplate);
		return redisUtil;
	}
}
