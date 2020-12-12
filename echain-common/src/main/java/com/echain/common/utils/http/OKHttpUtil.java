package com.echain.common.utils.http;


import java.io.File;
import java.io.IOException;
import java.net.Proxy;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.MapUtils;

import okhttp3.Authenticator;
import okhttp3.Callback;
import okhttp3.ConnectionPool;
import okhttp3.CookieJar;
import okhttp3.Credentials;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Route;
import okio.BufferedSink;
import okio.Okio;

public class OKHttpUtil {
	
//	private static OKHttpUtil instance;
	
	private OkHttpClient.Builder httpClientBuilder;
	
	private OkHttpClient httpClient;

	private Request.Builder requestBuilder;

	private MediaType jsonMediaType = MediaType.parse("application/json; charset=utf-8");
	
	/**
	 * 开始类
	 * @return
	 */
	public static OKHttpUtil newInstance() {
//		if(instance != null) {
//			return instance;
//		}
//		instance =  new OKHttpUtil();
		return new OKHttpUtil();
	}
	
	/**
	 * 构造函数
	 */
	private OKHttpUtil() {
		httpClientBuilder = new OkHttpClient.Builder().connectTimeout(120,TimeUnit.SECONDS).readTimeout(120,TimeUnit.SECONDS);
		requestBuilder = new Request.Builder();
	}
	
	/**
	 * 设置代理
	 * @return
	 */
	public OKHttpUtil proxy(Proxy proxy) {
		httpClientBuilder.proxy(proxy);
		return this; 
	}
	
	/**
	 * 代理鉴权
	 * @param proxyAuthenticator
	 * @return
	 */
	public OKHttpUtil proxyAuthenticator(Authenticator proxyAuthenticator) {
		httpClientBuilder.proxyAuthenticator(proxyAuthenticator);
		return this; 
	}
	
	/**
	 * 普通账号密码鉴权
	 * @param username
	 * @param password
	 * @return
	 */
	public OKHttpUtil proxyAuthenticator(String username,String password) {
		proxyAuthenticator(new Authenticator() {
            public Request authenticate(Route route, Response response) throws IOException {
                String credential = Credentials.basic(username, password);
                return response.request().newBuilder()
                        .header("Proxy-Authorization", credential)
                        .build();
            }
        });
		return this; 
	}
	
	/**
	 * 链接 超时时间 默认秒
	 * @param timeout
	 * @return
	 */
	public OKHttpUtil connectTimeout(Long timeout) {
		connectTimeout(timeout, TimeUnit.SECONDS);
		return this;
	}
	
	/**
	 * 链接 超时时间
	 * @param timeout
	 * @param unit
	 * @return
	 */
	public OKHttpUtil connectTimeout(Long timeout,TimeUnit unit) {
		httpClientBuilder.connectTimeout(timeout, unit);
		return this;
	}
	
	/**
	 * 读取超时时间 默认秒
	 * @param timeout
	 * @return
	 */
	public OKHttpUtil readTimeout(Long timeout) {
		readTimeout(timeout, TimeUnit.SECONDS);
		return this;
	}
	
	/**
	 * 读取 超时时间
	 * @param timeout
	 * @param unit
	 * @return
	 */
	public OKHttpUtil readTimeout(Long timeout,TimeUnit unit) {
		httpClientBuilder.readTimeout(timeout, unit);
		return this;
	}
	
	/**
	 * 连接池,解决内存溢出问题
	 * @param connectionPool
	 * @return
	 */
	public OKHttpUtil connectionPool(ConnectionPool connectionPool) {
		httpClientBuilder.connectionPool(connectionPool);
		return this;
	}
	
	/**
	 * 设置cookiejar 保持cookie
	 * @param cookieJar
	 * @return
	 */
	public OKHttpUtil cookieJar(CookieJar cookieJar) {
		httpClientBuilder.cookieJar(cookieJar);
		return this;
	}

	/**
	 * 设置clientBuilder
	 * @param builder
	 * @return
	 */
	public Builder clientBuilder(Builder builder) {
		this.httpClientBuilder = builder;
		return this.httpClientBuilder;
	}
	
	/**
	 * 获取clientBuilder
	 * @return
	 */
	public Builder clientBuilder() {
		return this.httpClientBuilder;
	}
	
	
	/**
	 * 添加拦截器
	 * @param interceptor
	 * @return
	 */
	public OKHttpUtil addInterceptor(Interceptor interceptor) {
		httpClientBuilder.addInterceptor(interceptor);
		return this;
	}

