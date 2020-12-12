package com.echain.common.enums.business.user;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.Maps;

import lombok.Getter;
@Getter
public enum UserAccountTypeEnum {
	
	PHONE(0,"手机号码"),
	EMAIL(1,"邮箱");
	;
	
	private int code;
	private String name;

	private static Map<String, UserAccountTypeEnum> tmpMap = new HashMap<String, UserAccountTypeEnum>();

	static {
		for (UserAccountTypeEnum e : UserAccountTypeEnum.values()) {
			tmpMap.put(String.valueOf(e.getCode()), e);
		}
	}

	UserAccountTypeEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public static UserAccountTypeEnum get(Integer code) {
		if(code == null){
			return null;
		}
		return tmpMap.get(String.valueOf(code));
	}

	public static String getName(Integer code) {
		UserAccountTypeEnum e = get(code);
		if (e != null) {
			return e.getName();
		}
		return null;
	}
	
	public static Map<String,Object> toJsonObject(){
		Map<String,Object> jo = Maps.newHashMap();
		for(UserAccountTypeEnum e : UserAccountTypeEnum.values()){
			jo.put(String.valueOf(e.getCode()), e.getName());
		}
		return jo;
	}
}
