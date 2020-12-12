package com.echain.common.beans;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JsonResult{

	public JsonResult() {
		this(false);
	}

	public JsonResult(boolean bSuccess, String sMsg, int nCode, Object oResult) {
		this.success = bSuccess;
		this.msg = sMsg;
		this.code = nCode;
		this.result = oResult;
	}

	public JsonResult(boolean bSuccess, String sMsg, Object oResult) {
		this(bSuccess, sMsg, JsonResultCode.SUCCESS, oResult);
	}

	public JsonResult(boolean bSuccess, String sMsg) {
		this(bSuccess, sMsg, null);
	}

	public JsonResult(boolean bSuccess) {
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
	private Object result;


	public static JsonResult Error(String sMsg) {
		return Error(sMsg, null);
	}
	
	public static JsonResult Error(int nCode,String sMsg) {
		return Error(nCode,sMsg, null);
	}
	
	public static JsonResult Error(String sMsg, Object oResult) {
		return Error(JsonResultCode.SYSTEM_ERROR, sMsg, oResult);
	}

	public static JsonResult Error(int nCode, String sMsg, Object oResult) {
		return new JsonResult(false, sMsg, nCode, oResult);
	}

	public static JsonResult OK() {
		return OK(null);
	}

	public static JsonResult OK(Object oResult) {
		return OK(oResult, JsonResultCode.SUCCESS);
	}

	public static JsonResult OK(Object oResult, int nCode) {
		return new JsonResult(true, "", nCode, oResult);
	}
}
