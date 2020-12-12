package com.echain.dao.business;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.echain.domain.business.ChatMsg;
import com.echain.domain.business.user.msg.RequestChatMsg;
import com.echain.domain.business.user.msg.ResponseChatMsg;

public interface ChatMsgDao extends BaseMapper<ChatMsg>{

	@Select("<script>"
			+ "select chat_id as chatId,count(1) as msgCount,max(create_time) as updateTime from v_chat_msg"
			+" <where> "
			+ "<if test=\"enty.chatId!= null\"><![CDATA[chat_id =  #{enty.chatId}]]></if>"
			+ " </where>"
			+ " group by chat_id order by 3 desc limit #{enty.start},#{enty.end}"
			+ "</script>")
	List<ResponseChatMsg> selectChatMsg(@Param("enty") RequestChatMsg enty);
	
	@Select("<script>"
			+ "select count(distinct chat_id) from v_chat_msg"
			+" <where> "
			+ "<if test=\"enty.chatId!= null\"><![CDATA[chat_id =  #{enty.chatId}]]></if>"
			+ " </where>"
			+ "</script>")
	Long selectChatMsgCount(@Param("enty") RequestChatMsg enty);
	
}
