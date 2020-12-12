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
import com.echain.domain.business.user.UserUSDTWallet;
import com.echain.domain.sys.SysOperateLog;
import com.echain.domain.sys.SysUser;
import com.echain.service.business.UserUsdtWalletService;
import com.echain.web.controller.AbsSupperController;

@RestController
@RequestMapping(value = "/api/business/user/usdtwallet", method = { RequestMethod.POST, RequestMethod.GET })
public class UserUSDTWalletController extends AbsSupperController {

	@Autowired
	private UserUsdtWalletService userUsdtWalletService;

	private final String promitionNameSpace = "vuser_usdtwallet:";
	
	@RequiresPermissions(promitionNameSpace+"view")
	@RequestMapping(value = "query", method = RequestMethod.POST)
	public PageJsonResult query(UserUSDTWallet entity,PageQuery pageQuery) {
		if (entity == null) {
			entity = new UserUSDTWallet();
		}
		SysUser user = getLoginUser();
		operateLoger.info(new SysOperateLog(user, "USDT钱包管理", "index"));
		Page<UserUSDTWallet> iPageQuery = new Page<UserUSDTWallet>(pageQuery.getPageNo(), pageQuery.getPageSize());
		IPage<UserUSDTWallet> page = userUsdtWalletService.select(iPageQuery, entity);
		return toPageJson(page);
	}
}
