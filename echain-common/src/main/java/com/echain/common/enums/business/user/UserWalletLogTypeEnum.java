package com.echain.common.enums.business.user;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.Maps;

import lombok.Getter;

@Getter
public enum UserWalletLogTypeEnum {

	/**收益*/
	PROFIT(0,"收益"),
	/**订单*/
	ORDER(1,"订单"),
	/**转账*/
	TRANSFER(2,"转账"),
	/**充值*/
	RECHARGE(3,"充值"),
	/*提现*/
	CASHOUT(4,"提现"),
	/**平台奖励**/
	REWARD(5,"平台奖励"),
	/**代理提成*/
	AGENT_REBATE(6,"提成"),
	
	/* OTHER(5,"其他"), */
	;
	
	private int code;
	private String name;
	private String color;

	private static Map<String, UserWalletLogTypeEnum> tmpMap = new HashMap<String, UserWalletLogTypeEnum>();

	static {
		for (UserWalletLogTypeEnum e : UserWalletLogTypeEnum.values()) {
			tmpMap.put(String.valueOf(e.getCode()), e);
		}
	}

	UserWalletLogTypeEnum(int code, String name) {
		this(code, name, "");
	}
	
	UserWalletLogTypeEnum(int code, String name,String color) {
		this.code = code;
		this.name = name;
		this.color = color;
	}

	public static UserWalletLogTypeEnum get(Integer code) {
		if(code == null){
			return null;
		}
		return tmpMap.get(String.valueOf(code));
	}

	public static String getName(Integer code) {
		UserWalletLogTypeEnum e = get(code);
		if (e != null) {
			return e.getName();
		}
		return null;
	}
	
	public static Map<String,Object> toJsonObject(){
		Map<String,Object> jo = Maps.newHashMap();
		for(UserWalletLogTypeEnum e : UserWalletLogTypeEnum.values()){
			Map<String,Object> mm = Maps.newHashMap();
			mm.put("name", e.getName());
			mm.put("color", e.getColor());
			jo.put(String.valueOf(e.getCode()), mm);
		}
		return jo;
	}
}
