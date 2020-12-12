package com.echain.web.cmcc.beans;

public class ChargeRequest {
	/**
	 * 手机号
	 */
	private String mobile;
	
	/**
	 * 充值金额
	 */
	private Double money;
	
	/**
	 * 手机类型
	 */
	private MobileTypeEnum mobileType;
	
	/**
	 * 归属地
	 */
	private String cityCode;

	/**
	 * 支付方式
	 */
	private PayTypeEnum payType;
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public MobileTypeEnum getMobileType() {
		return mobileType;
	}

	public void setMobileType(MobileTypeEnum mobileType) {
		this.mobileType = mobileType;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public PayTypeEnum getPayType() {
		return payType;
	}

	public void setPayType(PayTypeEnum payType) {
		this.payType = payType;
	}
}
