package com.echain.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.echain.common.beans.UserContext;
import com.echain.common.enums.ClientTypeEnum;
import com.echain.common.utils.RequestClientUtil;
import com.echain.common.utils.UserContextHolder;

public class ClientTypeInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		UserContext userContext = UserContextHolder.getUserContext();
		if (userContext == null) {
			userContext = new UserContext();
		}
		userContext.setIp(RequestClientUtil.getRemoteIP(request));
		userContext.setClientType(
				RequestClientUtil.isMoblie(request) ? ClientTypeEnum.M.getCode() : ClientTypeEnum.PC.getCode());
		UserContextHolder.setUserContexct(userContext);
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		UserContextHolder.remove();
		super.postHandle(request, response, handler, modelAndView);
	}

}
