package com.echain.web.controller.business;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.echain.common.beans.JsonResult;
import com.echain.common.beans.PageJsonResult;
import com.echain.common.beans.PageQuery;
import com.echain.domain.business.user.UserWallet;
import com.echain.domain.sys.SysOperateLog;
import com.echain.domain.sys.SysUser;
import com.echain.service.business.UserWalletService;
import com.echain.web.controller.AbsSupperController;

@RestController
@RequestMapping(value = "/api/business/user/wallet", method = { RequestMethod.POST, RequestMethod.GET })
public class UserWalletController extends AbsSupperController {
	
	@Autowired
	private UserWalletService userWalletService;

	private final String promitionNameSpace = "vuser_wallet:";
	
	@RequiresPermissions(promitionNameSpace+"view")
	@RequestMapping(value = "query", method = RequestMethod.POST)
	public PageJsonResult query(UserWallet entity,PageQuery pageQuery) {
		if (entity == null) {
			entity = new UserWallet();
		}
		SysUser user = getLoginUser();
		operateLoger.info(new SysOperateLog(user, "钱包管理", "index"));
		Page<UserWallet> iPageQuery = new Page<UserWallet>(pageQuery.getPageNo(), pageQuery.getPageSize());
		iPageQuery.addOrder(OrderItem.descs("usable_amount"));
		IPage<UserWallet> page = userWalletService.select(iPageQuery, entity);
		return toPageJson(page);
	}

	/**
	 * check
	 * @param entity
	 * @return
	 */
	protected JsonResult checkEntity(UserWallet entity) {
		if(entity.getId() == null) {
			return error("错误的参数!");
		}
		return null;
	}
	
	@RequiresPermissions(promitionNameSpace+"resetTradePwd")
	@RequestMapping(value = "resetTradePwd", method = RequestMethod.POST)
	public JsonResult resetTradePwd(UserWallet entity) {
		JsonResult rst = checkEntity(entity);
		if(rst!=null && StringUtils.isBlank(entity.getTradePassword())) {
			return error("错误的参数");
		}
		SysUser user = getLoginUser();
		operateLoger.info(new SysOperateLog(user, "重置交易密码!", String.format("玩家:[%s]", entity.getId())));
		if(userWalletService.resetTradePwd(entity)) {
			return ok();
		}
		return error("操作失败");
	}
}
