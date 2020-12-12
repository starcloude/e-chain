package com.echain.common.enums.log;

import lombok.Getter;

/**
 */
@Getter
public enum LogKeyEnum{

	OPERATE(2,"REDIS_LOGGER_OPERATE","系统操作日志"),
	;

	private int code;
	private String name;
	private String memo;

	LogKeyEnum(int code, String name,String memo) {
		this.code = code;
		this.name = name;
		this.memo = memo;
	}
}
