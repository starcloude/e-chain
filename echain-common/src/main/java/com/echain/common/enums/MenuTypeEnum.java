package com.echain.common.enums;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.Maps;

import lombok.Getter;

/**
 * 菜单类型
 */
@Getter
public enum MenuTypeEnum {

	NAVIGATION(1,"导航"),
	MENU(2,"菜单"),
	BUTTON(3,"按钮");
	
	private int code;
	private String name;

	private static Map<String, MenuTypeEnum> tmpMap = new HashMap<String, MenuTypeEnum>();

	static {
		for (MenuTypeEnum e : MenuTypeEnum.values()) {
			tmpMap.put(String.valueOf(e.getCode()), e);
		}
	}

	MenuTypeEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public static MenuTypeEnum get(Integer code) {
		if(code == null){
			return null;
		}
		return tmpMap.get(String.valueOf(code));
	}

	public static String getName(Integer code) {
		MenuTypeEnum e = get(code);
		if (e != null) {
			return e.getName();
		}
		return null;
	}
	
	public static Map<String,Object> toJsonObject(){
		Map<String,Object> jo =  Maps.newHashMap();
		for(MenuTypeEnum e : MenuTypeEnum.values()){
			jo.put(String.valueOf(e.getCode()), e.getName());
		}
		return jo;
	}
}
