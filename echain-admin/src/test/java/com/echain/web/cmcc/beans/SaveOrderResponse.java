package com.echain.web.cmcc.beans;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

/**
 * 创建订单成功,返回
 * @author 作者B
 *
 */
public class SaveOrderResponse {
	
	/**
	 * 
	 * {"data":{"orderId":"440786870168196321","serialNo":"20190121164750556363154811986445","payTime":"2019/01/21 16:47:50","amount":"99.8","chargeMoney":"100","payUrl":"https://pay.shop.10086.cn/paygw/440786870168196321-1548060470771-4db895ecdedb3910a18d168c6d504ad7-20.html","payWay":"WWW","accountType":null,"associatedNum":null},"retCode":"000000","retMsg":"保存订单成功","sOperTime":null}
	 * 
	 */
	//订单号
	private String orderId;
	//序列号
	private String serialNo;
	//支付时间
	private Date payTime;
	//实际支金额
	private String amount;
	//充值金额
	private String chargeMoney;
	
	//支付地址
	private String payUrl;
	
	//支付方式
	private String payWay;
	
	
	private String ts;
	
	private String hmac;
	
	private String channelId;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getChargeMoney() {
		return chargeMoney;
	}

	public void setChargeMoney(String chargeMoney) {
		this.chargeMoney = chargeMoney;
	}

	public String getPayUrl() {
		return payUrl;
	}

	public void setPayUrl(String payUrl) {
		this.payUrl = payUrl;
	}

	public String getPayWay() {
		return payWay;
	}

	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}
	
	private void saxPayUrl() {
		if(StringUtils.isBlank(payUrl)) {
			return;
		}
		String pay = payUrl.substring(payUrl.lastIndexOf("/")+1,payUrl.indexOf(".htm"));
		String[] tmp = pay.split("-");
		if(tmp.length==4) {
			this.ts = tmp[1];
			this.hmac = tmp[2];
			this.channelId = tmp[3];
		}
	}

	public String getTs() {
		if(ts == null) {
			saxPayUrl();
		}
		return ts;
	}

	public String getHmac() {
		if(hmac == null) {
			saxPayUrl();
		}
		return hmac;
	}

	public String getChannelId() {
		if(hmac == null) {
			saxPayUrl();
		}
		return channelId;
	}
}
