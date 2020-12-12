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
import com.echain.domain.business.user.UserRecharge;
import com.echain.domain.sys.SysOperateLog;
import com.echain.domain.sys.SysUser;
import com.echain.service.business.UserRechargeService;
import com.echain.web.controller.AbsSupperController;

@RestController
@RequestMapping(value = "/api/business/recharge/", method = { RequestMethod.POST, RequestMethod.GET })
public class UserRechargeController extends AbsSupperController {

	@Autowired
	private UserRechargeService userRechargeService;

	private final String promitionNameSpace = "recharge:";
	
	@RequiresPermissions(promitionNameSpace+"view")
	@RequestMapping(value = "query", method = RequestMethod.POST)
	public PageJsonResult query(UserRecharge entity,PageQuery pageQuery) {
		if (entity == null) {
			entity = new UserRecharge();
		}
		SysUser user = getLoginUser();
		operateLoger.info(new SysOperateLog(user, "充值管理", "index"));
		Page<UserRecharge> iPageQuery = new Page<UserRecharge>(pageQuery.getPageNo(), pageQuery.getPageSize());
		iPageQuery.addOrder(OrderItem.descs("id"));
		IPage<UserRecharge> page = userRechargeService.select(iPageQuery, entity);
		return toPageJson(page);
	}

	/**
	 * check
	 * @param entity
	 * @return
	 */
	protected JsonResult checkEntity(UserRecharge entity) {
		if(entity.getState() ==null || entity.getId() == null ||
				(StringUtils.isNotBlank(entity.getMemo()) && entity.getMemo().length()>50 )) {
			return error("错误的参数!");
		}
		return null;
	}
	
	/**
	 * 成功
	 * @param entity
	 * @return
	 */
	@RequiresPermissions(promitionNameSpace+"edit")
	@RequestMapping(value = "success", method = RequestMethod.POST)
	public JsonResult success(UserRecharge entity) {
		JsonResult rst = checkEntity(entity);
		if(rst != null) {
			return rst;
		}
		SysUser user = getLoginUser();
		operateLoger.info(new SysOperateLog(user, "充值管理-成功", Json.toJSON(entity)));
		userRechargeService.success(entity);
		return ok();
	}
	
	/**
	 * 失败
	 * @param entity
	 * @return
	 */
	@RequiresPermissions(promitionNameSpace+"edit")
	@RequestMapping(value = "fail", method = RequestMethod.POST)
	public JsonResult fail(UserRecharge entity) {
		JsonResult rst = checkEntity(entity);
		if(rst != null) {
			return rst;
		}
		SysUser user = getLoginUser();
		operateLoger.info(new SysOperateLog(user, "充值管理-失败", Json.toJSON(entity)));
		userRechargeService.fail(entity);
		return ok();
	}
}
