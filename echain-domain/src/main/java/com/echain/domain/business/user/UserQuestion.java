package com.echain.domain.business.user;

import com.baomidou.mybatisplus.annotation.TableName;
import com.echain.domain.UpdateTimeEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("v_user_question")
public class UserQuestion extends UpdateTimeEntity{
	
	/**
	 * 用户ID
	 */
	private Long userId;
	
	/**
	 * 问题ID
	 */
	private Long questionId;
	
	/**
	 * 答案
	 */
	private String answer;
}
