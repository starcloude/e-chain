package com.echain.service.cmcc;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import com.echain.common.utils.Json;
import com.echain.common.utils.http.OKHttpUtil;
import com.google.common.collect.Maps;

import okhttp3.Response;

public class LovshsbApiWrapper {

	private static OKHttpUtil oKHttpUtil = OKHttpUtil.newInstance();
//			.addInterceptor(new LogInterceptor()).cookieJar(new DefaultCookieJar(new MemoryCookieStore()));
	
	protected static String getAbsUrl(String method) {
		return "http://codes.lovehsb.com/api/push/"+method;
	}
	
	public static void main(String[] args) throws IOException {
		GetPhoneResponse phoneRes = getPhone();
		if(phoneRes == null || !phoneRes.isSuccess()) {
			System.out.println("没有取到手机号!");
			return;
		}
		System.out.println("提取到手机号:"+phoneRes.getPhone() +" 订单号:"+phoneRes.getOrderId());
		System.out.println("获取到验证码:"+getCode(phoneRes.getOrderId(), 120L));
	}
	
	/**
	 * 获取可用的手机号
	 * @throws IOException
	 */
	public static GetPhoneResponse getPhone() throws IOException {
		Map<String,String> params = initPostParams();
		params.put("productid", "10004");
		params.put("time", System.currentTimeMillis()+"");
		params.put("sign", getSign(params));
		Response res = oKHttpUtil.post(getAbsUrl("getPhone"), params);
		return getBody(res,GetPhoneResponse.class);
	}
	
	/**
	 * 初始化post参数
	 * @return
	 */
	protected static Map<String,String> initPostParams(){
		Map<String,String> params = Maps.newTreeMap();
		params.put("userid", "10003");
		return params;
	}
	
	/**
	 * 签名
	 * @param params
	 * @return
	 */
	protected static String getSign(Map<String,String> params) {
		StringBuilder sbd = new StringBuilder();
		params.entrySet().forEach(e->{
			if("sign".equals(e.getKey())) {
				return;
			}
			sbd.append(e.getKey()).append("=").append(e.getValue()).append("&");
		});
		sbd.append("key=cPdCrKsZenHTIWxLqBOM");
		return DigestUtils.md5Hex(sbd.toString());
	}
	
	public static void sendUseTime(String orderId) throws IOException {
		Map<String,String> params = initPostParams();
		params.put("orderid", orderId);
		params.put("sign", getSign(params));
		System.out.println(oKHttpUtil.post(getAbsUrl("sendUseTime"), params).body().string());
	}
	
	/**
	 * 获取验证码
	 * @param orderId
	 * @param timeout 超时时间,单位秒
	 * @throws IOException
	 */
	public static String getCode(String orderId,Long timeout) throws IOException {
		Map<String,String> params = initPostParams();
		params.put("orderid", orderId);
		params.put("sign", getSign(params));
		long times = 0;
		while(times <= timeout) {
			Response res = oKHttpUtil.post(getAbsUrl("getCode"), params);
			GetCodeResponse codeRes =getBody(res,GetCodeResponse.class);
			//如果是空 ,或者失败 ,或者code == null
			if(codeRes == null || !codeRes.isSuccess() || StringUtils.isBlank(codeRes.getCode())) {
				times++;
				try {Thread.sleep(1000);} catch (InterruptedException e) {}
				continue;
			}
			return codeRes.getCode();
		}
		return null;
	}
	
	protected static <T> T getBody(Response res,Class<T> clazz) throws IOException {
		String body = getBody(res);
		return Json.parse(body, clazz);
	}
	
	protected static String getBody(Response res) throws IOException {
		return res.body().string();
	}
}
