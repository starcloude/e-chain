package com.echain.web.controller.sys;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.echain.common.beans.JsonResult;
import com.echain.common.beans.PageJsonResult;
import com.echain.common.beans.PageQuery;
import com.echain.common.utils.Json;
import com.echain.domain.sys.SysOperateLog;
import com.echain.domain.sys.SysRole;
import com.echain.domain.sys.SysUser;
import com.echain.service.sys.SysRoleService;
import com.echain.web.controller.AbsSupperController;

@RestController
@RequestMapping(value = "/api/system/role/")
public class RoleController extends AbsSupperController {

	@Resource
	private SysRoleService roleService;

	@RequiresPermissions("role:view")
	@RequestMapping(value = "query", method = RequestMethod.POST)
	public PageJsonResult query(SysRole role,PageQuery pageQuery) {
		if (role == null) {
			role = new SysRole();
		}
		SysUser user = getLoginUser();
		operateLoger.info(new SysOperateLog(user, "角色管理", "index"));
		
		IPage<SysRole> page = roleService.select(new Page<SysRole>(pageQuery.getPageNo(), pageQuery.getPageSize()), role);
		return toPageJson(page);
	}
	
	/**
	 * 查询所有的角色信息
	 * 不设置权限
	 * @param role
	 * @return
	 */
	@RequestMapping(value = "query", method = RequestMethod.GET)
	public JsonResult query() {
		SysRole role = new SysRole();
		role.setYn(0);
		return JsonResult.OK(roleService.select(role));
	}

	@RequiresPermissions("role:edit")
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public JsonResult edit(SysRole role) {
		if (role.getId() == null || role.getId() <= 0 || StringUtils.isBlank(role.getName())) {
			return JsonResult.Error("错误的参数!");
		}
		// 不允许修改YN
		role.setYn(null);
		try {
			SysUser user = getLoginUser();
			operateLoger.info(new SysOperateLog(user, "角色管理-修改", Json.toJSON(role)));
			role.setUpdatePin(user.getNo());
			roleService.update(role);
			return JsonResult.OK();
		} catch (Exception ex) {
			return JsonResult.Error("更新发生异常,请联系管理员!");
		}
	}

	@RequiresPermissions("role:add")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public JsonResult add(SysRole role) {
		if (StringUtils.isBlank(role.getName())) {
			return JsonResult.Error("错误的参数!");
		}
		try {
			SysUser user = getLoginUser();
			operateLoger.info(new SysOperateLog(user, "角色管理-添加", Json.toJSON(role)));
			role.setCreatePin(user.getNo());
			roleService.insert(role);
			return JsonResult.OK();
		} catch (Exception ex) {
			return JsonResult.Error("新增发生异常,请联系管理员!");
		}
	}

	@RequiresPermissions("role:delete")
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public JsonResult delete(Long id, Integer yn) {
		if (id == null || id <= 0 || yn == null || yn < 0) {
			return JsonResult.Error("错误的参数!");
		}

		yn = yn == 0 ? 0 : 1;
		SysRole role = new SysRole();
		role.setId(id);	
		role.setYn(yn);

		try {
			SysUser user = getLoginUser();
			operateLoger.info(new SysOperateLog(user, "角色管理-删除/恢复", Json.toJSON(role)));
			role.setUpdatePin(user.getNo());
			roleService.update(role);
			return JsonResult.OK();
		} catch (Exception ex) {
			return JsonResult.Error("删除发生异常,请联系管理员!");
		}
	}
}
