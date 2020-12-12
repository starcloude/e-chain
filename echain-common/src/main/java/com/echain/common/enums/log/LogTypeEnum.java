package com.echain.common.enums.log;

import lombok.Getter;
@Getter
public enum LogTypeEnum {

	DEBUG(1, "DEBUG", "DEBUG"), INFO(2, "INFO", "INFO"), ERROR(3, "ERROR", "ERROR");

	private int code;
	private String name;
	private String memo;

	LogTypeEnum(int code, String name, String memo) {
		this.code = code;
		this.name = name;
		this.memo = memo;
	}
}
