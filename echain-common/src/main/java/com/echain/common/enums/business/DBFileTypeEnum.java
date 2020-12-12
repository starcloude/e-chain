package com.echain.common.enums.business;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.Maps;

public enum DBFileTypeEnum {

	IMAGE(1,"图片"),
	FILE(2,"附件"),
	;
	
	private int code;
	private String name;

	private static Map<String, DBFileTypeEnum> tmpMap = new HashMap<String, DBFileTypeEnum>();

	static {
		for (DBFileTypeEnum e : DBFileTypeEnum.values()) {
			tmpMap.put(String.valueOf(e.getCode()), e);
		}
	}

	DBFileTypeEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public static DBFileTypeEnum get(Integer code) {
		if(code == null){
			return null;
		}
		return tmpMap.get(String.valueOf(code));
	}

	public static String getName(Integer code) {
		DBFileTypeEnum e = get(code);
		if (e != null) {
			return e.getName();
		}
		return null;
	}
	
	public static Map<String,Object> toJsonObject(){
		Map<String,Object> jo = Maps.newHashMap();
		for(DBFileTypeEnum e : DBFileTypeEnum.values()){
			jo.put(String.valueOf(e.getCode()), e.getName());
		}
		return jo;
	}
}
