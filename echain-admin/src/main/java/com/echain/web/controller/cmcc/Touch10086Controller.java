package com.echain.web.controller.cmcc;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.echain.common.beans.JsonResult;
import com.echain.common.utils.Json;
import com.echain.service.cmcc.CookieResponse;
import com.echain.web.controller.AbsSupperController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/api/10086/", method = { RequestMethod.POST, RequestMethod.GET })
public class Touch10086Controller extends AbsSupperController {
	
//	@Autowired
//	private RedisUtil redisUtil;
	
	@Autowired
	private Touch10086LoginWrapper touch10086LoginWrapper;
	
//	private static ExecutorService executorService = Executors.newFixedThreadPool(50);
	
	@PostMapping("getCookie")
	public JsonResult getCookie(String callBackUrl) {
		if (StringUtils.isBlank(callBackUrl)) {
			return error("参数错误,请勿外站提交!");
		}
		touch10086LoginWrapper.login(callBackUrl);
		return JsonResult.OK("后台创建cookie中,请稍等.....");
	}
	
	@RequestMapping(value = "/getCookieResult", method = RequestMethod.POST)
	public JsonResult getCookieResult(@RequestBody Map<String,String> map, HttpServletRequest request) throws Exception {
		String jsonStr = map.get("content");
		log.info("content:"+jsonStr);
		CookieResponse cookieRes = Json.parse(jsonStr, CookieResponse.class);
		return JsonResult.OK(cookieRes);
	}
	
//	@RequestMapping(value = "/get10086Cookie", method = RequestMethod.GET)
//	public JsonResult get10086Cookie(String callBackUrl) throws Exception {
//		if (StringUtils.isBlank(callBackUrl)) {
//			return error("参数错误,请勿外站提交!");
//		}
//		//获取可用的手机号
//		/*GetPhoneResponse phoneRes = LovshsbApiWrapper.getPhone();
//		if(phoneRes == null || !phoneRes.isSuccess()) {
//			JsonResult.Error("没有取到手机号!");
//		}
//		String phone = phoneRes.getPhone();*/
//		String phone = "17600937279";
//		
//		//判断该手机号码当前是否存在cookie，有点话直接返回
//		if(redisUtil.get("cookie-"+phone) != null &&StringUtils.isNotBlank(redisUtil.get("cookie-"+phone))){
//			log.info(phone+"存在有效的cookie!");
//			CookieResponse cookieRes = new CookieResponse();
//			cookieRes.setPhone(phone);
//			cookieRes.setCookie(redisUtil.get("cookie-"+phone) == null?"":redisUtil.get("cookie-"+phone));
//			return JsonResult.OK(Json.toJSON(cookieRes));
//		}
//		//判断该手机号码是否在30天内已使用过
//		if(redisUtil.get("phone-"+phone) != null && StringUtils.isNotBlank(redisUtil.get("phone-"+phone))){
//			log.info(phone+"30天内已使用过了!");
//			return JsonResult.Error(phone+"30天内已使用过了!");
//		}
//		
//		Touch10086LoginWrapper touch = new Touch10086LoginWrapper();
//		
//		//登录 并写cookie
//		JsonResult rs = touch.login(phone);
//		Long ts = System.currentTimeMillis();
//    	log.info("准备开启线程查询cookie:{} -- ts:{}", phone, ts);
//    	executorService.submit(()->{
//    		log.info("线程已经开启,正式查询cookie:{} -- ts:{}", phone, ts);
//    		long startTs = System.currentTimeMillis();
//    		try {
//    			if(rs.getCode()==JsonResultCode.SUCCESS) {
//    				try {
//    					CookieResponse cookieRes = Json.parse(rs.getResult().toString(), CookieResponse.class);
//    					Map<String,String> map = new HashMap<String, String>();
//    					map.put("phone", cookieRes.getPhone());
//    					map.put("CookieStr", cookieRes.getCookie());
//    					map.put("startTime", cookieRes.getStartTime().toString());
//    					
//    					//手机号码保存30天
//    					redisUtil.setEx("phone-"+phone, cookieRes.getCookie(), 30L, TimeUnit.DAYS);
//    					//cookie保存30分钟
//    					redisUtil.setEx("cookie-"+phone, cookieRes.getCookie(), 30L, TimeUnit.MINUTES);
//    					int count = redisUtil.get("CookieCount") == null || StringUtils.isBlank(redisUtil.get("CookieCount"))?0:(Integer.parseInt(redisUtil.get("CookieCount")) + 1);
//    					redisUtil.set("CookieCount", count+"");
//    					log.info("CookieCount="+redisUtil.get("CookieCount"));
//    					
//    					OKHttpUtil.newInstance().post(callBackUrl, map);
//    				} catch (Exception e) {
//    					e.printStackTrace();
//    				}
//    				executorService.shutdown();
//    			}else {
//    				JsonResult.Error(rs.getMsg());
//    			}
//    		}catch(Exception ex) {
//    			log.error("线程查询cookie发生异常 手机号:{} -- ts:{} " , phone ,ts,ex);
//    		}
//    		log.info("线程查询cookie结束 手机号:{} -- ts:{}  耗时:{}" , phone ,ts,(System.currentTimeMillis()-startTs)/1000.0);
//    	});
//		return JsonResult.OK();
//	}
	
}
