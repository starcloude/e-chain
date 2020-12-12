package com.echain.web.aspects;

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
	
}
