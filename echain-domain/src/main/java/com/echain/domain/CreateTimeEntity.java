package com.echain.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTimeEntity extends IDEntity {

	/**
	 * 创建时间
	 */
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@TableField(updateStrategy = FieldStrategy.NEVER,insertStrategy = FieldStrategy.NEVER)
	private Date createTime;
	
	// 创建时间截止
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@TableField(exist = false)
	private Date createTimeEnd;

	/**
	 * 创建人
	 */
	@TableField(updateStrategy = FieldStrategy.NEVER)
	private String createPin;

}
