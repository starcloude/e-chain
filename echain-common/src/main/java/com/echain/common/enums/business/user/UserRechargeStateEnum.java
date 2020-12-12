package com.echain.common.enums.business.user;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.Maps;

import lombok.Getter;

@Getter
public enum UserRechargeStateEnum {

	INIT(0,"充值中"),
	SUCCESS(1,"成功","green"),
	FAIL(2,"失败","red"),
	;
	
	private int code;
	private String name;
	private String color;

	private static Map<String, UserRechargeStateEnum> tmpMap = new HashMap<String, UserRechargeStateEnum>();

	static {
		for (UserRechargeStateEnum e : UserRechargeStateEnum.values()) {
			tmpMap.put(String.valueOf(e.getCode()), e);
		}
	}

	UserRechargeStateEnum(int code, String name) {
		this(code, name, "");
	}
	
	UserRechargeStateEnum(int code, String name,String color) {
		this.code = code;
		this.name = name;
		this.color = color;
	}

	public static UserRechargeStateEnum get(Integer code) {
		if(code == null){
			return null;
		}
		return tmpMap.get(String.valueOf(code));
	}

	public static String getName(Integer code) {
		UserRechargeStateEnum e = get(code);
		if (e != null) {
			return e.getName();
		}
		return null;
	}
	
	public static Map<String,Object> toJsonObject(){
		Map<String,Object> jo = Maps.newHashMap();
		for(UserRechargeStateEnum e : UserRechargeStateEnum.values()){
			Map<String,String> ee = Maps.newHashMap();
			ee.put("name", e.getName());
			ee.put("color", e.getColor());
			jo.put(String.valueOf(e.getCode()), ee);
		}
		return jo;
	}
}
