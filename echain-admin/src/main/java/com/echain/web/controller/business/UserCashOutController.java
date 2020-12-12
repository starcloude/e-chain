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
import com.echain.common.utils.Json;
import com.echain.domain.business.user.UserCashOut;
import com.echain.domain.sys.SysOperateLog;
import com.echain.domain.sys.SysUser;
import com.echain.service.business.UserCashOutService;
import com.echain.web.controller.AbsSupperController;

@RestController
@RequestMapping(value = "/api/business/user/cashout", method = { RequestMethod.POST, RequestMethod.GET })
public class UserCashOutController extends AbsSupperController {

	@Autowired
	private UserCashOutService userCashOutService;

	private final String promitionNameSpace = "vuser_cashout:";
	
	@RequiresPermissions(promitionNameSpace+"view")
	@RequestMapping(value = "query", method = RequestMethod.POST)
	public PageJsonResult query(UserCashOut entity,PageQuery pageQuery) {
		if (entity == null) {
			entity = new UserCashOut();
		}
		SysUser user = getLoginUser();
		operateLoger.info(new SysOperateLog(user, "提现管理", "index"));
		Page<UserCashOut> iPageQuery = new Page<UserCashOut>(pageQuery.getPageNo(), pageQuery.getPageSize());
		iPageQuery.addOrder(OrderItem.descs("id"));
		IPage<UserCashOut> page = userCashOutService.select(iPageQuery, entity);
		return toPageJson(page);
	}

	/**
	 * check
	 * @param entity
	 * @return
	 */
	protected JsonResult checkEntity(UserCashOut entity) {
		if(entity.getState() ==null || entity.getId() == null ||
				(StringUtils.isNotBlank(entity.getMemo()) && entity.getMemo().length()>50 )) {
			return error("错误的参数!");
		}
		return null;
	}
	
	/**
	 * 提现成功
	 * @param entity
	 * @return
	 */
	@RequiresPermissions(promitionNameSpace+"edit")
	@RequestMapping(value = "success", method = RequestMethod.POST)
	public JsonResult success(UserCashOut entity) {
		JsonResult rst = checkEntity(entity);
		if(rst != null) {
			return rst;
		}
		SysUser user = getLoginUser();
		operateLoger.info(new SysOperateLog(user, "提现管理-成功", Json.toJSON(entity)));
		userCashOutService.success(entity);
		return ok();
	}
	
	/**
	 * 提现失败
	 * @param entity
	 * @return
	 */
	@RequiresPermissions(promitionNameSpace+"edit")
	@RequestMapping(value = "fail", method = RequestMethod.POST)
	public JsonResult fail(UserCashOut entity) {
		JsonResult rst = checkEntity(entity);
		if(rst != null) {
			return rst;
		}
		SysUser user = getLoginUser();
		operateLoger.info(new SysOperateLog(user, "提现管理-失败", Json.toJSON(entity)));
		userCashOutService.fail(entity);
		return ok();
	}
}
