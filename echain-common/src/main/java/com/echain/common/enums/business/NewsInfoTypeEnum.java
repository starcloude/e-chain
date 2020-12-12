package com.echain.common.enums.business;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.Maps;

public enum NewsInfoTypeEnum {

	COIN(1,"币圈"),
	OTHER(2,"其他"),
	;
	
	private int code;
	private String name;

	private static Map<String, NewsInfoTypeEnum> tmpMap = new HashMap<String, NewsInfoTypeEnum>();

	static {
		for (NewsInfoTypeEnum e : NewsInfoTypeEnum.values()) {
			tmpMap.put(String.valueOf(e.getCode()), e);
		}
	}

	NewsInfoTypeEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public static NewsInfoTypeEnum get(Integer code) {
		if(code == null){
			return null;
		}
		return tmpMap.get(String.valueOf(code));
	}

	public static String getName(Integer code) {
		NewsInfoTypeEnum e = get(code);
		if (e != null) {
			return e.getName();
		}
		return null;
	}
	
	public static Map<String,Object> toJsonObject(){
		Map<String,Object> jo = Maps.newHashMap();
		for(NewsInfoTypeEnum e : NewsInfoTypeEnum.values()){
			jo.put(String.valueOf(e.getCode()), e.getName());
		}
		return jo;
	}
}
