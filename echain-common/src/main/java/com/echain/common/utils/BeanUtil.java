package com.echain.common.utils;

import org.apache.commons.beanutils.BeanUtils;

public class BeanUtil {
	
	/**
	 * 基于BeanUtils 进行属性拷贝
	 * @param <T>
	 * @param src
	 * @param target
	 * @return
	 */
	public static <T> T convert(Object src, Class<T> target) {
		T t = null;
		try {
			t = target.newInstance();
			BeanUtils.copyProperties(t, src);
			return t;
		}catch(Exception ex) {
			ex.printStackTrace();
			return t;
		}
	}
}
