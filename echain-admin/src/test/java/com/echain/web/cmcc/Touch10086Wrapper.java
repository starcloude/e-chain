package com.echain.web.cmcc;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.util.HtmlUtils;

import com.echain.common.utils.DoubleUtil;
import com.echain.common.utils.Json;
import com.echain.common.utils.http.LogInterceptor;
import com.echain.common.utils.http.OKHttpUtil;
import com.echain.common.utils.http.cookie.CookieStore;
import com.echain.common.utils.http.cookie.DefaultCookieJar;
import com.echain.common.utils.http.cookie.MemoryCookieStore;
import com.echain.web.cmcc.beans.ChargeOrder;
import com.echain.web.cmcc.beans.ChargeRequest;
import com.echain.web.cmcc.beans.CmccPayTypeEnum;
import com.echain.web.cmcc.beans.CmccResult;
import com.echain.web.cmcc.beans.MobileTypeEnum;
import com.echain.web.cmcc.beans.NumAreaResponse;
import com.echain.web.cmcc.beans.OrderPayResult;
import com.echain.web.cmcc.beans.OrderPayResultStatusEnum;
import com.echain.web.cmcc.beans.PayRuleResponse;
import com.echain.web.cmcc.beans.PayTypeEnum;
import com.echain.web.cmcc.beans.SaleRule;
import com.echain.web.cmcc.beans.SaveOrderResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.Maps;

import okhttp3.Cookie;
import okhttp3.Response;


public class Touch10086Wrapper {

	Pattern weChatPattern = Pattern.compile("form action=\"(.*?)\"");
	Pattern weChatOpenPattern = Pattern.compile("var url=\"(.*?)\"");
	
	private static CookieStore cookieStore = new MemoryCookieStore();

	private File cookieFile = new File("d://10086.txt");
	
	private static OKHttpUtil oKHttpUtil = OKHttpUtil.newInstance()
			.addInterceptor(new LogInterceptor())
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
	
