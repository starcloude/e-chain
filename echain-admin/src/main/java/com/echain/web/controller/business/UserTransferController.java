package com.echain.web.controller.business;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.echain.common.beans.PageJsonResult;
import com.echain.common.beans.PageQuery;
import com.echain.domain.business.user.UserTransfer;
import com.echain.domain.sys.SysOperateLog;
import com.echain.domain.sys.SysUser;
import com.echain.service.business.UserTransferService;
import com.echain.web.controller.AbsSupperController;

@RestController
@RequestMapping(value = "/api/business/transfer", method = { RequestMethod.POST, RequestMethod.GET })
public class UserTransferController extends AbsSupperController {

	@Autowired
	private UserTransferService userTransferService;

	private final String promitionNameSpace = "transfer:";
	
	@RequiresPermissions(promitionNameSpace+"view")
	@RequestMapping(value = "query", method = RequestMethod.POST)
	public PageJsonResult query(UserTransfer entity,PageQuery pageQuery) {
		if (entity == null) {
			entity = new UserTransfer();
		}
		SysUser user = getLoginUser();
		operateLoger.info(new SysOperateLog(user, "用户密码问题管理", "index"));
		Page<UserTransfer> iPageQuery = new Page<UserTransfer>(pageQuery.getPageNo(), pageQuery.getPageSize());
		IPage<UserTransfer> page = userTransferService.select(iPageQuery, entity);
		return toPageJson(page);
	}
}
