package com.echain.web.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Order(1)
@Slf4j
public class ControllerExceptionAspect {
	@Pointcut("execution(* com.echain.web.controller.*.*(..))")
	public void exceptionAspect() {
	};

	@AfterThrowing(pointcut = "exceptionAspect()", throwing = "e")
	public void doException(JoinPoint jp, Throwable e) {
		if (e != null) {
			log.error("抓到异常咯!!!  {}" ,e.getMessage(), e);
		}
	}
}
