package com.echain.service.log;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import com.echain.common.enums.TurnEnum;
import com.echain.common.enums.log.LogKeyEnum;
import com.echain.common.enums.log.LogTypeEnum;
import com.echain.common.utils.Json;
import com.echain.service.common.RedisCache;
import com.echain.service.common.RedisUtil;

public abstract class AbsRedisLoggerService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private RedisUtil redisUtil;
	
	@Autowired
	private RedisCache redisCache;
	
	
	@Async
	public void info(Object log) {
		if(TurnEnum.ON.getCode().equals(redisCache.get(getTurnKey(LogTypeEnum.INFO)))) {
			this.writeLog(LogTypeEnum.INFO,log);
		}
	}
	
	@Async
	public void error(Object log) {
		if(TurnEnum.ON.getCode().equals(redisCache.get(getTurnKey(LogTypeEnum.ERROR)))) {
			this.writeLog(LogTypeEnum.ERROR,log);
		}
	}
	
	@Async
	public void debug(Object log) {
		if(TurnEnum.ON.getCode().equals(redisCache.get(getTurnKey(LogTypeEnum.DEBUG)))) {
			this.writeLog(LogTypeEnum.DEBUG,log);
		}
	}
	
	protected void writeLog(LogTypeEnum level,Object log) {
		try {
			String value = "";
			if(log instanceof String) {
				value = log.toString();
			}else {
				value = Json.toJSON(log);
			}
			if(StringUtils.isBlank(value)) {
				return;
			}
			String key = getKey(level);
			redisUtil.lLeftPush(key, value);
		}catch(Exception ex) {
			logger.error("日志写入发生异常!",ex);
		}
	}
	
	private String getKey(LogTypeEnum level) {
		return String.format("%s_%s", getLogKey().getName(),level.getName());
	}
	
	private String getTurnKey(LogTypeEnum type) {
		return String.format("%s_TURN", getKey(type));
	}
	
	public abstract LogKeyEnum getLogKey();
}
