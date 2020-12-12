package com.echain.web.controller;

import java.awt.Font;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.echain.common.utils.Constant;
import com.echain.service.common.RedisUtil;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.utils.CaptchaUtil;

@Controller
@RequestMapping(value = "/captcha")
public class CaptchaController {
	
	@Resource
	private RedisUtil redisUtil;
	
	@Value("${captcha.time.out:60}")
	private int captchaTimeOut;
	
	@RequestMapping(value = "/index",method = RequestMethod.GET)
	public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String r = request.getParameter("r");
		if(StringUtils.isBlank(r)) {
			return;
		}
		
		if(r.length()>10) {
			r = r.substring(0, 9);
		}
		
		 // 设置请求头为输出图片类型
        CaptchaUtil.setHeader(response);
        
        // 三个参数分别为宽、高、位数
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 5);
        
        // 设置字体
        specCaptcha.setFont(new Font("Verdana", Font.PLAIN, 32));  // 有默认字体，可以不用设置
        
        // 设置类型，纯数字、纯字母、字母数字混合
        specCaptcha.setCharType(com.wf.captcha.base.Captcha.TYPE_ONLY_NUMBER);
        
        // 生成的验证码
        String code = specCaptcha.text();
        
        //验证码存session
        redisUtil.setEx(Constant.getCaptchaCodeKey(r, code), code, captchaTimeOut, TimeUnit.SECONDS);
        
        // 输出图片流
        specCaptcha.out(response.getOutputStream());
	}
}
