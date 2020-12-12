package com.echain.service.cmcc;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.echain.common.utils.Json;
import com.echain.common.utils.http.OKHttpUtil;
import com.google.common.collect.Maps;

import lombok.Data;
import okhttp3.Response;

public class UnknowApiWrapper {

	private static OKHttpUtil oKHttpUtil = OKHttpUtil.newInstance();
//			.addInterceptor(new LogInterceptor()).cookieJar(new DefaultCookieJar(new MemoryCookieStore()));
	
	private static String getAbsUrl(String method) {
		return "http://121.43.156.105/api/"+method;
	}
	
	public static void main(String[] args) throws IOException {
		
		String token = "d84acb25-bf1c-4722-bcb4-c924d8f08995";//login();
		
		GetPhoneRes phoneRes = getPhone(token);
		if(phoneRes == null || !phoneRes.isSuccess()) {
			System.out.println("没有取到手机号!");
			return;
		}
		String phone = phoneRes.getData();
		System.out.println("提取到手机号:"+phoneRes.getData());
		System.out.println("获取到验证码:"+getCode(phone,token, 120L));
	}
	
	/**
	 * 登录获取 token
	 * {"code":0,"data":"d84acb25-bf1c-4722-bcb4-c924d8f08995"}
	 * @return
	 * @throws IOException
	 */
	public static String login() throws IOException {
		Response res = oKHttpUtil.get(getAbsUrl("login?username=AK360&password=a0b923820dcc509a"));
		LoginResponse loginRes = getBody(res,LoginResponse.class);
		return loginRes.getData();
	}
	
	/**
	 * 获取可用的手机号
	 * {"code":0,"data":"13687309369"}
	 * @throws IOException
	 */
	public static GetPhoneRes getPhone(String token) throws IOException {
		Response res = oKHttpUtil.get(getAbsUrl("getPhone?token="+token));
		return getBody(res,GetPhoneRes.class);
	}
	
	
	/**
	 * 获取验证码
	 * @param phone
	 * @param token
	 * @param timeout 超时时间,单位秒
	 * @throws IOException
	 */
	public static String getCode(String phone,String token,Long timeout) throws IOException {
		Map<String,String> params = Maps.newHashMap();
		params.put("phone", phone);
		params.put("token", token);
		long times = 0;
		while(times <= timeout) {
			Response res = oKHttpUtil.get(getAbsUrl("getMessage"), params);
			GetCodeRes codeRes =getBody(res,GetCodeRes.class);
			//如果是空 ,或者失败 ,或者code == null
			if(codeRes == null || !codeRes.isSuccess() || StringUtils.isBlank(codeRes.getData())) {
				times++;
				try {Thread.sleep(1000);} catch (InterruptedException e) {}
				continue;
			}
			return codeRes.getData();
		}
		return null;
	}
	
	private static <T> T getBody(Response res,Class<T> clazz) throws IOException {
		String body = getBody(res);
		System.out.println(body);
		return Json.parse(body, clazz);
	}
	
	private static String getBody(Response res) throws IOException {
		return res.body().string();
	}
}

@Data
class LoginResponse{
	private String data;
}

@Data
class GetPhoneRes{
	private Integer code;
	private String msg;
	private String data;
	
	public boolean isSuccess() {
		return code!=null && code == 0;
	}
}

@Data
class GetCodeRes{
	private Integer code;
	private String msg;
	private String data;
	
	public boolean isSuccess() {
		return code!=null && code == 0;
	}
}