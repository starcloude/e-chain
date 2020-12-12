package com.echain.domain.business.user.msg;

import com.echain.common.beans.PageQuery;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RequestChatMsg extends PageQuery {

	/**
	 * 聊天ID
	 */
	private Long chatId;
	
}
