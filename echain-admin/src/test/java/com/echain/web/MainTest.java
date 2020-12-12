package com.echain.web;

import java.io.IOException;
import java.util.Map;

import com.echain.common.utils.Json;
import com.echain.common.utils.http.OKHttpUtil;
import com.echain.domain.sys.SysUser;
import com.echain.web.controller.cmcc.Touch10086LoginWrapper;
import com.google.common.collect.Maps;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MainTest {

	public static void main(String[] args) throws IOException {
		
//		if(true) {
//			
//			//本地测试
//			Touch10086LoginWrapper loginWrapper = new Touch10086LoginWrapper();
//			for(int i=0;i<3;i++) {
//				loginWrapper.login("http://8.129.41.87:7267/api/cookie");
//			}
//			return;
//		}

		OKHttpUtil okHttpUtil = OKHttpUtil.newInstance();//.addInterceptor(new LogInterceptor());
		Map<String,String> xx = Maps.newHashMap();
//		xx.put("param1", "param1");
//		xx.put("param2", "param2");
		
//		okHttpUtil.post("http://localhost:10010/get", "{hello}");
//		okHttpUtil.post("http://localhost:10010/get", xx);
		//解密测试
//		xx.put("ciphertext", "rK3TwJa+5uhlS9zFAgH6aQ==");
//		System.out.println(okHttpUtil.post("http://api.yoyis.win:60/api/getPlaintext", xx).body().string());
//		okHttpUtil.get("http://localhost:10010/get", xx);
		
		///ck 测试
		for(int i=0;i<3 ;i++) {
			xx.put("callBackUrl", "http://8.129.41.87:7267/api/cookie");
			System.out.println(okHttpUtil.post("http://admin.yoyis.win:60/api/10086/getCookie", xx).body().string());
		}
		
		SysUser user = new SysUser();
		user.setPwd("pwd");
		user.setId(1L);
		user.setName("周碧银");
		System.out.println(Json.toJSON(user));
		
		boolean rst = isOne("2") && isOne("1");
		System.out.println(rst);
		try {
			throw new RuntimeException("hello runtime");
		} catch (Exception ex) {
			log.error("test: {}", "abc", ex);
		}
	}
	
	protected static boolean isOne(String str) {
		System.out.println("check isOne");
		return "1".equals(str);
	}
}
