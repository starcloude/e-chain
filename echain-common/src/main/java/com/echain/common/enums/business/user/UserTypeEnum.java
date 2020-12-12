package com.echain.common.enums.business.user;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.Maps;

import lombok.Getter;
@Getter
public enum UserTypeEnum {
	
	NORMAL(0,"普通用户"),
	AGENT(1,"团队长");
	;
	
	private int code;
	private String name;

	private static Map<String, UserTypeEnum> tmpMap = new HashMap<String, UserTypeEnum>();

	static {
		for (UserTypeEnum e : UserTypeEnum.values()) {
			tmpMap.put(String.valueOf(e.getCode()), e);
		}
	}

	UserTypeEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public static UserTypeEnum get(Integer code) {
		if(code == null){
			return null;
		}
		return tmpMap.get(String.valueOf(code));
	}

	public static String getName(Integer code) {
		UserTypeEnum e = get(code);
		if (e != null) {
			return e.getName();
		}
		return null;
	}
	
	public static Map<String,Object> toJsonObject(){
		Map<String,Object> jo = Maps.newHashMap();
		for(UserTypeEnum e : UserTypeEnum.values()){
			jo.put(String.valueOf(e.getCode()), e.getName());
		}
		return jo;
	}
}
