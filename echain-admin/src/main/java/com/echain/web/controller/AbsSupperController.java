package com.echain.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.echain.common.beans.JsonResult;
import com.echain.common.beans.PageJsonResult;
import com.echain.domain.sys.SysUser;
import com.echain.service.log.OperateLogger;
import com.echain.web.autoconfig.DateConverter;
import com.echain.web.shiro.ShiroSession;

public abstract class AbsSupperController {

	@Resource
	protected OperateLogger operateLoger;

	/**
	 * 获取登录用户信息
	 * 
	 * @return
	 */
	protected SysUser getLoginUser() {
		return ShiroSession.get("user", SysUser.class);
	}

	/**
	 * 设置session
	 * 
	 * @param key
	 * @param value
	 */
	protected void setSession(String key, Object value) {
		ShiroSession.set(key, value);
	}

	@InitBinder
	protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
		GenericConversionService genericConversionService = (GenericConversionService) binder.getConversionService();
        if (genericConversionService != null) {
            genericConversionService.addConverter(new DateConverter());
        }
	}
	
	/**
	 * ipage to PageJsonResult
	 * @param page
	 * @return
	 */
	protected PageJsonResult toPageJson(IPage<?> page) {
		PageJsonResult result = new PageJsonResult(true);
		result.setCurrent(page.getCurrent());
		result.setSize(page.getSize());
		result.setTotal(page.getTotal());
		result.setResult(page.getRecords());
		return result;
	}
	
	/**
	 * 返回成功
	 * @param data
	 * @return
	 */
	protected JsonResult ok(Object data) {
		return JsonResult.OK(data);
	}
	
	/**
	 * 返回成功
	 * @return
	 */
	protected JsonResult ok() {
		return ok(null);
	}
	
	/**
	 * 失败
	 * @param msg
	 * @return
	 */
	protected JsonResult error(String msg) {
		return JsonResult.Error(msg);
	}
	
	/**
	 * 失败
	 * @param code
	 * @param msg
	 * @return
	 */
	protected JsonResult error(int code,String msg) {
		return JsonResult.Error(code, msg);
	}
}
