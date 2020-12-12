package com.echain.service.log;

import org.springframework.stereotype.Service;

import com.echain.common.enums.log.LogKeyEnum;

@Service("operateLogger")
public class OperateLogger extends AbsRedisLoggerService{
	public LogKeyEnum getLogKey() {
		return LogKeyEnum.OPERATE;
	}
}
