package com.echain.domain.business.user;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableName;
import com.echain.common.enums.business.user.UserWalletLogTypeEnum;
import com.echain.domain.CreateTimeEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("v_user_wallet_log")
public class UserWalletLog extends CreateTimeEntity {

	/**
	 * 用户ID
	 */
	private Long userId;

	/**
	 * 变动金额
	 */
	private BigDecimal amount;

	/**
	 * 类型
	 * 
	 * @see UserWalletLogTypeEnum
	 */
	private Integer type;

	/**
	 * 备注
	 */
	private String memo;
	
}