	/**
	 * 初始化cookie
	 */
	protected void initCookie() {
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
	
	/**
	 * 刷新cookie
	 */
	protected void refreshCookie() {
		Map<String,List<SerializableCookie>> map = com.google.common.collect.Maps.newHashMap();
		cookieStore.getCookies().entrySet().forEach(e->{
			map.put(e.getKey(), e.getValue().stream().map(t->{return new SerializableCookie(t);}).collect(Collectors.toList()));
		});
		String cookie  = Json.toJSON(map);
		try {
			FileUtils.write(cookieFile, cookie,"UTF-8", false);
			System.out.println("\n\n"+cookie+"\n写入成功\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ChargeOrder createOrderOnly(ChargeRequest request) throws IOException {
    	String phone = request.getMobile();
    	Double money  = request.getMoney();
        
        long lStart = System.currentTimeMillis();
        try {
            //获取归属地省
            NumAreaResponse area = getNumArea(phone);
            if (area == null) {
//                logger.error("电话号码:{} 归属地获取失败!", phone);
                throw new RuntimeException("手机号:[" + phone + "]归属地查询失败!");
            }
            //获取缴费规则
            PayRuleResponse rule = getPayRule(phone, area);

            if (rule == null) {
//                logger.error("电话号码:{} 获取支付规则发生异常! ", phone);
                throw new RuntimeException("手机号:[" + phone + "]支付规则查询失败!");
            }
            
            SaleRule saleRule = rule.getLastRule();
            
            Double discount = Double.valueOf(1);
            Integer operateId = null; 
            if(saleRule != null) {
            	discount = saleRule.getSaleCode();
            	operateId = saleRule.getOperateId();
            }
            
            //实际支付金额
            Double amount = DoubleUtil.mul(money, discount);
//            logger.debug("电话号码:{} 充值金额:{} 实际支付金额:{}", phone, money, amount);

            //创建订单
            SaveOrderResponse order = createOrderTouch(phone, money, amount,operateId, area);
            
            if (order == null) {
                throw new RuntimeException("手机号:[" + phone + "]创建支付订单失败!");
            }
            
            if(order.getPayUrl().indexOf("SSOCheck.htm")>0) {
            	String newPayUrl = get302Url(order.getPayUrl());
            	if(StringUtils.isBlank(newPayUrl)) {
            		throw new RuntimeException("手机号:[" + phone + "]创建支付订单失败! 提取支付落地页失败! "+ order.getPayUrl());
            	}
            	order.setPayUrl(newPayUrl);
            }
            
            ChargeOrder ord = new ChargeOrder();
            ord.setOrderId(order.getOrderId());
            ord.setOrderJson(Json.toJSON(order));
            ord.setOrderPageUrl(order.getPayUrl());
            System.out.println("创建移动成功,方法耗时:"+(System.currentTimeMillis() - lStart)+" 毫秒!");
//            cmccUserService.recCmccUser(user.getUserNo(), order.getOrderId());
            return ord;
        } catch (RuntimeException ex) {
        	ex.printStackTrace();
        	throw (ex);
        }
	}

	
	private SaveOrderResponse createOrderTouch(String phone, Double money, Double amount,Integer operateId, NumAreaResponse area) throws IOException {
        String url = String.format("https://touch.10086.cn/i/v1/pay/saveorder/%s?provinceId=%s", phone, area.getProvCd());
        Map<String,Object> param = Maps.newHashMap();
        param.put("channel", "0003");
        param.put("activityNO", "");
        param.put("payWay", "WAP");
        if(Math.round(amount) - amount.intValue() == 0) {
        	param.put("amount", amount.intValue());
        }else {
        	param.put("amount", amount);
        }
        if(operateId != null) {
//        	jo.put("operateId", operateId);
        	param.put("operateId", 3215);
        }
        param.put("chargeMoney", money);
        param.put("choseMoney", money);
        param.put("homeProv", area.getProvCd());
        param.put("numFlag", "0");
        param.put("source", "");
        	
        String json="";
        try {
        	
	        json = oKHttpUtil
	        		.header("Referer", "https://touch.10086.cn/i/mobile/rechargecredit.html")
	        		.header("payPhoneNo", "13990691001")
	        		.post(url, Json.toJSON(param)).body().string();

	        if(StringUtils.isBlank(json)) {
	        	throw new RuntimeException("提交移动订单返回空串！！");
	        }
	        
	        CmccResult<SaveOrderResponse> rst = Json.readValue(json,new TypeReference<CmccResult<SaveOrderResponse>>() {});
	        System.out.println(json);
//	        handleException(phone,rst);
	        
	        //登录已经过期
	        if("500004".equals(rst.getRetCode())) {
	        	throw new RuntimeException("没登录,请先登录,cookie 已经过期!");
	        }
	        if(!rst.isSuccess()) {
	        	throw new RuntimeException("手机号:[" + phone + "]创建支付订单失败! " + rst.getRetMsg());
	        }
	        return rst.getData();
        }catch(RuntimeException ex) {
        	ex.printStackTrace();
//        	wechatWarningService.add("订单创建 - 提交订单异常  "+ex.getMessage());
        	throw ex;
        }
    }
	
	public String getPayUrl(ChargeRequest request, String orderJson) throws IOException {
		SaveOrderResponse order  = Json.parse(orderJson,SaveOrderResponse.class);
		
		long lStart = System.currentTimeMillis();
		String url = getPayUrl(order, request.getPayType());
        if(StringUtils.isBlank(url)) {
        	throw new RuntimeException("没有取到支付链接,请稍后重试!");
        }
        System.out.println("获取支付链接成功,方法耗时:"+(System.currentTimeMillis() - lStart)+" 毫秒!");
		return url;
	}
	
	
	private String getPayUrl(SaveOrderResponse orderObj, PayTypeEnum pType) throws IOException {
        try {
            CmccPayTypeEnum payType = CmccPayTypeEnum.ALIPAY;
            if (pType.getCode() == PayTypeEnum.WXSCAN.getCode() || pType.getCode() == PayTypeEnum.WXWAP.getCode()) {
                payType = CmccPayTypeEnum.WECHAT;
            }
            
            String payUrl = null;
            
            //如果是微信WAP
            if(pType.getCode() == PayTypeEnum.WXWAP.getCode()) {
            	payUrl = getPayUrlOpenWx(orderObj, payType);
            }else {
            	payUrl = getAlipayUrl(orderObj, payType);
            }
           return payUrl;
        } catch (RuntimeException ex) {
        	ex.printStackTrace();
            throw (ex);
        }
    }
	
	private String getAlipayUrl(SaveOrderResponse order,CmccPayTypeEnum payType) throws IOException  {
		Map<String, String> map = new HashMap<String, String>();
        map.put("bankAbbr", payType.getName());
        map.put("orderId", order.getOrderId());
        map.put("type", "C");
        map.put("ts", order.getTs());
		map.put("hmac", order.getHmac());
        map.put("channelId", order.getChannelId());
        String url = "https://pay.shop.10086.cn/paygw/mobileAndBankPayH5";
        
        String html =  oKHttpUtil
        		.header("Referer",order.getPayUrl())
        		.header("Origin","https://pay.shop.10086.cn")
        		.header("Host", "pay.shop.10086.cn")
        		.header("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 14_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/14.0.1 Mobile/15E148 Safari/604.1")
        		.post(url, map).body().string();
        
    	System.out.println("HTML内容:"+html);
  
    	html = oKHttpUtil
    	.header("Content-Type", "application/x-www-form-urlencoded")
    	.header("Host", "pay.it.10086.cn")
    	.header("Origin", "https://pay.shop.10086.cn")
    	.header("Referer", "https://pay.shop.10086.cn/paygw/mobileAndBankPayH5")
    	.header("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1")
    	.post("https://pay.it.10086.cn/payprod-format/h5/dup_submit", getPayPageParam(html)).body().string();
    	
    	System.out.println("\n\nHTML::"+html);

		Pattern ptn = Pattern.compile("window.location.href = \"(.*?)\"");
		Matcher m = ptn.matcher(html);
		if(m.find()) {
			return m.group(1);
		}
		return null;
	}
	
	 
	 String get302Url(String url) {
		 System.out.println("url:"+url);
		String newuri = "";
		try {
//			oKHttpUtil.clientBuilder().followRedirects(false).build();
	        Response resp = oKHttpUtil.get(url);
	        return resp.networkResponse().request().url().toString();
//	        int code = resp.code();
//	        if (code == 302) {
//	        	newuri = resp.header("location"); // 跳转的目标地址是在 HTTP-HEAD 中的
//	        }else {
//	        	System.out.println(resp.body().string());
//	        }
        }catch(Exception ex) {
        	ex.printStackTrace();
        }
		return newuri;
	}
	
	 
	 String get302Url(String url,Map<String,String> param) {
		String newuri = "";
		try {
	        Response resp = oKHttpUtil.post(url, param);
	        int code = resp.code();
	        if (code == 302) {
	        	newuri= resp.header("location"); // 跳转的目标地址是在 HTTP-HEAD 中的
	        }else {
	        	System.out.println(resp.body().string());
	        }
        }catch(Exception ex) {
        	ex.printStackTrace();
        }
		return newuri;
	}
	 
	 private Map<String,String> getPayPageParam(String html) {
		 
		Pattern aliPayPattern = Pattern.compile("\"hidden\" name=\"(.*?)\"(.*?) value=\"(.*?)\"");
		System.out.println("HTML内容:"+html);
		Matcher matcher = aliPayPattern.matcher(html);
		Map<String,String> param = new HashMap<>();
		while(matcher.find()) {
			param.put(matcher.group(1), HtmlUtils.htmlUnescape(matcher.group(3)));
		}
		System.out.println("参数:"+Json.toJSON(param));
		return param;
	 }
	 
	private String getPayUrlOpenWx(SaveOrderResponse order, CmccPayTypeEnum payType) throws IOException{
        Map<String, String> map = new HashMap<String, String>();
        map.put("bankAbbr", payType.getName());
        map.put("orderId", order.getOrderId());
        map.put("type", "C");
        map.put("ts", order.getTs());
		map.put("hmac", order.getHmac());
        map.put("channelId", order.getChannelId());
        String url = "https://pay.shop.10086.cn/paygw/mobileAndBankPayH5";
        
        String wxurl = null;
        String html =  oKHttpUtil
        		.header("Referer",order.getPayUrl())
        		.header("Origin","https://pay.shop.10086.cn")
        		.header("Host", "pay.shop.10086.cn")
        		.header("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 14_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/14.0.1 Mobile/15E148 Safari/604.1")
        		.post(url, map).body().string();
        
    	System.out.println("HTML内容:"+html);
  
    	html = oKHttpUtil
    	.header("Content-Type", "application/x-www-form-urlencoded")
    	.header("Host", "pay.it.10086.cn")
    	.header("Origin", "https://pay.shop.10086.cn")
    	.header("Referer", "https://pay.shop.10086.cn/paygw/mobileAndBankPayH5")
    	.header("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1")
    	.post("https://pay.it.10086.cn/payprod-format/h5/dup_submit", getPayPageParam(html)).body().string();
    	
    	System.out.println("\n\nHTML::"+html);

		Pattern ptn = Pattern.compile("action=\"(.*?)\"");
		Matcher m = ptn.matcher(html);
		if(m.find()) {
			wxurl = m.group(1);
		}
		
		return getWxUrl(wxurl);
    }
    
    private String getWxUrl(String url) throws IOException {
    	String html = oKHttpUtil
		.header("Referer", "https://pay.shop.10086.cn")
		.header("Upgrade-Insecure-Requests", "1")
		.header("Host", "wx.tenpay.com")
		.header("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
		.header("Host", "wx.tenpay.com")
		.header("Accept-Language", "en,zh-CN;q=0.8,zh;q=0.6,en-US;q=0.4")
		.get(url).body().string();

		if (StringUtils.isBlank(html)) {
			return null;
		}
		// 正则匹配
		Pattern pattern = Pattern.compile("var url=\"(.*?)\"");
		Matcher matcher = pattern.matcher(html);
		boolean isMatch = matcher.find();
		String openWxUrl = null;
		if (isMatch) {
			openWxUrl = matcher.group(1);
			if(StringUtils.isBlank(openWxUrl)) {
				return null;
			}
			if(openWxUrl.startsWith("weixin:")) {
				return openWxUrl;
			}
		}
		return null;
    }
	
	//{"data":null,"retCode":"511012","retMsg":"用户状态异常","sOperTime":null}
	//{"data":null,"retCode":"511014","retMsg":"请先进行实名制认证，再来充值~","sOperTime":null}
	
	private NumAreaResponse getNumArea(String phone) throws IOException {
        String url = String.format("https://shop.10086.cn/i/v1/res/numarea/%s", phone);
        String json  = oKHttpUtil.header("Referer", "https://touch.10086.cn/i/mobile/rechargecredit.html?welcome=1605531950697").get(url).body().string();
    	TypeReference<CmccResult<NumAreaResponse>> type = new TypeReference<CmccResult<NumAreaResponse>>() {}; // 构造TypeReference的匿名内部类对象，直接获取Type对象
        CmccResult<NumAreaResponse> rst = Json.readValue(json, type);
        if(!rst.isSuccess()) {
        	throw new RuntimeException("手机号:[" + phone + "]归属地查询失败!"+ rst.getRetMsg());
        }
        return rst.getData();
    }

    /**
     * 获取优惠规则
     *
     * @param phone
     * @param area
     * @param config
     * @return
     * @throws IOException 
     */
    private PayRuleResponse getPayRule(String phone, NumAreaResponse area) throws IOException {
        String url = String.format("https://shop.10086.cn/i/v1/pay/payrule/%s", phone);
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("amountFlag", "1");
        param.put("channel", "00");
        param.put("enableFlag", "1");
        param.put("enableType", "0");
        param.put("phoneNo", Base64.encodeBase64String(phone.getBytes("UTF-8")));
        param.put("provCode", area.getProvCd());
        param.put("saleFlag", "1");
        param.put("saleQueryType", "2");
        String json = oKHttpUtil.post(url,Json.toJSON(param)).body().string();
//        System.out.println(json+" -- payRule");
        CmccResult<PayRuleResponse> rRst = Json.readValue(json, new TypeReference<CmccResult<PayRuleResponse>>() {});
        if(rRst == null || !rRst.isSuccess()) {
        	throw new RuntimeException("手机号:[" + phone + "]支付规则查询失败!"+rRst.getRetMsg());
        }
        return rRst.getData();
    }
	
	public String checkLogin(String phone,String pwd) throws IOException {
		Map<String,String> params = Maps.newHashMap();
		params.put("accountType", "01");
		params.put("pwdType", "02");
		params.put("account", phone);
		params.put("password", pwd);
		params.put("backUrl", "https://touch.10086.cn/i/");
		params.put("rememberMe", "0");
		params.put("channelID", "12014");
		params.put("loginMode", "03");
		params.put("protocol", "https:");
		params.put("timestamp", System.currentTimeMillis()+"");

		String url = "https://login.10086.cn/login.htm";
		String json = oKHttpUtil.get(url,params).body().string();
		System.out.println("Login : "+json);
		return json;
	}	
	
	public OrderPayResult payCallBack(String order) {
		OrderPayResult rst = new OrderPayResult();
    	SaveOrderResponse orderObj = Json.parse(order, SaveOrderResponse.class);
    	String url = String.format("https://pay.shop.10086.cn/paygw/WXafterpayeBack?orderId=%s", orderObj.getOrderId());
    	String content="";
        try {
            content = oKHttpUtil.get(url).body().string();
            //不为空,就记录日志
            if(StringUtils.isNotBlank(content)) {
            	rst.setStatus(OrderPayResultStatusEnum.SUCCESS);
            	rst.setMsg(content);
            }
            return rst;
        } catch (Exception ex) {
        	rst.setStatus(OrderPayResultStatusEnum.UNKNOW);
        	ex.printStackTrace();
        }
        return rst;
    }
	
	public static void main(String[] args) throws Exception  {
		
		Touch10086Wrapper cmcc  = new Touch10086Wrapper();

		//初始化cookie
		cmcc.initCookie();
		
		//访问下充值首页r
		//http://touch.10086.cn/i/mobile/rechargecredit.html
//		oKHttpUtil.get("http://touch.10086.cn/i/mobile/rechargecredit.html");

		//扫描结果
		//cmcc.checkChargeSuccess(config);
		
		ChargeRequest request  = new ChargeRequest();
		request.setMobile("13990691004");
		request.setMobileType(MobileTypeEnum.CMCC);
		request.setMoney(10.0);
		request.setPayType(PayTypeEnum.WXWAP);
		
		ChargeOrder chargeOrder = null;
		chargeOrder = cmcc.createOrderOnly(request);
//		cmcc.refreshCookie();
		
		//刷新cookie
//		chargeOrder.setOrderJson("{\"orderId\":\"498320258015166094\",\"serialNo\":\"20201117141738739752345800145477\",\"payTime\":\"20201117141738\",\"amount\":\"9.98\",\"chargeMoney\":\"10\",\"payUrl\":\"https://pay.shop.10086.cn/paygw/498320258015166094-1605593858634-511375341acf0b6b0fcdb3d2f4d377e2-20.html\",\"payWay\":\"WAP\"}");
		System.out.println("\n\n订单:\n"+Json.toJSON(chargeOrder)+"\n");
		
		String payUrl = cmcc.getPayUrl(request, chargeOrder.getOrderJson());

		System.out.println("\n\n支付地址:"+payUrl+"\n\n");
		
		if(StringUtils.isBlank(payUrl)) {
			return;
		}
		
		while(true) {
			try {
				OrderPayResult prst = cmcc.payCallBack(chargeOrder.getOrderJson());
				System.out.println(Json.toJSON(prst));
				if(prst!= null&& prst.getStatus()!= null && prst.getStatus().getCode() == OrderPayResultStatusEnum.SUCCESS.getCode()) {
					System.out.println("支付成功了!!!!");
					break;
				}
			}catch(Exception ex) {
				break;
			}
			Thread.sleep(30000);
		}
	}
	
	void checkChargeSuccess() throws IOException {
		//status  1 未支付 ; 2 支付成功;3 支付失败;4 充值成功; 5 充值失败;
		String url ="https://shop.10086.cn/i/v1/cust/orderlistqry/15001300920?loginNo=15001300920&orderType=004&status=6&startTime=202003&endTime=202003&currentPage=1&channelId=00&pageSize=5";
		String html = oKHttpUtil.get(url).body().string();
		System.out.println(html);
	}
}
