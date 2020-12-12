package com.echain.common.beans;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MyJsonResult<T>{

	public MyJsonResult() {
		this(false);
	}

	public <S extends T> MyJsonResult(boolean bSuccess, String sMsg, int nCode, T oResult) {
		this.success = bSuccess;
		this.msg = sMsg;
		this.code = nCode;
		this.result = oResult;
	}

	public <S extends T> MyJsonResult(boolean bSuccess, String sMsg, T oResult) {
		this(bSuccess, sMsg, JsonResultCode.SUCCESS, oResult);
	}

	public <S extends T> MyJsonResult(boolean bSuccess, String sMsg) {
		this(bSuccess, sMsg, null);
	}

	public <S extends T> MyJsonResult(boolean bSuccess) {
		this(bSuccess, "");
	}

	/**
	 * 成功标记
	 */
	private boolean success;

	/**
	 * 返回结果
	 */
	private String msg;

	/**
	 * 返回Code
	 */
	private int code;

	/**
	 * 返回结果
	 */
	private T result;


	public static <T> MyJsonResult<T> Error(String sMsg) {
		return Error(sMsg, null);
	}
	
	public static <T> MyJsonResult<T> Error(int nCode,String sMsg) {
		return Error(nCode,sMsg, null);
	}
	
	public static <T> MyJsonResult<T> Error(String sMsg, T oResult) {
		return Error(JsonResultCode.SYSTEM_ERROR, sMsg, oResult);
	}

	public static <T> MyJsonResult<T> Error(int nCode, String sMsg, T oResult) {
		return new MyJsonResult<T>(false, sMsg, nCode, oResult);
	}

	public static <T> MyJsonResult<T> OK() {
		return OK(null);
	}

	public static <T> MyJsonResult<T> OK(T oResult) {
		return OK(oResult, JsonResultCode.SUCCESS);
	}

	public static <T> MyJsonResult<T> OK(T oResult, int nCode) {
		return new MyJsonResult<T>(true, "", nCode, oResult);
	}
}
