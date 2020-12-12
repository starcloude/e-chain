package com.echain.service.cmcc;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse {

	private Integer status;
	
	private String msg;
	
	public boolean isSuccess() {
		return Integer.valueOf(0).equals(status);
	}
}
