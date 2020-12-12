package com.echain.common.enums.business;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.Maps;

import lombok.Getter;

@Getter
public enum NewsInfoStateEnum {

	INIT(0,"待审核","orange"),
	PASS(1,"通过"),
	REFUSE(2,"未通过","red"),
	;
	
	private int code;
	private String name;
	private String color;

	private static Map<String, NewsInfoStateEnum> tmpMap = new HashMap<String, NewsInfoStateEnum>();

	static {
		for (NewsInfoStateEnum e : NewsInfoStateEnum.values()) {
			tmpMap.put(String.valueOf(e.getCode()), e);
		}
	}

	NewsInfoStateEnum(int code, String name) {
		this(code, name, "");
	}
	
	NewsInfoStateEnum(int code, String name,String color) {
		this.code = code;
		this.name = name;
		this.color = color;
	}

	public static NewsInfoStateEnum get(Integer code) {
		if(code == null){
			return null;
		}
		return tmpMap.get(String.valueOf(code));
	}

	public static String getName(Integer code) {
		NewsInfoStateEnum e = get(code);
		if (e != null) {
			return e.getName();
		}
		return null;
	}
	
	public static Map<String,Object> toJsonObject(){
		Map<String,Object> jo = Maps.newHashMap();
		for(NewsInfoStateEnum e : NewsInfoStateEnum.values()){
			Map<String,Object> mm = Maps.newHashMap();
			mm.put("name", e.getName());
			mm.put("color", e.getColor());
			jo.put(String.valueOf(e.getCode()), mm);
		}
		return jo;
	}
}
