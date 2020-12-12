package com.echain.domain.business.user;

import com.baomidou.mybatisplus.annotation.TableName;
import com.echain.domain.UpdateTimeEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("v_user_usdt_wallet")
public class UserUSDTWallet extends UpdateTimeEntity {
	/**
	 * 用户ID
	 */
	private Long userId;
	
	/**
	 * usdt链名称 
	 */
	private String chain;
	
	/**
	 * 钱包地址
	 */
	private String address;
}
