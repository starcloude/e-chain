package com.echain.common.enums;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.Maps;

import lombok.Getter;

/**
 * 客户端
 */
@Getter
public enum ClientTypeEnum {
	
	PC(1,"PC"),
	M(2,"M");
	
	private int code;
	private String name;

	private static Map<String, ClientTypeEnum> tmpMap = new HashMap<String, ClientTypeEnum>();

	static {
		for (ClientTypeEnum e : ClientTypeEnum.values()) {
			tmpMap.put(String.valueOf(e.getCode()), e);
		}
	}

	ClientTypeEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public static ClientTypeEnum get(Integer code) {
		if(code == null){
			return null;
		}
		return tmpMap.get(String.valueOf(code));
	}

	public static String getName(Integer code) {
		ClientTypeEnum e = get(code);
		if (e != null) {
			return e.getName();
		}
		return null;
	}
	
	public static Map<String,Object> toJsonObject(){
		Map<String,Object> jo = Maps.newHashMap();
		for(ClientTypeEnum e : ClientTypeEnum.values()){
			jo.put(String.valueOf(e.getCode()), e.getName());
		}
		return jo;
	}
}
