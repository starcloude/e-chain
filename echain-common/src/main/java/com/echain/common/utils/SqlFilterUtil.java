package com.echain.common.utils;

import org.apache.commons.lang3.StringUtils;

public class SqlFilterUtil {

	public static String convertValue(String value){
		if(StringUtils.isBlank(value)){
			return null;
		}
		return value.replace("'", "").replace(";", "").replace("-", "").replace("*", "");
	}
}
