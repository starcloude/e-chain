package com.echain.web.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.echain.common.beans.JsonResult;
import com.echain.service.common.RedisUtil;
import com.echain.web.controller.cmcc.JSEncryptUtil;
import com.echain.web.worker.UserAgentProfitWorker;
import com.echain.web.worker.UserMachineReturnWorker;
import com.echain.web.worker.UserMachineStopWorker;
import com.echain.web.worker.UserProfitWorker;

@RestController
@RequestMapping(value = "_debug/")
public class DebugController extends AbsSupperController {

	@Autowired
	private Environment env;  
	
	@Autowired
	protected RedisUtil redisUtil;
	
	@Autowired
	private UserMachineStopWorker userMachineStopWorker;
	
	@Autowired
	private UserAgentProfitWorker userAgentProfitWorker;
	
	@Autowired
	private UserProfitWorker userProfitWorker;
	
	@Autowired
	private UserMachineReturnWorker userMachineReturnWorker;
	
	@GetMapping("js/encrypt")
	public String jsEncrypt(String value) {
		if(StringUtils.isBlank(value)) {
			return null;
		}
		return JSEncryptUtil.encrypt(value);
	}
	
	@GetMapping("properties/get")
	public String getProperties(String key) {
		if(StringUtils.isBlank(key)) {
			return null;
		}
		return env.getProperty(key);
	}
	
	@GetMapping("redis/rpop")
	public String rpop(String key) {
		if(StringUtils.isBlank(key)) {
			return null;
		}
		return redisUtil.lRightPop(key);
	}
	
	@GetMapping("worker/userMachineStop")
	public JsonResult userMachineStop() {
		long ts = System.currentTimeMillis();
		userMachineStopWorker.worker();
		return ok(System.currentTimeMillis() - ts);
	}
	
	@GetMapping("worker/userAgentProfit")
	public JsonResult userAgentProfit() {
		long ts = System.currentTimeMillis();
		userAgentProfitWorker.worker();
		return ok(System.currentTimeMillis() - ts);
	}
	
	@GetMapping("worker/userProfit")
	public JsonResult userProfit() {
		long ts = System.currentTimeMillis();
		userProfitWorker.worker();
		return ok(System.currentTimeMillis() - ts);
	}
	
	@GetMapping("worker/userMachineReturn")
	public JsonResult userMachineReturn() {
		long ts = System.currentTimeMillis();
		userMachineReturnWorker.worker();
		return ok(System.currentTimeMillis() - ts);
	}
	
	
}
