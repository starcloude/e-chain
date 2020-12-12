package com.echain.domain.business.user;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableName;
import com.echain.common.enums.business.user.UserRechargeStateEnum;
import com.echain.domain.UpdateTimeEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("v_user_recharge")
public class UserRecharge extends UpdateTimeEntity {
	/**
	 * 用户ID
	 */
	private Long userId;

	/**
	 * 金额
	 */
	private BigDecimal amount;

	/**
	 * usdt个数
	 */
	private BigDecimal usdt;

	/**
	 * usdt 链名称
	 */
	private String chain;

	/**
	 * 钱包地址
	 */
	private String address;

	/**
	 * 流水号
	 */
	private String serialNo;

	/**
	 * 状态
	 * 
	 * @see UserRechargeStateEnum
	 */
	private Integer state;

	/**
	 * 备注信息
	 */
	private String memo;
	
}
