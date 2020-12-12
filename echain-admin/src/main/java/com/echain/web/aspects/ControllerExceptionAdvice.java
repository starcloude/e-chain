package com.echain.web.aspects;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.echain.common.beans.JsonResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ControllerExceptionAdvice {

	@ResponseBody
	@ExceptionHandler(Throwable.class)
	public JsonResult handleException(Throwable e) {
		log.error("发生异常 handleException !",e);
		return JsonResult.Error(e.getMessage());
	}
	
	@ResponseBody
	@ExceptionHandler({UnauthorizedException.class, AuthorizationException.class})
	public JsonResult handleUnauthorizedException(Throwable e) {
		log.error("发生异常 UnauthorizedException !",e);
		return JsonResult.Error("你没有权限,请联系管理员!");
	}
	
	
}
