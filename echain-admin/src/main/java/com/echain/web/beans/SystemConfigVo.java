package com.echain.web.beans;

import lombok.Data;

@Data
public class SystemConfigVo {
	//操作日志
	private String optlog;
	
	//USDT汇率
	private String exchangeRate;
	
	//矿机下级上限
	private String limitChildrenCount;
	
	//团队长提成比例
	private String teamLeaderCommissionRate;
	
	//最小划转数量
	private String minTransferCount;
	
	//划转手续费率
	private String transferRate;
	
	//划转手续费下限
	private String minTransferAmount;
	
	//划转手续费上限
	private String maxTransferAmount;
	
	//最小提现数量
	private String minCashOutCount;
	
	//提现手续费率
	private String cashOutRate;
	
	//提现手续费下限
	private String minCashOutAmount;
	
	//提现手续费上限
	private String maxCashOutAmount;
		
}