	/**
	 * 设置header
	 * @param name
	 * @param value
	 * @return
	 */
	public OKHttpUtil header(String name,String value) {
		requestBuilder.header(name, value);
		return this;
	}
	
	/**
	 * 设置header
	 * @param headers
	 * @return
	 */
	public OKHttpUtil header(Headers headers) {
		requestBuilder.headers(headers);
		return this;
	}
	
	/**
	 * 重新buildclient
	 * @return
	 */
	public OKHttpUtil buildClient() {
		httpClient = httpClientBuilder.build();
		return this;
	}

	/**
	 * get
	 * @param url
	 * @param params
	 * @return
	 * @throws IOException
	 */
	public Response get(String url,Map<String,String> params) throws IOException {
		return exec(requestBuilder.url(buildGetUrl(url, params)).get().build());
	}
	
	/**
	 * get
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public Response get(String url) throws IOException {
		Map<String,String> param  = null;
		return get(url,param);
	}
	
	/**
	 * 异步get
	 * @param url
	 * @param params
	 * @param callBack
	 * @throws IOException
	 */
	public void get(String url,Map<String,String> params,Callback callBack) throws IOException {
		exec(requestBuilder.url(buildGetUrl(url, params)).get().build(),callBack);
	}
	
	/**
	 * 异步get
	 * @param url
	 * @param callBack
	 * @throws IOException
	 */
	public void get(String url,Callback callBack) throws IOException {
		Map<String,String> param  = null;
		get(url,param, callBack);
	}
	
	
	
	/**
	 * post
	 * @param url
	 * @param params
	 * @return
	 * @throws IOException
	 */
	public Response post(String url,Map<String,String> params) throws IOException {
		FormBody.Builder body = new FormBody.Builder();
		if(MapUtils.isNotEmpty(params)) {
			params.entrySet().forEach(e->{
				body.add(e.getKey(),e.getValue());
			});
		}
		return exec(requestBuilder.url(url).post(body.build()).build());
	}
	
	/**
	 * post
	 * @param url
	 * @param params
	 * @param callBack
	 * @throws IOException
	 */
	public void post(String url,Map<String,String> params,Callback callBack) throws IOException {
		FormBody.Builder body = new FormBody.Builder();
		if(MapUtils.isNotEmpty(params)) {
			params.entrySet().forEach(e->{
				body.add(e.getKey(),e.getValue());
			});
		}
		exec(requestBuilder.url(url).post(body.build()).build(),callBack);
	}
	
	/**
	 * post json
	 * @param url
	 * @param json
	 * @return
	 * @throws IOException
	 */
	public Response post(String url,String json) throws IOException {
		return exec(requestBuilder.url(url).post(RequestBody.create(jsonMediaType, json)).build());
	}
	
	/**
	 * post json
	 * @param url
	 * @param json
	 * @param callBack
	 * @throws IOException
	 */
	public void post(String url,String json,Callback callBack) throws IOException {
		exec(requestBuilder.url(url).post(RequestBody.create(jsonMediaType, json)).build(),callBack);
	}
	
	/**
	 * get url 拼接参数
	 * @param url
	 * @param params
	 * @return
	 */
	private HttpUrl buildGetUrl(String url,Map<String,String> params) {
		HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
		if(MapUtils.isEmpty(params)) {
			return urlBuilder.build();
		}
		params.forEach((k,v)->{
			urlBuilder.addQueryParameter(k, v);
		});
		return urlBuilder.build();
	}
	
	/**
	 * 异步执行
	 * @param request
	 * @param callBack
	 */
	public void exec(Request request,Callback callBack) {
		if(httpClient == null) {
			buildClient();
		}
		httpClient.newCall(request).enqueue(callBack);
	}
	
	/**
	 * 同步
	 * @param request
	 * @throws IOException
	 */
	public Response exec(Request request) throws IOException {
		if(httpClient == null) {
			buildClient();
		}
		return httpClient.newCall(request).execute();
	}
	
	/**
	 * 文件下载
	 * @param response
	 * @param path
	 * @param fileName
	 * @throws IOException
	 */
	public void response2File(Response response,String path,String fileName) throws IOException {
		File file=new File(path+"/"+fileName);
        if (!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        BufferedSink sink =Okio.buffer((Okio.sink(file)));
        sink.writeAll(response.body().source());
        sink.flush();
        sink.close();
	}
}