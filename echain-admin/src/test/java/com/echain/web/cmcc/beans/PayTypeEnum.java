package com.echain.web.cmcc.beans;

import java.util.HashMap;
import java.util.Map;

/**
 * 支付类型
 * @author 作者B
 */
public enum PayTypeEnum {

	ALISCAN(1, "992","支付宝扫码"),
	ALIWAP(2, "2992","支付宝WAP"),
	ALITRANSFER(296, "2002","支付宝转账"),
	WXSCAN(3, "1004","微信扫码"),
	WXWAP(4, "2004","微信WAP"),
	QQSCAN(5, "1005","QQ钱包扫码"),
	QQWAP(6, "2005","QQ钱包WAP");
	
	private int code;
	private String no;
	private String name;

	private static Map<String, PayTypeEnum> tmpMap = new HashMap<String, PayTypeEnum>();

	static {
		for (PayTypeEnum e : PayTypeEnum.values()) {
			tmpMap.put(String.valueOf(e.getCode()), e);
		}
	}

	PayTypeEnum(int code, String no, String name) {
		this.code = code;
		this.no = no;
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public String getNo() {
		return no;
	}

	public String getName() {
		return name;
	}

	public static PayTypeEnum get(Integer code) {
		if(code == null){
			return null;
		}
		return tmpMap.get(String.valueOf(code));
	}

	public static String getName(Integer code) {
		PayTypeEnum e = get(code);
		if (e != null) {
			return e.getName();
		}
		return null;
	}
}
