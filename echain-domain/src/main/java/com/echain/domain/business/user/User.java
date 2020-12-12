package com.echain.domain.business.user;

import com.baomidou.mybatisplus.annotation.TableName;
import com.echain.common.enums.business.user.UserStateEnum;
import com.echain.common.enums.business.user.UserTypeEnum;
import com.echain.domain.BaseLogicEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("v_user")
public class User extends BaseLogicEntity {
	/**
	 * 昵称
	 */
	private String nickName;
	/**
	 * 头像地址
	 */
	private String headImg;
	/**
	 * 账号
	 */
	private String account;
	/**
	 * 账号类型
	 */
	private Integer accountType;
	/**
	 * 密码
	 */
	@JsonIgnore
	private String password;
	/**
	 * salt
	 */
	@JsonIgnore
	private String salt;
	/**
	 * 用户状态
	 * 
	 * @see UserStateEnum
	 */
	private Integer state;
	/**
	 * 邀请人ID
	 */
	private Long invitedId;
	
	/**
	 * 账号类型
	 * @see UserTypeEnum
	 */
	private Integer type;
	
	/**
	 * 标签(自定义标签)
	 */
	private String tag;
}
