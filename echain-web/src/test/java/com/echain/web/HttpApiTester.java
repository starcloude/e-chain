package com.echain.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.assertj.core.util.Lists;

import com.echain.common.utils.Json;
import com.echain.common.utils.http.LogInterceptor;
import com.echain.common.utils.http.OKHttpUtil;
import com.echain.common.utils.http.cookie.DefaultCookieJar;
import com.echain.common.utils.http.cookie.MemoryCookieStore;
import com.echain.domain.business.user.UserQuestion;
import com.google.common.collect.Maps;

import okhttp3.Cookie;
import okhttp3.Headers;
import okhttp3.Response;

public class HttpApiTester {

	private static String getUrl(String path) {
		return String.format("http://localhost:8088%s", path);
	}
	
	public static void main(String[] args) throws Exception {
//		testEchainLogin();//登录
		
//		testGetSession();//当前用户信息
		
//		testAddQuestion();//设置问题
		
//		testQuestionSelect();//问题选项
		
//		testQuestionQuery();//获取用户问题
		
//		testForgetPwd();//忘记密码
		
//		String address = "E1111111111111111,E2222222222222222,E3333333333333333";
//		String address = "E1111111111111111,E2222222222222222";
//		String address = "E1111111111111111";
		String address = "";
		int length = address.split(",").length;
		System.out.println(address);
		System.out.println(length);
		int r = (int)(Math.random()*length);
		System.out.println(r);
		System.out.println(address.split(",")[r]);
	}
	
	/**
	 * 测试忘记密码
	 * @throws IOException
	 */
	static void testForgetPwd() throws IOException {
		Map<String,String> params = Maps.newLinkedHashMap();
		params.put("account", "13313313313");
		params.put("password", "123456");
		List<UserQuestion> mapList = new ArrayList<UserQuestion>();
		UserQuestion uq = new UserQuestion();
		uq.setQuestionId(1L);
		uq.setAnswer("a");
		mapList.add(uq);
		
		uq = new UserQuestion();
		uq.setQuestionId(2L);
		uq.setAnswer("b");
		mapList.add(uq);
		
		uq = new UserQuestion();
		uq.setQuestionId(3L);
		uq.setAnswer("c");
		mapList.add(uq);
		
		params.put("questionMap", Json.toJSON(mapList));
		
		Map<String,String> headers = Maps.newHashMap();
		headers.put("h1", "v1");
		OKHttpUtil okHttpUtil = OKHttpUtil.newInstance()
				.cookieJar(new DefaultCookieJar(new MemoryCookieStore()))
				.addInterceptor(new LogInterceptor())
				.header(Headers.of(headers));
		
		Response response = okHttpUtil.post(getUrl("/api/forgetPwd"), params);
		echoResponse(response);
	}
	
	/**
	 * 测试获取用户问题
	 * @throws IOException
	 */
	static void testQuestionQuery() throws IOException {
		Response response = OKHttpUtil.newInstance()
				.header("Cookie", getCookie())
				.addInterceptor(new LogInterceptor()).get(getUrl("/api/question/query?userId="+100001));
		echoResponse(response);
	}
	
	/**
	 * 测试获取问题选项
	 * @throws IOException
	 */
	static void testQuestionSelect() throws IOException {
		Response response = OKHttpUtil.newInstance()
				.header("Cookie", getCookie())
				.addInterceptor(new LogInterceptor()).get(getUrl("/api/question/select"));
		echoResponse(response);
	}
	
	/**
	 * 测试新增问题
	 * @throws IOException
	 */
	static void testAddQuestion() throws IOException {
		Map<String,String> params = Maps.newLinkedHashMap();
		params.put("userId", "100001");
		List<UserQuestion> mapList = new ArrayList<UserQuestion>();
		UserQuestion uq = new UserQuestion();
		uq.setQuestionId(1L);
		uq.setAnswer("a");
		mapList.add(uq);
		
		uq = new UserQuestion();
		uq.setQuestionId(2L);
		uq.setAnswer("b");
		mapList.add(uq);
		
		uq = new UserQuestion();
		uq.setQuestionId(3L);
		uq.setAnswer("c");
		mapList.add(uq);
		
		params.put("questionMap", Json.toJSON(mapList));
		
		Map<String,String> headers = Maps.newHashMap();
		headers.put("h1", "v1");
		OKHttpUtil okHttpUtil = OKHttpUtil.newInstance()
				.cookieJar(new DefaultCookieJar(new MemoryCookieStore()))
				.addInterceptor(new LogInterceptor())
				.header(Headers.of(headers));
		
		Response response = okHttpUtil.post(getUrl("/api/question/add"), params);
		echoResponse(response);
	}
	
	/**
	 * 测试登录
	 * @throws IOException
	 */
	static void testEchainLogin() throws IOException {
		Map<String,String> params = Maps.newLinkedHashMap();
		params.put("userName", "13313313313");
		params.put("userPwd", "123456");
		params.put("r", "123456");
		params.put("captchaCode", "67798");
		
		Map<String,String> headers = Maps.newHashMap();
		headers.put("h1", "v1");
		
		OKHttpUtil okHttpUtil = OKHttpUtil.newInstance()
				.cookieJar(new DefaultCookieJar(new MemoryCookieStore()))
				.addInterceptor(new LogInterceptor())
				.header(Headers.of(headers));
		
		//登录
		Response response = okHttpUtil.post(getUrl("/api/login"), params);
		echoResponse(response);
		
		//获取session
		response = okHttpUtil.get(getUrl("/api/session"));
		echoResponse(response);
	}
	
//	{"success":true,"msg":"","code":0,"result":{"user":{"id":100001,"yn":0,"nickName":"小三","account":"13313313313","accountType":2,"state":0}}}
//	echo cookie 开始 ......
//	key:localhost
//	SHARE_JSESSIONID=23732c67-bac9-4f8c-8e75-bbe2b56b35c1; path=/; httponly
//	rememberMe=deleteMe; max-age=0; path=/
//	echo cookie 结束 ......

	static void testGetSession() throws IOException{
		Response response = OKHttpUtil.newInstance()
				.header("Cookie", getCookie())
				.addInterceptor(new LogInterceptor()).get(getUrl("/api/session"));
		echoResponse(response);
	}
	
	static String getCookie() {
		List<Cookie> cookies = Lists.newArrayList();
		cookies.add(new Cookie.Builder()
				.domain("localhost")
				.name("SHARE_JSESSIONID")
				.value("0bf52ff2-f2fa-4064-8ecb-0205de763c8b")
				.path("/")
				.build());
		return cookies.stream().map(Cookie::toString).collect(Collectors.joining());
	}
	
	static void echoResponse(Response response) throws IOException {
		if(response != null) {
			System.out.println("======================");
			System.out.println(response.code());
			System.out.println(response.body().string());
			System.out.println("======================");
		}
	}
}
