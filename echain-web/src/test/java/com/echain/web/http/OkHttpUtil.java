package com.echain.web.http;



import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.assertj.core.util.Lists;

import lombok.Getter;
import lombok.Setter;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @see com.echain.common.utils.http.OKHttpUtil
 * @author 作者B
 *
 */
@Deprecated
public class OkHttpUtil {
	
//	static OkHttpClient oKHttpClient = new OkHttpClient().newBuilder().addInterceptor(new  LogInterceptor()).build();
	private final static HashMap<String, List<Cookie>> cookieStore = new HashMap<>();
	
	static OkHttpClient oKHttpClient = new OkHttpClient().newBuilder()
			.followRedirects(false)
			//日志
//			.addInterceptor(new  LogInterceptor())
			//cookie 保持
			.cookieJar(new CookieJar() {
				@Override
				public void saveFromResponse(HttpUrl url, List<okhttp3.Cookie> cookies) {
					List<Cookie> dbCookies = cookieStore.get(url.host());
					dbCookies = dbCookies == null ? Lists.newArrayList(): dbCookies;
					dbCookies.addAll(cookies);
					cookieStore.put(url.host(), dbCookies);
				}
				@Override
				public List<okhttp3.Cookie> loadForRequest(HttpUrl url) {
					List<Cookie> cookies = cookieStore.get(url.host());
                    return cookies != null ? cookies : new ArrayList<Cookie>();
				}
			})
			.build();
	
	
	/**
	 * post form 表达提交
	 * @param requestBuilder
	 * @param params
	 * @return
	 */
	public static Request.Builder post(Request.Builder requestBuilder,Map<String,String> params) {
		FormBody.Builder body = new FormBody.Builder();
		params.entrySet().forEach(e->{
			body.add(e.getKey(),e.getValue());
		});
		return requestBuilder.post(body.build());
	}

	/**
	 * post json
	 * @param requestBuilder
	 * @param json
	 * @return
	 */
	public static Request.Builder post(Request.Builder requestBuilder,String json) {
		MediaType JSON = MediaType.parse("application/json; charset=utf-8");
		RequestBody body = RequestBody.create(JSON, json);
		return requestBuilder.post(body);
	}
	
	/**
	 * cookie 输出
	 */
	public static void echoCookie() {
		System.out.println("echo cookie 开始 ......");
		cookieStore.entrySet().forEach(entry->{
			System.out.println("key:"+entry.getKey());
			entry.getValue().forEach(System.out::println);
		});
		System.out.println("echo cookie 结束 ......\n");
	}
	
	/**
	 * 异步
	 * @param request
	 */
	public static void async(Request request) {
		async(request,new Callback()
        {
			@Override
			public void onFailure(Call arg0, IOException ex) {
				ex.printStackTrace();
			}
			@Override
			public void onResponse(Call arg0, Response arg1) throws IOException {
				System.out.println(arg1.body().string());
			}
        });
	}
	
	/**
	 * 异步执行
	 * @param request
	 * @param callBack
	 */
	public static void async(Request request,Callback callBack) {
		oKHttpClient.newCall(request).enqueue(callBack);
	}
	
	/**
	 * 同步
	 * @param request
	 * @throws IOException
	 */
	public static void sync(Request request) throws IOException {
		Response response = oKHttpClient.newCall(request).execute();
		System.out.println(response.code());
		if(response.code() == 302) {
			//如果是302 获取em.out.println(response.header("Location"));
		}
		System.out.println(response.body().string());
	}
	
	/**
	 * get url 拼接参数
	 * @param url
	 * @param params
			Syst
	 * @return
	 */
	public static HttpUrl get(String url,Map<String,String> params) {
		HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
		if(params == null || params.isEmpty()) {
			return urlBuilder.build();
		}
		params.forEach((k,v)->{
			urlBuilder.addQueryParameter(k, v);
		});
		return urlBuilder.build();
	}
	
	public static Request.Builder get(Request.Builder requestBuilder,String url,Map<String,String> params) {
		return requestBuilder.url(get(url, params));
	}
}

@Getter
@Setter
class Us implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String name;
	//transient 不序列化
	private transient String pwd;
}