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
import com.echain.common.enums.business.user.UserAuthenticationStateEnum;
import com.echain.common.utils.Json;
import com.echain.domain.business.user.UserAuthentication;
import com.echain.domain.sys.SysOperateLog;
import com.echain.domain.sys.SysUser;
import com.echain.service.business.UserAuthenticationService;
import com.echain.web.controller.AbsSupperController;

@RestController
@RequestMapping(value = "/api/business/user/auth", method = { RequestMethod.POST, RequestMethod.GET })
public class UserAuthenticationController extends AbsSupperController {

	@Autowired
	private UserAuthenticationService userAuthenticationService;

	private final String promitionNameSpace = "vuser_auth:";
	
	@RequiresPermissions(promitionNameSpace+"view")
	@RequestMapping(value = "query", method = RequestMethod.POST)
	public PageJsonResult query(UserAuthentication entity,PageQuery pageQuery) {
		if (entity == null) {
			entity = new UserAuthentication();
		}
		SysUser user = getLoginUser();
		operateLoger.info(new SysOperateLog(user, "认证管理", "index"));
		
		Page<UserAuthentication> iPageQuery = new Page<UserAuthentication>(pageQuery.getPageNo(), pageQuery.getPageSize());
		iPageQuery.addOrder(OrderItem.descs("id"));
		IPage<UserAuthentication> page = userAuthenticationService.select(iPageQuery, entity);
		return toPageJson(page);
	}
	
	/**
	 * 只编辑状态 和 memo
	 * @param entity
	 * @return
	 */
	@RequiresPermissions(promitionNameSpace+"approve")
	@RequestMapping(value = "approve", method = RequestMethod.POST)
	public JsonResult approve(UserAuthentication entity) {
		JsonResult rst = checkEntity(entity);
		if (rst != null) {
			return rst;
		}
		try {
			SysUser user = getLoginUser();
			UserAuthenticationStateEnum newState = UserAuthenticationStateEnum.get(entity.getState());
			if(newState == null) {
				return error("无法识别的状态!"+entity.getState());
			}
			
			UserAuthentication dbEntity = userAuthenticationService.get(entity.getId());
			if(dbEntity==null) {
				return error("用户["+entity.getIdCardImg1()+"]不存在!");
			}
			
			if(dbEntity.getState() != null && UserAuthenticationStateEnum.INIT.getCode() != dbEntity.getState()) {
				return error("当前状态,不允许进行修改!");
			}
			
			dbEntity.setUpdatePin(user.getNo());
			dbEntity.setState(newState.getCode());
			dbEntity.setMemo(entity.getMemo());
			
			operateLoger.info(new SysOperateLog(user, "认证管理-修改", Json.toJSON(entity)));
			userAuthenticationService.update(dbEntity);
			return ok();
		} catch (Exception ex) {
			return error("更新发生异常,请联系管理员!");
		}
	}

	/**
	 * check
	 * @param entity
	 * @return
	 */
	private JsonResult checkEntity(UserAuthentication entity) {
		if(entity.getState() ==null || entity.getId() == null ||
				(StringUtils.isNotBlank(entity.getMemo()) && entity.getMemo().length()>50 )) {
			return error("错误的参数!");
		}
		return null;
	}
}
