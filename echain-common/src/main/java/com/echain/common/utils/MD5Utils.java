package com.echain.common.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Utils {
	// 公盐
	private static final String PUBLIC_SALT = "pwd";

	/**
	 * 用户密码加密，盐值为 ：私盐+公盐
	 * 
	 * @param password 密码
	 * @param salt     私盐
	 * @return MD5加密字符串
	 */
	public static String encryptPassword(String password, String salt) {
		return DigestUtils.md5Hex(PUBLIC_SALT + password + salt).toUpperCase();
	}
}