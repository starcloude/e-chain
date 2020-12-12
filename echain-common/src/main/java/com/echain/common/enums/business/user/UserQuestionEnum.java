package com.echain.common.enums.business.user;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.Maps;

public enum UserQuestionEnum {

	QESTION1(1,"您出生在哪个城市?"),
	QESTION2(2,"您是什么星座?"),
	QESTION3(3,"您居住在哪个城市?"),
	QESTION4(4,"您毕业的学校名称是什么?"),
	QESTION5(5,"您最喜欢的明星是哪位?"),
	QESTION6(6,"您最喜欢哪首歌曲?"),
	QESTION7(7,"您的出生日期?"),
	QESTION8(8,"您记忆最深刻的老师的名字?"),
	QESTION9(9,"您最喜欢什么运动?"),
	QESTION10(10,"您的手机是什么品牌?"),
	;
	
	private int code;
	private String name;

	private static Map<String, UserQuestionEnum> tmpMap = new HashMap<String, UserQuestionEnum>();

	static {
		for (UserQuestionEnum e : UserQuestionEnum.values()) {
			tmpMap.put(String.valueOf(e.getCode()), e);
		}
	}

	UserQuestionEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public static UserQuestionEnum get(Integer code) {
		if(code == null){
			return null;
		}
		return tmpMap.get(String.valueOf(code));
	}

	public static String getName(Integer code) {
		UserQuestionEnum e = get(code);
		if (e != null) {
			return e.getName();
		}
		return null;
	}
	
	public static Map<String,Object> toJsonObject(){
		Map<String,Object> jo = Maps.newHashMap();
		for(UserQuestionEnum e : UserQuestionEnum.values()){
			jo.put(String.valueOf(e.getCode()), e.getName());
		}
		return jo;
	}
}
