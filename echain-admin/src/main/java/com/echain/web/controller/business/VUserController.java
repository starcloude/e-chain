package com.echain.web.controller.business;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
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
import com.echain.common.enums.YnEnum;
import com.echain.common.enums.business.user.UserTypeEnum;
import com.echain.common.utils.Json;
import com.echain.common.utils.MD5Utils;
import com.echain.domain.business.user.User;
import com.echain.domain.sys.SysOperateLog;
import com.echain.domain.sys.SysUser;
import com.echain.service.business.UserService;
import com.echain.web.controller.AbsSupperController;

@RestController
@RequestMapping(value = "/api/business/user", method = { RequestMethod.POST, RequestMethod.GET })
public class VUserController extends AbsSupperController {

	@Resource
	private UserService userService;

	private final String promitionNameSpace = "vuser:";
	
	@RequiresPermissions(promitionNameSpace+"view")
	@RequestMapping(value = "query", method = RequestMethod.POST)
	public PageJsonResult query(User entity,PageQuery pageQuery) {
		if (entity == null) {
			entity = new User();
		}
		SysUser user = getLoginUser();
		operateLoger.info(new SysOperateLog(user, "玩家管理", "index"));
		
		Page<User> iPageQuery = new Page<User>(pageQuery.getPageNo(), pageQuery.getPageSize());
		iPageQuery.addOrder(OrderItem.descs("id"));
		IPage<User> page = userService.select(iPageQuery, entity);
		return toPageJson(page);
	}
	
	@RequiresPermissions(promitionNameSpace+"edit")
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public JsonResult edit(User entity) {
		JsonResult rst = checkEntity(entity);
		if (rst != null) {
			return rst;
		}
		try {
			SysUser user = getLoginUser();
			operateLoger.info(new SysOperateLog(user, "玩家管理-修改", Json.toJSON(entity)));
			if(entity.getId() == null || entity.getId() == 0) {
				//设置密码
				entity.setSalt(RandomStringUtils.randomNumeric(8));
				if(StringUtils.isNotBlank(entity.getPassword())) {
					String pwd = MD5Utils.encryptPassword(entity.getPassword(), entity.getSalt());
					entity.setPassword(pwd);
				}else {
					if (entity.getPassword().length() < 3 || entity.getPassword().length() > 30) {
						return JsonResult.Error("密码少于3个字符或超过30个字符!");
					}
				}
				entity.setAccountType(UserTypeEnum.NORMAL.getCode());
				userService.add(entity);
			}else {
				User dbUser = userService.get(entity.getId());
				if(dbUser==null) {
					return error("用户不存在!");
				}
		
				User myUser = new User();
				//昵称
				myUser.setNickName(entity.getNickName());
				//密码
				if(StringUtils.isNotBlank(entity.getPassword())) {
					myUser.setPassword(MD5Utils.encryptPassword(entity.getPassword(), dbUser.getSalt()));
				}
				//类型
				myUser.setType(entity.getType());
				//状态
				myUser.setState(entity.getState());
				//id
				myUser.setId(dbUser.getId());
				myUser.setUpdatePin(user.getNo());
				
				if(
					//以前不是代理,现在是代理,并且分组不为空
					(UserTypeEnum.AGENT.getCode() == entity.getType() && UserTypeEnum.AGENT.getCode() != dbUser.getType() && StringUtils.isNotBlank(entity.getTag()))
						||
					//以前是代理,且分组是空的,且行分组不为空; (设置分组)
					(UserTypeEnum.AGENT.getCode() == dbUser.getType() && StringUtils.isNotBlank(entity.getTag()) && StringUtils.isBlank(dbUser.getTag()))
				) {
					myUser.setTag(entity.getTag());
				}
				userService.update(myUser);
			}
			return ok();
		} catch (Exception ex) {
			return error("更新发生异常,请联系管理员!");
		}
	}

	@RequiresPermissions(promitionNameSpace+"delete")
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public JsonResult delete(Long id, Integer yn) {
		YnEnum eYn = YnEnum.get(yn);
		if (eYn == null) {
			return error("错误的参数!");
		}
		User entity = new User();
		entity.setId(id);	
		entity.setYn(eYn.getCode());
		try {
			SysUser user = getLoginUser();
			operateLoger.info(new SysOperateLog(user, "玩家管理-删除/恢复", Json.toJSON(entity)));
			entity.setUpdatePin(user.getNo());
			userService.update(entity);
			return ok();
		} catch (Exception ex) {
			return error("删除发生异常,请联系管理员!");
		}
	}
	
	/**
	 * check
	 * @param user
	 * @return
	 */
	private JsonResult checkEntity(User user) {
		return null;
	}
}
