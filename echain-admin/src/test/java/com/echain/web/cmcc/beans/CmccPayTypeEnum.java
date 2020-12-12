package com.echain.web.cmcc.beans;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 作者B
 */
public enum CmccPayTypeEnum {
	
	WECHAT(1,"WXPAY"),
	ALIPAY(2,"ALIPAY");
	
	private int code;
	private String name;

	private static Map<String, CmccPayTypeEnum> tmpMap = new HashMap<String, CmccPayTypeEnum>();

	static {
		for (CmccPayTypeEnum e : CmccPayTypeEnum.values()) {
			tmpMap.put(String.valueOf(e.getCode()), e);
		}
	}

	CmccPayTypeEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public static CmccPayTypeEnum get(Integer code) {
		if(code == null){
			return null;
		}
		return tmpMap.get(String.valueOf(code));
	}

	public static String getName(Integer code) {
		CmccPayTypeEnum e = get(code);
		if (e != null) {
			return e.getName();
		}
		return null;
	}
}
