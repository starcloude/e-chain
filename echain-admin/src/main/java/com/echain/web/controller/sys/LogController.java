package com.echain.web.controller.sys;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.echain.common.beans.PageJsonResult;
import com.echain.common.beans.PageQuery;
import com.echain.domain.sys.SysOperateLog;
import com.echain.domain.sys.SysUser;
import com.echain.service.log.OperateLogService;
import com.echain.web.controller.AbsSupperController;

@RestController
@RequestMapping(value = "/api/system/log/")
public class LogController extends AbsSupperController {
	@Resource
	private OperateLogService operateLogService;

	@RequiresPermissions("log:view")
	@RequestMapping(value = "query", method = RequestMethod.POST)
	public PageJsonResult query(SysOperateLog queryLog,PageQuery pageQuery) {
		SysUser user = getLoginUser();
		operateLoger.info(new SysOperateLog(user, "日志查询", "index"));
		Page<SysOperateLog> page = new Page<SysOperateLog>(pageQuery.getPageNo(), pageQuery.getPageSize());
		page.addOrder(OrderItem.descs("id"));
		IPage<SysOperateLog> dbPage = operateLogService.select(page, queryLog);
		return toPageJson(dbPage);
	}
}
