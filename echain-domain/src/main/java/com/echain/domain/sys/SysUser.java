package com.echain.domain.sys;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.echain.domain.BaseLogicEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("sys_user")
public class SysUser extends BaseLogicEntity {

	/**
	 * 账号
	 */
	private String no;

	/**
	 * 密码
	 */
	@JsonIgnore
	private String pwd;

	/**
	 * 盐
	 */
	@JsonIgnore
	private String salt;

	/**
	 * 真实名称
	 */
	private String name;

	/**
	 * 角色ID
	 */
	@TableField("r_id")
	private Long rid;

	/**
	 * 登录的IP地址
	 */
	@TableField(exist = false)
	private String ip;
}
