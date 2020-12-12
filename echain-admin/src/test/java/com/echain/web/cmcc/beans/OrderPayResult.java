package com.echain.web.cmcc.beans;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderPayResult {
	
	public OrderPayResult() {

	}
	
	public OrderPayResult(OrderPayResultStatusEnum status) {
		this.status = status;
	}
	
	private OrderPayResultStatusEnum status;
	private String msg;
	private String memo;
}
