package com.echain.service.cmcc;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetPhoneResponse extends BaseResponse{
	//订单号
	@JsonAlias("orderid")
	private String orderId;
	
	//手机号
	private String phone;
}
