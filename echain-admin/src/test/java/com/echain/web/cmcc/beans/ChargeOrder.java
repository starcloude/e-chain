package com.echain.web.cmcc.beans;

public class ChargeOrder {

	/**
	 * 订单号
	 */
	private String orderId;

	/**
	 * 订单原生数据
	 */
	private String orderJson;

	/**
	 * 订单页面
	 */
	private String orderPageUrl;
	
	/**
	 * 支付页面
	 */
	private String payUrl;
	
	/**
	 * 归属地
	 */
	private String cityCode;
	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderJson() {
		return orderJson;
	}

	public void setOrderJson(String orderJson) {
		this.orderJson = orderJson;
	}

	public String getOrderPageUrl() {
		return orderPageUrl;
	}

	public void setOrderPageUrl(String orderPageUrl) {
		this.orderPageUrl = orderPageUrl;
	}

	public String getPayUrl() {
		return payUrl;
	}

	public void setPayUrl(String payUrl) {
		this.payUrl = payUrl;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
}
