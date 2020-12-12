package com.echain.common.enums;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.Maps;

public enum SystemWorkerEnum {

	UserMachineStopWorker("WORKER_UserMachineStopWorker","玩家矿机超时自动停止"),
	UserProfitWorker("WORKER_UserProfitWorker","普通玩家收益结算"),
	UserAgentProfitWorker("WORKER_UserAgentProfitWorker","代理收益结算"),
	UserMachineReturnWorker("WORKER_UserMachineReturnWorker","矿机金额返还"),
	;
	
	private String code;
	private String name;

	private static Map<String, SystemWorkerEnum> tmpMap = new HashMap<String, SystemWorkerEnum>();

	static {
		for (SystemWorkerEnum e : SystemWorkerEnum.values()) {
			tmpMap.put(String.valueOf(e.getCode()), e);
		}
	}

	SystemWorkerEnum(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public static SystemWorkerEnum get(String code) {
		if(code == null){
			return null;
		}
		return tmpMap.get(code);
	}

	public static String getName(String code) {
		SystemWorkerEnum e = get(code);
		if (e != null) {
			return e.getName();
		}
		return null;
	}
	
	public static Map<String,Object> toJsonObject(){
		Map<String,Object> jo = Maps.newHashMap();
		for(SystemWorkerEnum e : SystemWorkerEnum.values()){
			jo.put(String.valueOf(e.getCode()), e.getName());
		}
		return jo;
	}
}
