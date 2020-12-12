package com.echain.web.controller.business;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.echain.common.beans.JsonResult;
import com.echain.common.beans.PageJsonResult;
import com.echain.common.beans.PageQuery;
import com.echain.common.enums.business.user.UserMachineStateEnum;
import com.echain.common.utils.Json;
import com.echain.domain.business.user.UserMachine;
import com.echain.domain.sys.SysOperateLog;
import com.echain.domain.sys.SysUser;
import com.echain.service.business.UserMachineService;
import com.echain.web.controller.AbsSupperController;

@RestController
@RequestMapping(value = "/api/business/user/machine", method = { RequestMethod.POST, RequestMethod.GET })
public class UserMachineController extends AbsSupperController {

	@Autowired
	private UserMachineService userMachineService;

	private final String promitionNameSpace = "vuser_machine:";
	
	@RequiresPermissions(promitionNameSpace+"view")
	@RequestMapping(value = "query", method = RequestMethod.POST)
	public PageJsonResult query(UserMachine entity,PageQuery pageQuery) {
		if (entity == null) {
			entity = new UserMachine();
		}
		SysUser user = getLoginUser();
		operateLoger.info(new SysOperateLog(user, "用户矿机管理", "index"));
		Page<UserMachine> iPageQuery = new Page<UserMachine>(pageQuery.getPageNo(), pageQuery.getPageSize());
		IPage<UserMachine> page = userMachineService.select(iPageQuery, entity);
		return toPageJson(page);
	}
	
	@RequiresPermissions(promitionNameSpace+"stop")
	@RequestMapping(value = "stop", method = RequestMethod.POST)
	public JsonResult stop(Long id) {
		if(id == null || id <=0) {
			return error("错误的参数!");
		}
		UserMachine enty = new UserMachine();
		enty.setId(id);
		enty.setState(UserMachineStateEnum.STOPED.getCode());
		SysUser user = getLoginUser();
		operateLoger.info(new SysOperateLog(user, "用户矿机管理", Json.toJSON(enty)));
		enty.setUpdatePin(user.getNo());
		if(!userMachineService.updateByIdAndOldStatus(enty, UserMachineStateEnum.RUNNING)) {
			return error("停止失败,请联系管理员!或稍后重试!");
		}
		return ok();
	}
}
