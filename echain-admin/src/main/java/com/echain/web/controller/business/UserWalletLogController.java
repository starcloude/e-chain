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
import com.echain.domain.business.user.UserWalletLog;
import com.echain.domain.sys.SysOperateLog;
import com.echain.domain.sys.SysUser;
import com.echain.service.business.UserWalletService;
import com.echain.web.controller.AbsSupperController;

@RestController
@RequestMapping(value = "/api/business/user/walletlog", method = { RequestMethod.POST, RequestMethod.GET })
public class UserWalletLogController extends AbsSupperController {

	@Autowired
	private UserWalletService userWalletService;

	private final String promitionNameSpace = "vuser_walletlog:";
	
	@RequiresPermissions(promitionNameSpace+"view")
	@RequestMapping(value = "query", method = RequestMethod.POST)
	public PageJsonResult query(UserWalletLog entity,PageQuery pageQuery) {
		if (entity == null) {
			entity = new UserWalletLog();
		}
		SysUser user = getLoginUser();
		operateLoger.info(new SysOperateLog(user, "钱包日志管理", "index"));
		Page<UserWalletLog> iPageQuery = new Page<UserWalletLog>(pageQuery.getPageNo(), pageQuery.getPageSize());
		IPage<UserWalletLog> page = userWalletService.selectLogs(iPageQuery, entity);
		return toPageJson(page);
	}
}
