package com.echain.common.enums;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.Maps;

import lombok.Getter;
@Getter
public enum YnEnum {
	
	YES(0,"有效","green"),
	NO(1,"无效","red"),
	;
	
	private int code;
	private String color;
	private String name;

	private static Map<String, YnEnum> tmpMap = new HashMap<String, YnEnum>();

	static {
		for (YnEnum e : YnEnum.values()) {
			tmpMap.put(String.valueOf(e.getCode()), e);
		}
	}

	YnEnum(int code, String name,String color) {
		this.code = code;
		this.name = name;
		this.color = color;
	}

	public static YnEnum get(Integer code) {
		if(code == null){
			return null;
		}
		return tmpMap.get(String.valueOf(code));
	}

	public static String getName(Integer code) {
		YnEnum e = get(code);
		if (e != null) {
			return e.getName();
		}
		return null;
	}
	
	public static Map<String,Object> toJsonObject(){
		Map<String,Object> jo = Maps.newHashMap();
		for(YnEnum e : YnEnum.values()){
			Map<String,String> ee = Maps.newHashMap();
			ee.put("name", e.getName());
			ee.put("color", e.getColor());
			jo.put(String.valueOf(e.getCode()), ee);
		}
		return jo;
	}
}
