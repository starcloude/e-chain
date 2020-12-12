package com.echain.web.controller.sys;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.echain.common.beans.JsonResult;
import com.echain.common.beans.PageJsonResult;
import com.echain.common.enums.MenuTypeEnum;
import com.echain.common.utils.Json;
import com.echain.domain.sys.SysMenu;
import com.echain.domain.sys.SysOperateLog;
import com.echain.domain.sys.SysUser;
import com.echain.service.sys.SysMenuService;
import com.echain.web.controller.AbsSupperController;

@RestController
@RequestMapping(value = "/api/system/menu/")
public class MenuController extends AbsSupperController {

	@Resource
	private SysMenuService menuService;

	@RequiresPermissions("menu:view")
	@RequestMapping(value = "query", method = RequestMethod.POST)
	public PageJsonResult query() {
		SysUser user = getLoginUser();
		operateLoger.info(new SysOperateLog(user, "菜单管理", "index"));

		SysMenu menu = new SysMenu();
		List<SysMenu> mList = menuService.select(menu);
		PageJsonResult result = new PageJsonResult(true);
		result.setCurrent(1);
		result.setSize(Integer.MAX_VALUE);
		result.setResult(mList);
		result.setTotal(mList == null ? 0 : mList.size());
		return result;
	}

	@RequiresPermissions("menu:edit")
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public JsonResult edit(SysMenu menu) {
		String checkRst = checkMenu(menu);
		if (StringUtils.isNotBlank(checkRst)) {
			return error(checkRst);
		}
		if (menu.getId() == null || menu.getId() <= 0) {
			return error("系统参数异常!");
		}
		try {
			SysUser user = getLoginUser();
			operateLoger.info(new SysOperateLog(user, "菜单管理 - 编辑", Json.toJSON(menu)));
			menu.setUpdatePin(user.getNo());
			menuService.update(menu);
			return ok();
		} catch (Exception ex) {
			return error("更新发生异常,请联系管理员!");
		}
	}

	@RequiresPermissions("menu:edit")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public JsonResult add(SysMenu menu) {
		String checkRst = checkMenu(menu);
		if (StringUtils.isNotBlank(checkRst)) {
			return error(checkRst);
		}
		try {
			SysUser user = getLoginUser();
			operateLoger.info(new SysOperateLog(user, "菜单管理 - 添加", Json.toJSON(menu)));
			menu.setCreatePin(user.getNo());
			menuService.insert(menu);
			return ok();
		} catch (Exception ex) {
			return error("新增发生异常,请联系管理员!");
		}
	}

	@RequiresPermissions("menu:delete")
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public JsonResult delete(Long id, Integer yn) {
		if (id == null || id <= 0 || yn == null || yn < 0) {
			return error("错误的参数!");
		}

		yn = yn == 0 ? 0 : 1;
		SysMenu menu = new SysMenu();
		menu.setId(id);
		menu.setYn(yn);

		try {
			SysUser user = getLoginUser();
			operateLoger.info(new SysOperateLog(user, "菜单管理 - 删除/恢复", Json.toJSON(menu)));
			menu.setUpdatePin(user.getNo());
			menuService.update(menu);
			return ok();
		} catch (Exception ex) {
			return error("删除发生异常,请联系管理员!");
		}
	}

	/**
	 * 数据合法性校验
	 * 
	 * @param menu
	 * @return
	 */
	private String checkMenu(SysMenu menu) {
		if (menu == null) {
			return "错误的参数!";
		}

		if (StringUtils.isBlank(menu.getText())) {
			return "菜单内容不允许为空!";
		}

		MenuTypeEnum menuType = MenuTypeEnum.get(menu.getType());
		if (menuType == null) {
			return "菜单类型无法识别!";
		}

		if (menu.getPid() == null || menu.getPid() < 0) {
			return "菜单父节点不正确!";
		}
		return null;
	}
}
