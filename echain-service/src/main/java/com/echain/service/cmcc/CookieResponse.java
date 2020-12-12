package com.echain.service.cmcc;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CookieResponse{
	
	//手机号
	private String phone;
	
	//cookie
	private String cookie;
	
	//创建时间
	private Long startTime;
	
	//订单号
	private String orderId;
	
}
