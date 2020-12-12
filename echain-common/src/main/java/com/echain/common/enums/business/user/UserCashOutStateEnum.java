package com.echain.common.enums.business.user;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.Maps;

import lombok.Getter;

@Getter
public enum UserCashOutStateEnum {

	INIT(0,"提现中","blue"),
	SUCCESS(1,"成功","green"),
	FAIL(2,"失败","red"),
	;
	
	private int code;
	private String name;
	private String color;
	private static Map<String, UserCashOutStateEnum> tmpMap = new HashMap<String, UserCashOutStateEnum>();

	static {
		for (UserCashOutStateEnum e : UserCashOutStateEnum.values()) {
			tmpMap.put(String.valueOf(e.getCode()), e);
		}
	}

	UserCashOutStateEnum(int code, String name) {
		this(code, name, "");
	}
	
	UserCashOutStateEnum(int code, String name,String color) {
		this.code = code;
		this.name = name;
		this.color=color;
	}

	public static UserCashOutStateEnum get(Integer code) {
		if(code == null){
			return null;
		}
		return tmpMap.get(String.valueOf(code));
	}

	public static String getName(Integer code) {
		UserCashOutStateEnum e = get(code);
		if (e != null) {
			return e.getName();
		}
		return null;
	}
	
	public static Map<String,Object> toJsonObject(){
		Map<String,Object> jo = Maps.newHashMap();
		for(UserCashOutStateEnum e : UserCashOutStateEnum.values()){
			Map<String,Object> ee = Maps.newHashMap();
			ee.put("name", e.getName());
			ee.put("color", e.getColor());
			jo.put(String.valueOf(e.getCode()), ee);
		}
		return jo;
	}
}
