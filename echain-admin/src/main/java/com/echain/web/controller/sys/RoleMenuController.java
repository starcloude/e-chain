package com.echain.web.controller.sys;

import java.awt.Menu;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.echain.common.beans.JsonResult;
import com.echain.common.beans.PageJsonResult;
import com.echain.common.utils.Json;
import com.echain.domain.sys.SysMenu;
import com.echain.domain.sys.SysOperateLog;
import com.echain.domain.sys.SysUser;
import com.echain.service.sys.SysMenuService;
import com.echain.service.sys.SysRoleService;
import com.echain.web.controller.AbsSupperController;
import com.google.common.collect.Lists;

@RestController
@RequestMapping(value = "/api/system/rolemenu/")
public class RoleMenuController extends AbsSupperController {

	@Resource
	private SysRoleService roleService;

	@Resource
	private SysMenuService menuService;

	@RequiresPermissions("rolemenu:view")
	@RequestMapping(value = "query", method = RequestMethod.POST)
	public PageJsonResult query(Long rid) {
		
		PageJsonResult result = new PageJsonResult(true);
		if (rid == null || rid <= 0) {
			result.setResult(new ArrayList<Menu>());
			return result;
		}
		
		SysUser user = getLoginUser();
		operateLoger.info(new SysOperateLog(user, "角色菜单", "index"));
		
		List<SysMenu> mList = menuService.selectByRoleId(rid);
		result.setCurrent(1);
		result.setSize(Integer.MAX_VALUE);
		result.setTotal(CollectionUtils.isEmpty(mList) ? 0 : mList.size());
		result.setResult(mList);
		return result;
	}

	@RequiresPermissions("rolemenu:edit")
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public JsonResult edit(Long rid, @RequestParam(value = "mids[]") Long[] mids) {
		try {
			SysUser user = getLoginUser();
			List<Long> rmList = Lists.newArrayList(mids);
			operateLoger.info(new SysOperateLog(user, "角色菜单-编辑",
					String.format("RoleID:%s Menu:%s", rid, Json.toJSON(rmList))));
			menuService.saveRoleMenu(rid, rmList, user.getNo());
			return ok();
		} catch (Exception ex) {
			return error("更新发生异常,请联系管理员!");
		}
	}
}
