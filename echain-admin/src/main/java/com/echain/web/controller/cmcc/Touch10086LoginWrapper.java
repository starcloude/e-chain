package com.echain.web.controller.cmcc;

import java.io.Serializable;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.echain.common.utils.Json;
import com.echain.common.utils.http.OKHttpUtil;
import com.echain.common.utils.http.cookie.CookieStore;
import com.echain.common.utils.http.cookie.DefaultCookieJar;
import com.echain.common.utils.http.cookie.MemoryCookieStore;
import com.echain.service.cmcc.CookieResponse;
import com.echain.service.cmcc.GetPhoneResponse;
import com.echain.service.cmcc.LovshsbApiWrapper;
import com.google.common.collect.Maps;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Cookie;
import okhttp3.Response;

@Service
@Slf4j
public class Touch10086LoginWrapper {
	
	private static CookieStore cookieStore = new MemoryCookieStore();
	
	private static OKHttpUtil oKHttpUtil = OKHttpUtil.newInstance()
//			.addInterceptor(new LogInterceptor())
			.header("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 12_1_4 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/12.0 Mobile/15E148 Safari/604.1")
			.header("Referer","https://login.10086.cn/html/login/touch.html")
			.header("Connection","keep-alive")
			.connectTimeout(180L)
			.cookieJar(new DefaultCookieJar(cookieStore))
			;
	
	
	@Async
	public void login(String callBack){
		//callBack = "http://localhost:8088/api/10086/getCookieResult";
		try {
			GetPhoneResponse phoneResponse = LovshsbApiWrapper.getPhone();
			if(phoneResponse == null || !phoneResponse.isSuccess()) {
				log.error("获取不到手机号!");
				return;
			}
			log.info("获取到手机号~ {}", Json.toJSON(phoneResponse));
			String phone = phoneResponse.getPhone();
//			phone = "15001300920";
			
			if(!index(phone)) {
				return;
			}
		
			if(!checkNum(phone)) {
				return;
			}
			
			if(!initSendFlag()) {
				return;
			}
			
			String token = loadToken(phone);
			
			if(StringUtils.isBlank(token)) {
				log.error("获取用户"+phone+"token失败!");
				return;
			}
			
			String code = "";
			boolean isSuccess = sendRadomCode(phone, token);
			if(!isSuccess) {
				log.error(phone+"短信验证码发送失败!");
				return;
			}
			
			/*Scanner input=new Scanner(System.in);
			System.out.print("请您输入验证码:\n");
			String code = input.next(); //输入字符串型
			input.close();*/
			
			//接收短信验证码
			code = LovshsbApiWrapper.getCode(phoneResponse.getOrderId(), 120L);
			if (StringUtils.isBlank(code)) {
				log.error("手机号:"+phone+"未收到短信验证码!");
				return;
			}
			
			log.info("手机号:{} 验证码:{} 开始登录!",phone,code);
			String pwd = JSEncryptUtil.encrypt(code);
			String encPhone = JSEncryptUtil.encrypt(phone);
			
			String json = checkLogin(encPhone, pwd);

			Map<String,String> loginJson = Json.toMap(json, String.class,String.class);
			if(!"0000".equals(loginJson.get("code"))) {
				log.error("登录失败! {} - {}",phone,loginJson.get("desc"));
				return;
			}
			
			String artifact = loginJson.get("artifact");
			String assertAcceptURL=loginJson.get("assertAcceptURL");
			
			if(StringUtils.isBlank(assertAcceptURL)) {
				log.error("登录异常! :{}",phone);
				return;
			}
			
			String backUrl=String.format("https://touch.10086.cn/i/mobile/home.html?welcome=%s", System.currentTimeMillis());
			String backUrlEncode ="";
			try{
				backUrlEncode = URLEncoder.encode(backUrl,"utf-8");
			}catch(Exception ex) {
				log.error("获取用户:{}backUrl失败!",phone);
				return;
			}
			String url302 = String.format("%s?backUrl=%s&artifact=%s",assertAcceptURL,backUrlEncode,artifact);

			oKHttpUtil.get(url302);
			
			//访问个人中心
			oKHttpUtil.get(backUrl);

			String cookie = echoCookie();
			
			CookieResponse cookieRes = new CookieResponse();
			cookieRes.setPhone(phone);
			cookieRes.setCookie(cookie);
			cookieRes.setStartTime(System.currentTimeMillis());
			cookieRes.setOrderId(phoneResponse.getOrderId());
			
			String postJson = Json.toJSON(cookieRes);
			Map<String,String> pp = Maps.newHashMap();
			pp.put("content", postJson);
			Response res = oKHttpUtil.post(callBack, pp);
			log.info("创建cookie 成功,回调:{} 参数:{} 返回:{}",callBack,Json.toJSON(pp),res.body().string());
		}catch(Exception ex) {
			log.error("获取登录cookie失败! ",ex);
		}
	}
	
	private String echoCookie() {
		Map<String,List<SerializableCookie>> map = com.google.common.collect.Maps.newHashMap();
		cookieStore.getCookies().entrySet().forEach(e->{
			map.put(e.getKey(), e.getValue().stream().map(t->{return new SerializableCookie(t);}).collect(Collectors.toList()));
		});
		String cookie  = Json.toJSON(map);
		return cookie;
	}
	
	private boolean index(String phone) {
		String url="https://login.10086.cn/html/login/touch.html";
		try {
			oKHttpUtil.get(url);
			return true;
		} catch (Exception e) {
			log.error("访问https://login.10086.cn/html/login/touch.html发生异常! ",e);
			return false;
		}
	}
	
	private boolean checkNum(String phone) {
		String url="https://login.10086.cn/chkNumberAction.action";
		try {
			Map<String,String> param = new HashMap<String, String>();
			param.put("userName", phone);
			oKHttpUtil.post(url, param);
			return true;
		} catch (Exception e) {
			log.error("访问https://login.10086.cn/chkNumberAction.action发生异常! ",e);
			return false;
		}
	}
	
	private boolean initSendFlag() {
		String url=String.format("https://login.10086.cn/sendflag.htm?timestamp=%s", System.currentTimeMillis());
		try {
			oKHttpUtil.get(url);
			return true;
		} catch (Exception e) {
			log.error("访问 {} 发生异常! ",url,e);
			return false;
		}
	}
	private String loadToken(String phone) {
		String url="https://login.10086.cn/loadToken.action";
		Map<String,String> param = new  HashMap<String, String>();
		param.put("userName", phone);
		try {
			String json = oKHttpUtil.post(url, param).body().string();
			Map<String,String> jo = Json.toMap(json, String.class, String.class);
			return jo.get("result");
		}catch(Exception ex) {
			log.error("访问 {} 发生异常! ",url,ex);
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
			boolean rst = "0".equals(json);
			if(!rst) {
				log.error("验证码发送失败~ {}  {}",phone,json);
			}
			return rst;
		}catch(Exception ex) {
			log.error("访问 {} 发生异常! ",url,ex);
			return false;
		}
	}
	
	private String checkLogin(String phone,String pwd) throws Exception {
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
		log.info("开始登录 url:{} 参数:{}",url,Json.toJSON(map));
		String json = oKHttpUtil.post(url, map).body().string();//HttpClientUtil.post(config.url(url).map(map));
		return json;
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
	
	public Cookie toCookie() {
		Cookie cookie = new Cookie.Builder()
				.name(getName())
				.domain(getDomain())
				.value(getValue())
				.path(getPath())
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
	}
	
	public SerializableCookie() {
		
	}
	
	public SerializableCookie(Cookie cookie) {
		fromCookie(cookie);
	}
	
}
