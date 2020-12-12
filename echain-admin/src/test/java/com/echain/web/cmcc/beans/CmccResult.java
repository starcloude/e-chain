package com.echain.web.cmcc.beans;

public class CmccResult<T> {
	
	private String retCode;
	private String retMsg;
	private T data;
	
	public String getRetCode() {
		return retCode;
	}
	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}
	public String getRetMsg() {
		return retMsg;
	}
	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	public boolean isSuccess() {
		return "000000".equals(getRetCode());
	}
}
