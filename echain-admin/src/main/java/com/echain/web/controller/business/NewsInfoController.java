package com.echain.web.controller.business;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.echain.common.beans.JsonResult;
import com.echain.common.beans.PageJsonResult;
import com.echain.common.beans.PageQuery;
import com.echain.common.utils.Json;
import com.echain.domain.business.NewsInfo;
import com.echain.domain.sys.SysOperateLog;
import com.echain.domain.sys.SysUser;
import com.echain.service.business.NewsInfoService;
import com.echain.web.controller.AbsSupperController;

@RestController
@RequestMapping(value = "/api/business/news", method = { RequestMethod.POST, RequestMethod.GET })
public class NewsInfoController extends AbsSupperController {

	@Resource
	private NewsInfoService newsInfoService;

	private final String promitionNameSpace = "news:";
	
	@RequiresPermissions(promitionNameSpace+"view")
	@RequestMapping(value = "query", method = RequestMethod.POST)
	public PageJsonResult query(NewsInfo entity,PageQuery pageQuery) {
		if (entity == null) {
			entity = new NewsInfo();
		}
		SysUser user = getLoginUser();
		operateLoger.info(new SysOperateLog(user, "资讯管理", "index"));
		
		Page<NewsInfo> iPageQuery = new Page<NewsInfo>(pageQuery.getPageNo(), pageQuery.getPageSize());
		iPageQuery.addOrder(OrderItem.descs("id"));
		IPage<NewsInfo> page = newsInfoService.select(iPageQuery, entity);
		return toPageJson(page);
	}
	
	@RequiresPermissions(promitionNameSpace+"edit")
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public JsonResult edit(NewsInfo entity) {
		JsonResult rst = checkEntity(entity);
		if (rst != null) {
			return rst;
		}
		try {
			SysUser user = getLoginUser();
			NewsInfo enty = newsInfoService.get(entity.getId());
			if(enty == null) {
				operateLoger.info(new SysOperateLog(user, "资讯管理-新增", Json.toJSON(entity)));
				newsInfoService.add(entity);
			}else {
				operateLoger.info(new SysOperateLog(user, "资讯管理-修改", Json.toJSON(entity)));
				newsInfoService.update(entity);
			}
			return ok();
		} catch (Exception ex) {
			return error("保存资讯发生异常,请联系管理员!");
		}
	}

	@RequiresPermissions(promitionNameSpace+"edit")
	@RequestMapping(value = "changeState", method = RequestMethod.POST)
	public JsonResult changeState(Long id, Integer state) {
		if (id == null) {
			return error("错误的参数!");
		}
		NewsInfo entity = new NewsInfo();
		entity.setId(id);
		entity.setState(state);
		try {
			SysUser user = getLoginUser();
			operateLoger.info(new SysOperateLog(user, "资讯管理-审核", Json.toJSON(entity)));
			newsInfoService.update(entity);
			return ok();
		} catch (Exception ex) {
			return error("审核发生异常,请联系管理员!");
		}
	}
	
	@RequiresPermissions(promitionNameSpace+"delete")
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public JsonResult delete(Long id) {
		if (id == null) {
			return error("错误的参数!");
		}
		try {
			SysUser user = getLoginUser();
			operateLoger.info(new SysOperateLog(user, "资讯管理-删除", id.toString()));
			newsInfoService.delete(id);
			return ok();
		} catch (Exception ex) {
			return error("删除资讯发生异常,请联系管理员!");
		}
	}
	
	/**
	 * check
	 * @param NewsInfo
	 * @return
	 */
	private JsonResult checkEntity(NewsInfo enty) {
		return null;
	}
}
