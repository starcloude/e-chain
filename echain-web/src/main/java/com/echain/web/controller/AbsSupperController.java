package com.echain.web.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.echain.common.beans.JsonResult;
import com.echain.common.beans.PageJsonResult;
import com.echain.domain.business.user.User;
import com.echain.service.log.OperateLogger;
import com.echain.web.shiro.ShiroSession;
import com.google.common.collect.Maps;

public abstract class AbsSupperController {

	@Resource
	protected OperateLogger operateLoger;

	/**
	 * 获取登录用户信息
	 * 
	 * @return
	 */
	protected User getLoginUser() {
		return ShiroSession.get("user", User.class);
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
	/**
	 * 图片后缀
	 */
	protected static final Map<String, String> imgMediaType;

	static {
		imgMediaType = Maps.newHashMap();
		imgMediaType.put("jpg", "image/jpeg");
		imgMediaType.put("jpeg", "image/jpeg");
		imgMediaType.put("gif", "image/gif");
		imgMediaType.put("png", "image/png");
		imgMediaType.put("bmp", "image/bmp");
	}

	/**
	 * 是否是图片
	 * 
	 * @param fileName
	 * @return
	 */
	protected boolean isImage(String fileName) {
		String fileExt = getFileExt(fileName);
		if (StringUtils.isNotBlank(fileExt)) {
			return imgMediaType.keySet().contains(fileExt.toLowerCase());
		}
		return false;
	}

	/**
	 * 获取文件后缀
	 * 
	 * @param fileName
	 * @return
	 */
	protected String getFileExt(String fileName) {
		int nIndex = fileName.lastIndexOf(".");
		if (nIndex <= 0) {
			return null;
		}
		return fileName.substring(nIndex + 1);
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
