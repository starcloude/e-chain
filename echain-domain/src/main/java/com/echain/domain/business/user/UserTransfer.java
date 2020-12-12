package com.echain.domain.business.user;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableName;
import com.echain.domain.CreateTimeEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("v_user_transfer")
public class UserTransfer extends CreateTimeEntity {
	/**
	 * 用户ID
	 */
	private Long userId;
	
	/**
	 * 划转数量
	 */
	private BigDecimal amount;
	
	/**
	 * 手续费
	 */
	private BigDecimal fee;
	
	/**
	 * 备注信息
	 */
	private String memo;
	
	/**
	 * 对方用户ID
	 */
	private Long toUserId;
	
	/**
	 * 对方账号
	 */
	private String toAccount;
	
}
