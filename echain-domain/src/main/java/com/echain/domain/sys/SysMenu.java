package com.echain.domain.sys;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.echain.domain.BaseLogicEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("sys_menu")
public class SysMenu extends BaseLogicEntity {

	/**
	 * 上级ID
	 */
	@TableField(value = "p_id")
	private Long pid;

	/**
	 * 图标
	 */
	@TableField(updateStrategy = FieldStrategy.NOT_NULL)
	private String icon;

	/**
	 * 类型
	 */
	private Integer type;

	/**
	 * 菜单编码 shiro权限使用
	 */
	private String code;

	/**
	 * 文案
	 */
	private String text;

	/**
	 * 访问地址
	 */
	private String url;

	/**
	 * 序号
	 */
	private Integer idx;

	/************************* 扩展字段 *************************/
	/**
	 * 权限ID
	 */
	@TableField(exist = false)
	private Long rid;

}
