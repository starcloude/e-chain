package com.echain.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import com.echain.common.beans.JsonResult;
import com.echain.common.enums.TurnEnum;
import com.echain.common.utils.Constant;
import com.echain.common.utils.Json;
import com.echain.service.common.RedisCache;

@Order(value = 1)
@WebFilter(filterName = "webSiteTurnFilter", urlPatterns = { "/api/*" })
public class WebSiteTurnFilter implements Filter {

	@Autowired
	private RedisCache redisCache;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res= (HttpServletResponse) response;
//		System.out.println("webSiteTurnFilter," + req.getRequestURI());
		
		String uri = req.getRequestURI();
		if(uri.indexOf("/api/_debug")>=0) {
			// 执行
			chain.doFilter(req, res);
			return;
		}
		
		String turn = redisCache.get(Constant.getWebSiteTurn(), TurnEnum.ON.getCode());
		if(TurnEnum.OFF.getCode().equals(turn)) {
			try {
				res .setStatus(200);
				res.setContentType("text/html;charset=utf-8");
				res.getWriter().write(Json.toJSON(JsonResult.Error("系统升级中,请稍后再试....")));
				res.getWriter().close();
				return;
			}catch(Exception ex) {
				return;
			}
		}
		chain.doFilter(req, res);
	}
}
