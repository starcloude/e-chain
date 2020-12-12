package com.echain.domain.business.user;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.echain.common.enums.business.user.UserMachineStateEnum;
import com.echain.domain.BaseLogicEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("v_user_machine")
public class UserMachine extends BaseLogicEntity{
	
	/**
	 * 用户ID
	 */
	private Long userId;
	
	/**
	 * 上级用户的ID
	 */
	private Long parentUserId;
	
	/**
	 * 上级产品的ID
	 */
	private Long parentMachineId;
	
	/**
	 * 产品型号
	 */
	private String code;
	
	/**
	 * 矿机ID
	 */
	private Long machineId;
	
	/**
	 * 金额
	 */
	private BigDecimal price;
	
	/**
	 * 单日收益
	 */
	private BigDecimal profit;
	
	/**
	 * 图标
	 */
	private String icon;
	
	/**
	 * 倍数
	 */
	private BigDecimal multiple;
	
	/**
	 * 邀请码
	 */
	private String invitedCode;
	
	
	/**
	 * 状态
	 * @see UserMachineStateEnum
	 */
	private Integer state;
	
	/**
	 * 开始日期
	 */
	private Date beginTime;
	
	/**
	 * 截止日期
	 */
	private Date endTime;
	
	/**
	 * 子矿机数量
	 */
	private Integer childrenCount;
}
