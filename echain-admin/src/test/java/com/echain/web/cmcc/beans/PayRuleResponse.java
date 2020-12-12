package com.echain.web.cmcc.beans;

class UsableRule{
	private Integer usable;

	public Integer getUsable() {
		return usable;
	}

	public void setUsable(Integer usable) {
		this.usable = usable;
	}
}

public class PayRuleResponse {

	/**
	 * {"data":{"amountRule":{"maxAmount":3000.0,"minAmount":10.0},"saleRules":[{"operateId":1552,"saleType":1,"saleCode":"0.998","saleDesc":"充值任意金额均可享受9.98折优惠！","presentType":"01","saleName":"充值9.98折","discountFormat":"3","isFixed":"1","activityType":"1","activityArea":"1","activityTotal":null,"remainder":null,"usable":"0","targetNoType":"0","tipInfo":"xxx;9.98折"}],"usableRule":{"usable":"0","tips":""}},"retCode":"000000","retMsg":"交费规则查询成功","sOperTime":null}
	 */
	private SaleRule[] saleRules;
	
	private	UsableRule usableRule;

	public SaleRule[] getSaleRules() {
		return saleRules;
	}

	public void setSaleRules(SaleRule[] saleRules) {
		this.saleRules = saleRules;
	}

	public UsableRule getUsableRule() {
		return usableRule;
	}

	public void setUsableRule(UsableRule usableRule) {
		this.usableRule = usableRule;
	}
	
	public Double getDiscount(SaleRule rule) {
		Double defaultValue = Double.valueOf(1);
		if(rule == null) {
			return defaultValue;
		}
		return rule.getSaleCode();
	}
	
	/**
	 * 获取使用规则
	 * @return
	 */
	public SaleRule getRule() {
		//没有规则
		if(saleRules == null || saleRules.length==0) {
			return null;
		}
		SaleRule defaultValue = saleRules[0];
		//使用规则== null
		if(usableRule == null || usableRule.getUsable()!= null || usableRule.getUsable().intValue()<0) {
			return defaultValue;
		}
		//超出了
		if(usableRule.getUsable() >= saleRules.length) {
			return defaultValue;
		}
		return saleRules[usableRule.getUsable()];
	}
	
	public SaleRule getLastRule() {
		//没有规则
		if(saleRules == null || saleRules.length==0) {
			return null;
		}
		return saleRules[saleRules.length-1];
	}
}
