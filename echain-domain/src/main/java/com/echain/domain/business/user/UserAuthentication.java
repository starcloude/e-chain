package com.echain.domain.business.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.echain.common.enums.business.user.UserAuthenticationStateEnum;
import com.echain.domain.UpdateTimeEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("v_user_authentication")
public class UserAuthentication extends UpdateTimeEntity{
	
	/**
	 * 主键ID
	 */
	@TableId(value = "id", type = IdType.INPUT)
	private Long id;
	
	/**
	 * 真实姓名
	 */
	private String realName;
	/**
	 * 身份证号
	 */
	private String idCardNo;
	/**
	 * 身份证正面
	 */
	private String idCardImg1;
	/**
	 * 身份证反面
	 */
	private String idCardImg2;
	/**
	 * 手持身份证拍照
	 */
	private String idCardWithUser;
	/**
	 * 状态
	 * @see UserAuthenticationStateEnum
	 */
	private Integer state;
	/**
	 * 备注信息
	 */
	private String memo;
}
