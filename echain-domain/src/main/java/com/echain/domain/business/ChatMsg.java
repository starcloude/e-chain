package com.echain.domain.business;

import com.baomidou.mybatisplus.annotation.TableName;
import com.echain.domain.CreateTimeEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("v_chat_msg")
public class ChatMsg extends CreateTimeEntity {
	
	/**
	 * 聊天室ID 就是 v_user.id 一个用户只有一个会话ID
	 */
	private Long chatId;
	
	/**
	 * 发起人
	 */
	private Long fromUserId;
	
	/**
	 * 接收人的ID
	 */
	private Long toUserId;
	
	/**
	 * 接收人的昵称
	 */
	private String toUserNick;
	
	/**
	 * 内容
	 */
	private String content;
}
