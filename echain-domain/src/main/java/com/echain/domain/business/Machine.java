package com.echain.domain.business;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableName;
import com.echain.domain.BaseLogicEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("v_machine")
public class Machine extends BaseLogicEntity {
	
	/**
	 * 产品型号
	 */
	private String code;
	
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
	 * 可运行天数
	 */
	private Integer runningDays;
}
