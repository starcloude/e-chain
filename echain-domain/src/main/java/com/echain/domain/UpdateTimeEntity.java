package com.echain.domain;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTimeEntity extends CreateTimeEntity {

	/**
	 * 更新时间
	 */
//	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	@TableField(update = "now()", fill = FieldFill.UPDATE)
	private Date updateTime;

	/**
	 * 更新人
	 */
	private String updatePin;
	
}
