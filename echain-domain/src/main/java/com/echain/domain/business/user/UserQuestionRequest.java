package com.echain.domain.business.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserQuestionRequest {
	
	/**
	 * 问题ID
	 */
	private Long[] questionId;
	
	/**
	 * 答案
	 */
	private String[] answer;
	
	/**
	 * 账号
	 */
	private String account;
	
	/**
	 * 密码
	 */
	private String password;
}
