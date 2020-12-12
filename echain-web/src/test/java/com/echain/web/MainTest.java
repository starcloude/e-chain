package com.echain.web;

import java.lang.reflect.InvocationTargetException;

import com.echain.common.utils.AesEncodeUtil;

public class MainTest {

	public static void main(String[] args) throws IllegalAccessException, InvocationTargetException {
		
		System.out.println(AesEncodeUtil.vue_plaintext("rK3TwJa+5uhlS9zFAgH6aQ=="));
		System.out.println("redis");
		encrypt("127.0.0.1");
		encrypt("33790");
		encrypt("1qaz@WSX");
		
		encrypt("33799");
		encrypt("uat123456");

		System.out.println("mysql");
		encrypt("jdbc:mysql://127.0.0.1:3306/echain?characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useUnicode=true&useSSL=false&serverTimezone=Asia/Shanghai&autoReconnect=true&failOverReadOnly=false&allowPublicKeyRetrieval=true");
		encrypt("root");
		encrypt("1qaz@WSX");
		
		encrypt("100042");
		vue_encrypt("100042");
		vue_plaintext("rK3TwJa+5uhlS9zFAgH6aQ==");
		
	}
	
	private static void encrypt(String value) {
		String encValue = AesEncodeUtil.encrypt(value);
		System.out.println("明文:"+value+"\n密文:"+encValue+"\n");
	}
	
	private static void vue_encrypt(String value) {
		String encValue = AesEncodeUtil.vue_ciphertext(value);
		System.out.println("明文:"+value+"\n密文:"+encValue+"\n");
	}
	
	private static void vue_plaintext(String value) {
		String encValue = AesEncodeUtil.vue_plaintext(value);
		System.out.println("密文:"+value+"\n明文:"+encValue+"\n");
	}
}
