package com.echain.web.controller.business;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.echain.common.beans.JsonResult;
import com.echain.common.beans.PageQuery;
import com.echain.domain.business.ChatMsg;
import com.echain.domain.business.user.User;
import com.echain.domain.business.user.msg.RequestChatMsg;
import com.echain.domain.sys.SysOperateLog;
import com.echain.domain.sys.SysUser;
import com.echain.service.business.ChatMsgService;
import com.echain.service.common.DbDataCache;
import com.echain.web.controller.AbsSupperController;

@RestController
@RequestMapping(value = "/api/business/chatmsg", method = { RequestMethod.POST, RequestMethod.GET })
public class ChatMsgController extends AbsSupperController {

	@Autowired
	private ChatMsgService chatMsgService;

	private final String promitionNameSpace = "chatmsg:";
	
	@Autowired
	private DbDataCache dbDataCache;
	
	@RequiresPermissions(promitionNameSpace+"view")
	@RequestMapping(value = "query", method = RequestMethod.POST)
	public JsonResult query(RequestChatMsg entity) {
		if (entity == null) {
			entity = new RequestChatMsg();
		}
		SysUser user = getLoginUser();
		operateLoger.info(new SysOperateLog(user, "客服留言管理", "index"));
		return toPageJson(chatMsgService.selectChatMsg(entity));
	}
	
	@RequiresPermissions(promitionNameSpace+"view")
	@RequestMapping(value = "queryHistory", method = RequestMethod.POST)
	public JsonResult queryHistory(ChatMsg entity,PageQuery pageQuery) {
		Page<ChatMsg> page = new Page<ChatMsg>(pageQuery.getPageNo(), pageQuery.getPageSize());
		page.addOrder(OrderItem.descs("id"));
		return toPageJson(chatMsgService.select(page, entity));
	}
	
	@RequiresPermissions(promitionNameSpace+"reply")
	@RequestMapping(value = "reply", method = RequestMethod.POST)
	public JsonResult reply(ChatMsg entity) {
		if(StringUtils.isAnyBlank(entity.getContent()) || entity.getChatId() == null) {
			return error("错误的参数!");
		}
		
		User vUser = dbDataCache.getVuser(entity.getChatId());
		if(vUser == null) {
			return error("无法获取用户信息,提交失败!");
		}
		
		entity.setContent(HtmlUtils.htmlEscape(entity.getContent()));
		SysUser user = getLoginUser();
		operateLoger.info(new SysOperateLog(user, "客服留言", "to:["+entity.getChatId()+"] 内容:["+entity.getContent()+"]"));
		
		//from
		entity.setFromUserId(user.getId());
		entity.setCreatePin(user.getNo());
		
		//to
		entity.setToUserId(entity.getChatId());
		entity.setToUserNick(vUser.getNickName());
		
		if(chatMsgService.add(entity)!=null) {
			return ok();
		}
		return error("操作失败!");
	}
	
}
