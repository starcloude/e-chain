package com.echain.common.enums.business.user;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.Maps;

import lombok.Getter;

@Getter
public enum UserMachineStateEnum {

	INIT(0,"待支付","orange"),
	RUNNING(1,"运行中","green"),
	STOPED(2,"已停止","red"),
	FAIL(3,"购买失败","grey"),
	;
	
	private int code;
	private String name;
	private String color;

	private static Map<String, UserMachineStateEnum> tmpMap = new HashMap<String, UserMachineStateEnum>();

	static {
		for (UserMachineStateEnum e : UserMachineStateEnum.values()) {
			tmpMap.put(String.valueOf(e.getCode()), e);
		}
	}

	UserMachineStateEnum(int code, String name) {
		this(code, name, "");
	}
	
	UserMachineStateEnum(int code, String name,String color) {
		this.code = code;
		this.name = name;
		this.color = color;
	}

	public static UserMachineStateEnum get(Integer code) {
		if(code == null){
			return null;
		}
		return tmpMap.get(String.valueOf(code));
	}

	public static String getName(Integer code) {
		UserMachineStateEnum e = get(code);
		if (e != null) {
			return e.getName();
		}
		return null;
	}
	
	public static Map<String,Object> toJsonObject(){
		Map<String,Object> jo = Maps.newHashMap();
		for(UserMachineStateEnum e : UserMachineStateEnum.values()){
			Map<String,Object> mm = Maps.newHashMap();
			mm.put("name", e.getName());
			mm.put("color", e.getColor());
			jo.put(String.valueOf(e.getCode()), mm);
		}
		return jo;
	}
}
