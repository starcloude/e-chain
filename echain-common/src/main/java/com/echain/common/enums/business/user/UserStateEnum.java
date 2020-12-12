package com.echain.common.enums.business.user;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.Maps;

import lombok.Getter;

@Getter
public enum UserStateEnum {

	NORMAL(0,"正常",""),
	NO_LOGIN(1,"禁止登陆","red"),
	NO_CASHOUT(2,"禁止提现","orange"),
	;
	
	private int code;
	private String name;
	private String color;

	private static Map<String, UserStateEnum> tmpMap = new HashMap<String, UserStateEnum>();

	static {
		for (UserStateEnum e : UserStateEnum.values()) {
			tmpMap.put(String.valueOf(e.getCode()), e);
		}
	}

	UserStateEnum(int code, String name) {
		this(code, name, null);
	}
	UserStateEnum(int code, String name,String color) {
		this.code = code;
		this.name = name;
		this.color = color;
	}
	
	public static UserStateEnum get(Integer code) {
		if(code == null){
			return null;
		}
		return tmpMap.get(String.valueOf(code));
	}

	public static String getName(Integer code) {
		UserStateEnum e = get(code);
		if (e != null) {
			return e.getName();
		}
		return null;
	}
	
	public static Map<String,Object> toJsonObject(){
		Map<String,Object> jo = Maps.newHashMap();
		for(UserStateEnum e : UserStateEnum.values()){
			Map<String,String> obj = Maps.newHashMap();
			obj.put("name", e.getName());
			obj.put("color", e.getColor());
			jo.put(String.valueOf(e.getCode()), obj);
		}
		return jo;
	}
}
