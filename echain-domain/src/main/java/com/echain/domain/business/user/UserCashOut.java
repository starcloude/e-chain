package com.echain.domain.business.user;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableName;
import com.echain.common.enums.business.user.UserCashOutStateEnum;
import com.echain.domain.UpdateTimeEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("v_user_cash_out")
public class UserCashOut extends UpdateTimeEntity {
	/**
	 * 用户ID
	 */
	private Long userId;
	
	/**
	 * 提现金额
	 */
	private BigDecimal amount;
	
	/**
	 * 手续费
	 */
	private BigDecimal fee;
	
	/**
	 * 提现的usdt数量
	 */
	private BigDecimal usdt;
	
	/**
	 * usdt链名称 
	 */
	private String chain;
	
	/**
	 * 钱包地址
	 */
	private String address;
	
	/**
	 * 状态
	 * @see UserCashOutStateEnum
	 */
	private Integer state;
	
	/**
	 * 备注信息
	 */
	private String memo;
	
}
