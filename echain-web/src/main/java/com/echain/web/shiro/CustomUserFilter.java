package com.echain.web.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.authc.UserFilter;

import com.echain.common.beans.JsonResult;
import com.echain.common.utils.Json;
import com.echain.common.utils.RequestClientUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomUserFilter extends UserFilter{

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse res = (HttpServletResponse) response;
        //如果不是ajax请求 ,那么直接返回true
        if(!RequestClientUtil.isAjaxRequest((HttpServletRequest)request)) {
        	return super.onAccessDenied(request, response);
        }
        try {
        	res.setStatus(200);
        	res.setContentType("text/html;charset=utf-8");
        	res.getWriter().write(Json.toJSON(JsonResult.Error(1001,"请先登录")));
        	res.getWriter().close();
		}catch(Exception ex) {
			log.error("返回JSON异常!",ex);
		}
        return false;
	}
}
