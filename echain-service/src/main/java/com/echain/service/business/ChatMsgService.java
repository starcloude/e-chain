package com.echain.service.business;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.echain.dao.business.ChatMsgDao;
import com.echain.domain.business.ChatMsg;
import com.echain.domain.business.user.msg.RequestChatMsg;
import com.echain.domain.business.user.msg.ResponseChatMsg;

@Service
public class ChatMsgService {

	@Autowired
	private ChatMsgDao chatMsgDao;

	/**
	 * 插入
	 * @param enty
	 * @return
	 */
	public Long add(ChatMsg enty) {
		int nCount = chatMsgDao.insert(enty);
		if (nCount == 1) {
			return enty.getId();
		}
		return null;
	}
	/**
	 * 查询总记录数
	 * @param enty
	 * @return
	 */
	public Integer selectCount(ChatMsg enty) {
		return chatMsgDao.selectCount(toWrapper(enty));
	}
	
	/**
	 * 根据实体查询对象信息
	 * @param enty
	 * @return
	 */
	public List<ChatMsg> select(ChatMsg enty) {
		return chatMsgDao.selectList(limitFiled(toWrapper(enty)));
	}
	
	/**
	 * 更新
	 * 
	 * @param enty
	 * @return
	 */
	public int update(ChatMsg enty) {
		int nRst = chatMsgDao.updateById(enty);
		return nRst;
	}
	
	/**
	 * 根据ID获取
	 * 
	 * @param id
	 * @return
	 */
	public ChatMsg get(Long id) {
		return chatMsgDao.selectById(id);
	}
	
	/**
	 * 根据userId删除
	 * 
	 * @param userId
	 * @return
	 */
	public int deleteByUserId(Long userId) {
		ChatMsg msg = new ChatMsg();
		msg.setFromUserId(userId);
		List<ChatMsg> list = chatMsgDao.selectList(limitFiled(toWrapper(msg)));
		List<Integer> map = new ArrayList<Integer>();
		list.forEach(m -> {
			int res = chatMsgDao.deleteById(m.getId());
			map.add(res);
		});
		return map.size();
	}
	
	/**
	 * 分页查询
	 * 
	 * @param page
	 * @param enty
	 * @return
	 */
	public IPage<ChatMsg> select(IPage<ChatMsg> page, ChatMsg enty) {
		LambdaQueryWrapper<ChatMsg> query = limitFiled(toWrapper(enty));
		return chatMsgDao.selectPage(page, query);
	}
	
	private LambdaQueryWrapper<ChatMsg> limitFiled(LambdaQueryWrapper<ChatMsg> query) {
		return query.select(ChatMsg::getId,ChatMsg::getChatId,ChatMsg::getFromUserId,ChatMsg::getToUserId,ChatMsg::getToUserNick,ChatMsg::getContent,ChatMsg::getCreateTime,ChatMsg::getCreatePin);
	}
	
	/**
	 * 查询条件
	 * @param enty
	 * @return
	 */
	private LambdaQueryWrapper<ChatMsg> toWrapper(ChatMsg enty) {
		return new QueryWrapper<ChatMsg>().lambda()
			// id
			.eq(enty.getId() != null, ChatMsg::getId, enty.getId())
			// chatId
			.eq(enty.getChatId() != null, ChatMsg::getChatId, enty.getChatId())
			// fromUserId
			.eq(enty.getFromUserId() != null, ChatMsg::getFromUserId, enty.getFromUserId())
			// toUserId
			.eq(enty.getToUserId() != null, ChatMsg::getToUserId, enty.getToUserId())
			// toUserNick
			.eq(StringUtils.isNotBlank(enty.getToUserNick()), ChatMsg::getToUserNick, enty.getToUserNick())
			// content
			.like(StringUtils.isNotBlank(enty.getContent()), ChatMsg::getContent, enty.getContent())
			//ID 倒叙（后台倒叙，前端正序，因为在查询时确定）
			//.orderByDesc(ChatMsg::getId)
			;
	}

	/************** 用于后端查询 分页****************/
	public IPage<ResponseChatMsg> selectChatMsg(RequestChatMsg request){
		IPage<ResponseChatMsg> page = new Page<ResponseChatMsg>();
		Long total =  chatMsgDao.selectChatMsgCount(request);
		if(total == null || total ==0) {
			return page;
		}
		//总记录数
		page.setTotal(total);
		//每页行数
		page.setSize(request.getPageSize());
		//当前记录数
		page.setRecords(chatMsgDao.selectChatMsg(request));
		//当前页数
		page.setCurrent(request.getPageNo());
		return page;
	}
}
