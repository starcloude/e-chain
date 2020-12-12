package com.echain.domain.business.user;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class SysFundCalcBean {

	/**
	 * 人群标签
	 */
	private String tag;
	/**
	 * 收入
	 */
	private BigDecimal i;
	/**
	 * 支出
	 */
	private BigDecimal o;
	/**
	 * 开始时间
	 */
	private Date begin;
	/**
	 * 截止时间
	 */
	private Date end;
}
