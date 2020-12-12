package com.echain.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.echain.common.beans.JsonResult;

@RestController
@RequestMapping(value = "/", method = { RequestMethod.POST, RequestMethod.GET })
public class ErrorController extends AbsSupperController{
	
	@RequestMapping(value ={ "/400","/403" ,"/404","/405","/500"})
	public JsonResult errorPage(HttpServletRequest request,HttpServletResponse response){
		String requestUri =request.getRequestURI(); 
		String msg="";
		if ("/400".equals(requestUri)) {
			msg="提交的参数参数无法识别,请联系管理员!";
		}
		else if ("/403".equals(requestUri)) {
			msg="您没有此权限,请联系管理员!";
		}
		else if ("/404".equals(requestUri)) {
			msg="页面不存在!";
		}
		else if ("/405".equals(requestUri)) {
			msg="METHOD_NOT_ALLOWED! 方法禁用，禁用请求中指定的方法";
		}
		else if ("/500".equals(requestUri)) {
			msg="Internal Server Error! 服务器内部错误，服务器遇到错误，无法完成请求";
		}
		response.setCharacterEncoding("UTF-8");
//		response.setStatus(200);
		return error(msg);
	}
}
