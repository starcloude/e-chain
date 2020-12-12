package com.echain.service.common;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import lombok.extern.slf4j.Slf4j;

/**
 * 此工具类,有缓存,谨慎使用
 */
@Slf4j
@Service("redisCache")
public class RedisCache {

	@Autowired
	private RedisUtil redisUtil;

	private LoadingCache<String, String> redisTurnCache = CacheBuilder.newBuilder().maximumSize(1000)
			.expireAfterWrite(10, TimeUnit.MINUTES).build(new CacheLoader<String, String>() {
				public String load(String key) {
					try {
						String value = redisUtil.get(key);
						if (StringUtils.isBlank(value)) {
							return "";
						}
						return value;
					} catch (Exception ex) {
						log.error("redisUtil.load 发生异常! ", ex);
						return "";
					}
				}
			});

	/**
	 * 从缓存中获取值
	 *
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public String get(String key, String defaultValue) {
		try {
			String value = redisTurnCache.get(key);
			if (StringUtils.isBlank(value)) {
				return defaultValue;
			}
			return value;
		} catch (Exception ex) {
			log.error("load from cache error ", ex);
			return defaultValue;
		}
	}

	/**
	 * 从缓存中获取值 ,默认值 是 0
	 *
	 * @param key
	 * @return
	 */
	public String get(String key) {
		return this.get(key, "0");
	}

	public Long getLongValue(String key) {
		return getLongValue(key,null);
	}
	
	public Long getLongValue(String key,Long defaultValue) {
		try {
			String value = get(key,String.valueOf(defaultValue));
			if(StringUtils.isNotBlank(value)) {
				return Long.valueOf(value);
			}
			return defaultValue;
		} catch (Exception ex) {
			return defaultValue;
		}
	}
	
	public BigDecimal getBigDecimal(String key) {
		return getBigDecimal(key,BigDecimal.ZERO);
	}
	
	public BigDecimal getBigDecimal(String key,BigDecimal defaultValue) {
		try {
			String value = get(key,String.valueOf(defaultValue));
			if(StringUtils.isNotBlank(value)) {
				return new BigDecimal(value);
			}
			return defaultValue;
		} catch (Exception ex) {
			return defaultValue;
		}
	}
}