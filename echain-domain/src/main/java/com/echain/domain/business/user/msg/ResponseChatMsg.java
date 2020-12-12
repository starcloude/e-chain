package com.echain.domain.business.user.msg;

import java.util.Date;

import lombok.Data;

@Data
public class ResponseChatMsg {

	private Long chatId;
	
	private Long msgCount;
	
	private Date updateTime;
}
