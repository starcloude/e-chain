package com.echain.web.cmcc;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.echain.common.utils.Json;
import com.echain.common.utils.http.OKHttpUtil;
import com.echain.common.utils.http.cookie.CookieStore;
import com.echain.common.utils.http.cookie.DefaultCookieJar;
import com.echain.common.utils.http.cookie.MemoryCookieStore;
import com.echain.web.controller.cmcc.JSEncryptUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.Maps;

import lombok.Data;
import okhttp3.Cookie;


public class Touch10086LoginWrapper {
	
	private static CookieStore cookieStore = new MemoryCookieStore();

	private File cookieFile = new File("d://10086.txt");
	
	private String phone = "15001300920";
	
	private static OKHttpUtil oKHttpUtil = OKHttpUtil.newInstance()
//			.addInterceptor(new LogInterceptor())
			.header("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 12_1_4 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/12.0 Mobile/15E148 Safari/604.1")
			.header("Referer","https://login.10086.cn/html/login/touch.html")
			.header("Connection","keep-alive")
			.cookieJar(new DefaultCookieJar(cookieStore))
			;
	
	protected String loadCookie() {
		try {
			return FileUtils.readFileToString(cookieFile,"UTF-8");
		} catch (IOException e) {
			return null;
		}
	}
	
	protected void refreshCookie(String cookie) {
		try {
			FileUtils.write(cookieFile, cookie,"UTF-8", false);
			System.out.println("\n\n"+cookie+"\n写入成功\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		Touch10086LoginWrapper touch = new Touch10086LoginWrapper();
		//登录 并写cookie
		touch.login();
		
		//加载cookie,读取 话费信息
		touch.testCheckFee();
		
	}
	
	
	public void testCheckFee() throws Exception{
		initCookie();
		System.out.println("检查话费......");
		while(true) {
			try {
				checkFee(phone);
				Thread.sleep(60000);
			}catch(Exception ex) {
				break;
			}
		}
	}
	
	
	
	public void login() throws Exception {
		
		index(phone);
		System.out.println("index.....");
//		System.out.println(JSONObject.toJSON(cookies.getCookieStore().getCookies()));
	
		checkNum(phone);
		System.out.println("checkNUm.....");
		
		initSendFlag();
		System.out.println("初始化SendFlag.....");
		
		String token = loadToken(phone);
//		System.out.println("加载token.......");
		if(StringUtils.isBlank(token)) {
			return;
		}
		
		boolean isSuccess = sendRadomCode(phone, token);
		System.out.println("发送验证码......");
		if(!isSuccess) {
			System.out.println("验证码发送失败!");
			return;
		}
		
		Scanner input=new Scanner(System.in);
		System.out.print("请您输入验证码:\n");
		String code=input.next(); //输入字符串型
		input.close();
		
		System.out.println(phone);
		System.out.println(code);
		String pwd = JSEncryptUtil.encrypt(code);
		String encPhone = JSEncryptUtil.encrypt(phone);
		System.out.println(encPhone);
		System.out.println(pwd);
		String json = checkLogin(encPhone, pwd);

		Map<String,String> loginJson = Json.toMap(json, String.class,String.class);
		
		if(!"0000".equals(loginJson.get("code"))) {
			System.out.println("登录失败!" +loginJson.get("desc"));
			return;
		}
		
		String artifact = loginJson.get("artifact");
		String assertAcceptURL=loginJson.get("assertAcceptURL");
		
		if(StringUtils.isBlank(assertAcceptURL)) {
			System.out.println("登录异常!");
			return ;
		}
		
		String backUrl=String.format("https://touch.10086.cn/i/mobile/home.html?welcome=%s", System.currentTimeMillis());
		String backUrlEncode ="";
		try{
			backUrlEncode = URLEncoder.encode(backUrl,"utf-8");
		}catch(Exception ex) {
			return;
		}
		String url302 = String.format("%s?backUrl=%s&artifact=%s",assertAcceptURL,backUrlEncode,artifact);
		//http://shop.10086.cn/i/v1/auth/getArtifact?backUrl=http%3A%2F%2Fshop.10086.cn%2Fi%2F&artifact=e920ed30212244c9b8c1c9d2e8ca2b76
		System.out.println(url302);
		oKHttpUtil.get(url302);
		
		//访问个人中心
		System.out.println(backUrl);
		oKHttpUtil.get(backUrl);

		System.out.println("\n\n");
		echoCookie();
		System.out.println("\n\n");
		
		System.out.println("检查话费......");
		checkFee(phone);
		echoCookie();
	}
	
	private void initCookie() {
		Map<String,List<Cookie>> cookies = Maps.newHashMap();
		String cookieStr = loadCookie();
		if(StringUtils.isBlank(cookieStr)) {
			return;
		}
		TypeReference<Map<String,List<SerializableCookie>>> type = new TypeReference<Map<String,List<SerializableCookie>>>(){};
		
		Map<String,List<SerializableCookie>> serializableCookies = Json.readValue(cookieStr,  type);
		if(serializableCookies == null) {
			return;
		}
		serializableCookies.entrySet().forEach(e->{
			cookies.put(e.getKey(), e.getValue().stream().map(c->{return c.toCookie();}).collect(Collectors.toList()));
		});
		cookieStore.initCookies(cookies);
	}
	
	private void echoCookie() {
		Map<String,List<SerializableCookie>> map = com.google.common.collect.Maps.newHashMap();
		cookieStore.getCookies().entrySet().forEach(e->{
			map.put(e.getKey(), e.getValue().stream().map(t->{return new SerializableCookie(t);}).collect(Collectors.toList()));
		});
		String cookie  = Json.toJSON(map);
		refreshCookie(cookie);
		System.out.println(cookie);
	}
	
	private void index(String phone) {
		String url="https://login.10086.cn/html/login/touch.html";
		try {
			oKHttpUtil.get(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void checkNum(String phone) {
		String url="https://login.10086.cn/chkNumberAction.action";
		try {
			Map<String,String> param = new HashMap<String, String>();
			param.put("userName", phone);
			oKHttpUtil.post(url, param);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void initSendFlag() {
		String url=String.format("https://login.10086.cn/sendflag.htm?timestamp=%s", System.currentTimeMillis());
		try {
			oKHttpUtil.get(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private String loadToken(String phone) {
		String url="https://login.10086.cn/loadToken.action";
		Map<String,String> param = new  HashMap<String, String>();
		param.put("userName", phone);
		try {
			String json = oKHttpUtil.post(url, param).body().string();
			System.out.println("loadToken:"+json);
			Map<String,String> jo = Json.toMap(json, String.class, String.class);
			return jo.get("result");
		}catch(Exception ex) {
			return null;
		}
	}
	
	private boolean sendRadomCode(String phone,String token) {
		String url="https://login.10086.cn/sendRandomCodeAction.action";
		
		Map<String,String> param = new  HashMap<String, String>();
		param.put("userName", phone);
		param.put("type", "01");
		param.put("channelID", "12014");
		try {
			String json = oKHttpUtil
					.header("Xa-before", token)
					.post(url, param).body().string();//  HttpClientUtil.post(config.url(url).headers(codeHeader).map(param));
			System.out.println("sendRandomCodeAction:"+json);
			return "0".equals(json);
		}catch(Exception ex) {
			return false;
		}
	}
	
	public String checkLogin(String phone,String pwd) throws Exception {
		Map<String,String> map = com.google.common.collect.Maps.newHashMap();
		map.put("accountType", "01");
		map.put("pwdType", "02");
		map.put("account", phone);
		map.put("password", pwd);
		map.put("inputCode", "");
		map.put("backUrl", "https://touch.10086.cn/i/");
		map.put("rememberMe", "0");
		map.put("channelID", "12014");
		map.put("loginMode", "03");
		map.put("protocol", "https:");
		map.put("timestamp", System.currentTimeMillis()+"");

		String url = "https://login.10086.cn/login.htm";
		String json = oKHttpUtil.post(url, map).body().string();//HttpClientUtil.post(config.url(url).map(map));
		System.out.println("Login : "+json);
		return json;
	}
	
	protected void checkScore(String phone) {
		String url="https://touch.10086.cn/i/v1/cust/creditPointQry/"+phone+"?channel=02";
		try {
			String json = oKHttpUtil.get(url).body().string();
			System.out.println("积分信息:[ "+json+" ]");
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void checkFee(String phone) {
		String url="https://shop.10086.cn/i/v1/fee/real/"+phone+"?_="+System.currentTimeMillis();
		try {
			//访问首页
			oKHttpUtil.get("https://touch.10086.cn/i/mobile/rechargecredit.html?welcome="+System.currentTimeMillis());
			//查询话费
			String json = oKHttpUtil.get(url).body().string();
			System.out.println(new Date()+" 话费信息:[ "+json+" ]");
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}

@Data
class SerializableCookie implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String value;
	private long expiresAt;
	private String domain;
	private String path;
//	private boolean secure;
//	private boolean httpOnly;
//	private boolean persistent;
//	private boolean hostOnly;
	
	public Cookie toCookie() {
		Cookie cookie = new Cookie.Builder()
				.name(getName())
				.domain(getDomain())
				.value(getValue())
				.path(getPath())
				//.httpOnly(isHttpOnly())
//				.hostOnlyDomain(domain)
				.expiresAt(getExpiresAt())
				.build();
		return cookie;
	}
	
	public void fromCookie(Cookie cookie) {
		setName(cookie.name());
		setValue(cookie.value());
		setDomain(cookie.domain());
		setPath(cookie.path());
		setExpiresAt(cookie.expiresAt());
//		setHttpOnly(cookie.httpOnly());
//		setPersistent(cookie.persistent());
//		setSecure(cookie.secure());
//		setHostOnly(cookie.hostOnly());
	}
	
	public SerializableCookie() {
		
	}
	
	public SerializableCookie(Cookie cookie) {
		fromCookie(cookie);
	}
	
}
