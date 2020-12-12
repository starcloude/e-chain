package com.echain.common.enums;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.Maps;

public enum TurnEnum {

	ON("on","开启"),
	OFF("off","关闭"),
	;
	
	private String code;
	private String name;

	private static Map<String, TurnEnum> tmpMap = new HashMap<String, TurnEnum>();

	static {
		for (TurnEnum e : TurnEnum.values()) {
			tmpMap.put(String.valueOf(e.getCode()), e);
		}
	}

	TurnEnum(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public static TurnEnum get(String code) {
		if(code == null){
			return null;
		}
		return tmpMap.get(code);
	}

	public static String getName(String code) {
		TurnEnum e = get(code);
		if (e != null) {
			return e.getName();
		}
		return null;
	}
	
	public static Object toJsonObject(){
		Map<String,String>jo = Maps.newHashMap();
		for(TurnEnum e : TurnEnum.values()){
			jo.put(e.getCode(), e.getName());
		}
		return jo;
	}
}