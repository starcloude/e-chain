package com.echain.domain.sys;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.echain.domain.BaseLogicEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("sys_role_menu")
public class SysRoleMenu extends BaseLogicEntity {
	/**
	 * 角色ID
	 */
	@TableField("r_id")
	private Long rid;
	
	/**
	 * 菜单ID
	 */
	@TableField("m_id")
	private Long mid;
	
}
