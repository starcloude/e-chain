package com.echain.domain.business.user;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.echain.domain.UpdateTimeEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("v_user_wallet")
public class UserWallet extends UpdateTimeEntity {
	
	/**
	 * 主键ID
	 */
	@TableId(value = "id", type = IdType.INPUT)
	private Long id;
	
	/**
	 * 交易密码
	 */
	private String tradePassword;
	
	/**
	 * salt
	 */
	private String salt;
	
	/**
	 * 总金额
	 */
	private BigDecimal totalAmount;
	
	/**
	 * 可用金额
	 */
	private BigDecimal usableAmount;
	
	/**
	 * 冻结金额
	 */
	private BigDecimal frozenAmount;
}
