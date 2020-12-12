package com.echain.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseLogicEntity extends UpdateTimeEntity {

	/**
	 * 逻辑删除字段
	 */
//	@TableLogic(value="0",delval="1")
	@TableField(fill = FieldFill.INSERT)
	private Integer yn;
}
