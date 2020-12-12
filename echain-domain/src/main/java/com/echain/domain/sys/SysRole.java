package com.echain.domain.sys;

import com.baomidou.mybatisplus.annotation.TableName;
import com.echain.domain.BaseLogicEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("sys_role")
public class SysRole extends BaseLogicEntity {
	/**
	 * 角色名称
	 */
	private String name;
}