package com.echain.web.aspects;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.echain.common.utils.Json;

import lombok.extern.slf4j.Slf4j;

//@Aspect
//@Component
//@Order(1)
@Slf4j
public class ControllerLogAspect {

	@Pointcut("execution(* com.echain.web.controller.*.*(..))")
	private void controllerAspect() {
	}

	@Before(value = "controllerAspect()")
	public void methodBefore(JoinPoint joinPoint) {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();

		// 打印请求内容
		log.info("===============请求内容===============");
		log.info("请求地址: {}",request.getRequestURL().toString());
		log.info("请求方式: {}" ,request.getMethod());
		log.info("请求类方法: {}" ,joinPoint.getSignature());
		log.info("请求类方法参数: {}" , Arrays.toString(joinPoint.getArgs()));
		log.info("===============请求内容===============");
	}

	/**
	 *  在方法执行完结后打印返回内容
	 * @param o
	 */
	@AfterReturning(returning = "o", pointcut = "controllerAspect()")
	public void methodAfterReturing(Object o) {
		if(o == null) {
			return;
		}
		log.info("--------------返回内容----------------");
		log.info("Response内容: {}" , Json.toJSON(o));
		log.info("--------------返回内容----------------");
	}
}
