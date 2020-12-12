package com.echain.web.controller.cmcc;

import java.io.InputStreamReader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.apache.commons.lang3.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JSEncryptUtil {
	
	private static Invocable invocableEngine;
	
	static {
		ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engie = manager.getEngineByName("js");
        try {
			engie.eval(new InputStreamReader(JSEncryptUtil.class.getResourceAsStream("/js/login.10086.js")));
		} catch (Exception e) {
			log.error("初始化JS引擎发生异常!",e);
		}
        invocableEngine = (Invocable) engie;
	}
	
	public static String encrypt(String pwd) {
		if(invocableEngine == null  || StringUtils.isBlank(pwd)) {
			return null;
		}
		try {
			return (String)invocableEngine.invokeFunction("encrypt",pwd);
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(JSEncryptUtil.class.getResource("."));
		System.out.println(JSEncryptUtil.class.getResource("/js/login.10086.js").getPath());
		for(int i=0;i<2;i++) {
			System.out.println(encrypt("150010"));
			System.out.println(System.currentTimeMillis()/1000);
		}
	}
}