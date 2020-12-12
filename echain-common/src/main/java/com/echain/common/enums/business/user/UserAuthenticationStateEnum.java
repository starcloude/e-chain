package com.echain.common.enums.business.user;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.Maps;

public enum UserAuthenticationStateEnum {

	INIT(0,"待审核"),
	SUCCESS(1,"已认证"),
	FAIL(2,"认证失败"),
	;
	
	private int code;
	private String name;

	private static Map<String, UserAuthenticationStateEnum> tmpMap = new HashMap<String, UserAuthenticationStateEnum>();

	static {
		for (UserAuthenticationStateEnum e : UserAuthenticationStateEnum.values()) {
			tmpMap.put(String.valueOf(e.getCode()), e);
		}
	}

	UserAuthenticationStateEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public static UserAuthenticationStateEnum get(Integer code) {
		if(code == null){
			return null;
		}
		return tmpMap.get(String.valueOf(code));
	}

	public static String getName(Integer code) {
		UserAuthenticationStateEnum e = get(code);
		if (e != null) {
			return e.getName();
		}
		return null;
	}
	
	public static Map<String,Object> toJsonObject(){
		Map<String,Object> jo = Maps.newHashMap();
		for(UserAuthenticationStateEnum e : UserAuthenticationStateEnum.values()){
			jo.put(String.valueOf(e.getCode()), e.getName());
		}
		return jo;
	}
}
