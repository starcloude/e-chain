package com.echain.web.cmcc.beans;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 作者B
 */
public enum MobileTypeEnum {
	
	/**
	 * 移动
	 */
	CMCC(1,"CMCC"),
	/**
	 * 联通
	 */
	CUCC(2,"CUCC"),
	/**
	 * 电信
	 */
	CTCC(3,"CTCC");
	
	private int code;
	private String name;

	private static Map<String, MobileTypeEnum> tmpMap = new HashMap<String, MobileTypeEnum>();

	static {
		for (MobileTypeEnum e : MobileTypeEnum.values()) {
			tmpMap.put(String.valueOf(e.getCode()), e);
		}
	}

	MobileTypeEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public static MobileTypeEnum get(Integer code) {
		if(code == null){
			return null;
		}
		return tmpMap.get(String.valueOf(code));
	}

	public static String getName(Integer code) {
		MobileTypeEnum e = get(code);
		if (e != null) {
			return e.getName();
		}
		return null;
	}
}
