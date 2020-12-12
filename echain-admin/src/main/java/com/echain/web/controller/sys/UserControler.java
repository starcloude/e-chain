package com.echain.web.controller.sys;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomStringUtils;
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
import com.echain.common.utils.MD5Utils;
import com.echain.domain.sys.SysOperateLog;
import com.echain.domain.sys.SysRole;
import com.echain.domain.sys.SysUser;
import com.echain.service.common.DbDataCache;
import com.echain.service.sys.SysRoleService;
import com.echain.service.sys.SysUserService;
import com.echain.web.controller.AbsSupperController;

@RestController
@RequestMapping(value = "/api/system/user/")
public class UserControler extends AbsSupperController {

//	@Qualifier("sysUserService")
	@Resource(name = "sysUserService")
	private SysUserService userService;

	@Resource
	private DbDataCache dbDataCache;

	@Resource
	private SysRoleService roleService;

	@RequiresPermissions("user:view")
	@RequestMapping(value = "query", method = RequestMethod.POST)
	public PageJsonResult query(SysUser user,PageQuery pageQuery) {
		if (user == null) {
			user = new SysUser();
		}

		SysUser sessionUser = getLoginUser();
		operateLoger.info(new SysOperateLog(sessionUser, "用户管理", "index"));

		PageJsonResult result = new PageJsonResult(true);
		result.setCurrent(pageQuery.getPageNo());
		result.setSize(pageQuery.getPageSize());

		IPage<SysUser> page = new Page<SysUser>(pageQuery.getPageNo(), pageQuery.getPageSize());
		page = userService.selectUsers(page, user);
		return toPageJson(page);
	}

	@RequiresPermissions("user:edit")
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public JsonResult edit(SysUser user) {
		String checkRst = check(user);
		if (StringUtils.isNotBlank(checkRst)) {
			return error(checkRst);
		}
		if (user.getId() == null || user.getId() <= 0) {
			return error("系统参数异常!");
		}
		try {

			String pwd = user.getPwd();
			// 如果密码不为空,那么就需要根据salt 重新加密生成加密密码
			if (StringUtils.isNotBlank(pwd)) {
				if (pwd.length() > 30) {
					return error("密码超过30个字符!");
				}
				SysUser dbUser = userService.select(user.getId());
				pwd = MD5Utils.encryptPassword(pwd, dbUser.getSalt());
			} else {
				pwd = null;
			}

			// 封装信息
			SysUser dbUser = new SysUser();
			dbUser.setId(user.getId());
			dbUser.setPwd(pwd);
			dbUser.setRid(user.getRid());
			dbUser.setName(user.getName());
			
			// 取session
			SysUser sessionUser = getLoginUser();
			operateLoger.info(new SysOperateLog(sessionUser, "用户管理-编辑", Json.toJSON(dbUser)));
			dbUser.setUpdatePin(sessionUser.getNo());
			if (sessionUser.getId() == dbUser.getId()) {
				sessionUser.setName(user.getName());
			}
			// 执行修改
			userService.updateById(dbUser);
			return ok();
		} catch (Exception ex) {
			return error("更新发生异常,请联系管理员!");
		}
	}

	@RequiresPermissions("user:add")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public JsonResult add(SysUser user) {
		String checkRst = check(user);
		if (StringUtils.isNotBlank(checkRst)) {
			return error(checkRst);
		}

		if (StringUtils.isBlank(user.getPwd()) || user.getPwd().length() > 30) {
			return error("密码为空或者超过30个字符!!");
		}

		try {
			SysUser query = new SysUser();
			query.setNo(user.getNo());
			Integer nCount = userService.selectCount(query);
			if (nCount != null && nCount > 0) {
				return error("用户名:[" + user.getNo() + "]已经存在了!");
			}

			SysUser sessionUser = getLoginUser();
			operateLoger.info(new SysOperateLog(sessionUser, "用户管理-添加用户", Json.toJSON(user)));

			user.setSalt(RandomStringUtils.randomNumeric(8));
			String pwd = MD5Utils.encryptPassword(user.getPwd(), user.getSalt());
			user.setPwd(pwd);
			user.setCreatePin(sessionUser.getNo());
			userService.add(user);
			return ok();
		} catch (Exception ex) {
			return error("新增发生异常,请联系管理员!");
		}
	}

	@RequiresPermissions("user:delete")
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public JsonResult delete(Long id, Integer yn) {
		if (id == null || id <= 0 || yn == null || yn < 0) {
			return error("错误的参数!");
		}
		yn = yn == 0 ? 0 : 1;
		SysUser user = new SysUser();
		user.setId(id);
		user.setYn(yn);
		try {
			SysUser sessionUser = getLoginUser();
			operateLoger.info(new SysOperateLog(sessionUser, "用户管理-删除", Json.toJSON(user)));
			user.setUpdatePin(sessionUser.getNo());
			userService.updateById(user);
			return ok();
		} catch (Exception ex) {
			return error("删除发生异常,请联系管理员!");
		}
	}

	@RequestMapping(value = "modify", method = RequestMethod.POST)
	public JsonResult modify(String name, String pwd1, String pwd2) {

		boolean changePwd = StringUtils.isNotBlank(pwd1);

		// 密码校验
		if (changePwd && !pwd1.equals(pwd2)) {
			return error("输入的2次密码不一致,请确认!");
		}
		if (changePwd && pwd1.length() > 30) {
			return error("密码不允许超过30个字符!!");
		}

		// 名字合法校验
		if (StringUtils.isBlank(name) || name.length() > 10) {
			return error("名字为空或者超过10个字符!");
		}

		try {
			// 取session
			SysUser sessionUser = getLoginUser();

			// 封装信息
			SysUser dbUser = new SysUser();
			dbUser.setId(sessionUser.getId());
			dbUser.setName(name);
			sessionUser.setName(name);
			operateLoger.info(new SysOperateLog(sessionUser, "修改用户-个人信息", Json.toJSON(dbUser)));
			if (changePwd) {
				pwd1 = MD5Utils.encryptPassword(pwd1, sessionUser.getSalt());
				dbUser.setPwd(pwd1);
			}
			// 刷新session
			setSession("user", sessionUser);
			dbUser.setUpdatePin(sessionUser.getNo());
			// 执行修改
			userService.updateById(dbUser);
			return ok();
		} catch (Exception ex) {
			return error("更新发生异常,请联系管理员!");
		}
	}

	/**
	 * 数据合法性校验
	 * 
	 * @param menu
	 * @return
	 */
	private String check(SysUser user) {
		if (user == null) {
			return "错误的参数!";
		}

		if (StringUtils.isBlank(user.getNo()) || user.getNo().length() > 20) {
			return "账号为空或者超过20个字符!";
		}

		if (StringUtils.isBlank(user.getName()) || user.getName().length() > 50) {
			return "名字为空或者超过50个字符!";
		}

		if (user.getRid() == null || user.getRid() <= 0) {
			return "请指定角色!";
		}
		
		SysRole role = dbDataCache.getRole(user.getRid());
		if (role == null) {
			return "无法识别的角色信息!";
		}
		return null;
	}
}
