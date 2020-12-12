package com.echain.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.echain.common.beans.JsonResult;
import com.echain.common.enums.MenuTypeEnum;
import com.echain.common.utils.Constant;
import com.echain.common.utils.RequestClientUtil;
import com.echain.common.vo.MyMenu;
import com.echain.domain.sys.SysMenu;
import com.echain.domain.sys.SysOperateLog;
import com.echain.domain.sys.SysUser;
import com.echain.service.common.DbDataCache;
import com.echain.service.common.RedisUtil;
import com.echain.web.shiro.ShiroSession;
import com.google.common.collect.Maps;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/")
public class IndexController extends AbsSupperController {


	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private DbDataCache dbDataCache;

	@RequestMapping(value = { "menu" })
	public JsonResult menu(Model model) {
		SysUser user = getLoginUser();
		List<SysMenu> menuList = dbDataCache.getRoleMenu(user.getRid());
		Map<String, List<SysMenu>> pidMenuMap = new HashMap<String, List<SysMenu>>();

		for (SysMenu m : menuList) {
			// 过滤掉button 过滤掉失效的
			if (m.getType() == null || m.getType() == MenuTypeEnum.BUTTON.getCode() || m.getYn() != 0) {
				continue;
			}
			// 如果没设置了权限编码,那么就必须要有权限
			if (m.getRid() == null || !m.getRid().equals(user.getRid())) {
				continue;
			}

			List<SysMenu> cList = pidMenuMap.get(m.getPid().toString());
			if (CollectionUtils.isEmpty(cList)) {
				cList = new ArrayList<SysMenu>();
			}
			cList.add(m);
			pidMenuMap.put(m.getPid().toString(), cList);
		}

		List<MyMenu> myMenuList = new ArrayList<MyMenu>();
		List<SysMenu> rootMenus = pidMenuMap.get("0");
		if (CollectionUtils.isNotEmpty(rootMenus)) {
			for (SysMenu m : rootMenus) {
				MyMenu myMenu = initMymenu(m, pidMenuMap);
				if (myMenu == null || (myMenu.getType().equals(MenuTypeEnum.NAVIGATION.getCode()) && CollectionUtils.isEmpty(myMenu.getList()))) {
					continue;
				}
				myMenuList.add(myMenu);
			}
		}
		return ok(myMenuList);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public JsonResult login(String userName, String userPwd, String r, String captchaCode, HttpServletRequest request) {
		if (StringUtils.isBlank(userName) || StringUtils.isBlank(userPwd) || StringUtils.isBlank(r)
				|| StringUtils.isBlank(captchaCode)) {
			return error("参数错误,请勿外站提交!");
		}
		if (r.length() > 10) {
			r = r.substring(0, 9);
		}
		if (captchaCode.length() < 4) {
			return error("验证码错误或已过期,请刷新后重试!");
		}
		String captChaCodeKey = Constant.getCaptchaCodeKey(r, captchaCode);
		String dbCaptChaCode = redisUtil.get(captChaCodeKey);
		if (!captchaCode.equals(dbCaptChaCode)) {
			return error("验证码错误或已过期,请刷新后重试!");
		}

		// 删除验证码
		redisUtil.delete(captChaCodeKey);

		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(userName, userPwd);
		try {
            subject.login(token);
        } catch (IncorrectCredentialsException e) {
            log.error("用户登录发生异常!", e);
            return error("账号或密码不正确!");
        } catch (AuthenticationException e) {
            log.error("用户登录发生异常!", e);
            return error(e.getMessage());
        } catch (Exception e) {
            log.error("用户登录发生异常!", e);
            return error("系统异常,请联系管理员!");
        }

		String ip = RequestClientUtil.getRemoteIP(request);
		SysUser sessionUser = getLoginUser();
		sessionUser.setIp(ip);
		setSession("user", sessionUser);
		operateLoger.info(new SysOperateLog(sessionUser, "用户登录", "login"));
		return ok(sessionUser);
	}

	@RequestMapping(value = "/logout")
	public JsonResult logout() {
		ShiroSession.logout();
		return ok();
	}

	@RequestMapping(value = "/session")
	public JsonResult session() {

		Map<String, Object> rstMap = Maps.newConcurrentMap();
		Map<String, Integer> promissionMap = Maps.newHashMap();
		SysUser user = getLoginUser();
		rstMap.put("user", user);
		List<SysMenu> menuList = dbDataCache.getRoleMenu(user.getRid());
		menuList.forEach(m -> {
			// 过滤掉失效的
			if (m.getType() == null || m.getYn() != 0) {
				return;
			}
			// 如果没设置了权限编码,那么就必须要有权限
			if (StringUtils.isBlank(m.getCode()) || (m.getRid() == null || !m.getRid().equals(user.getRid()))) {
				return;
			}
			promissionMap.put(m.getCode(), 1);
		});
		rstMap.put("promission", promissionMap);
		return ok(rstMap);
	}

	/**
	 * 初始化底层菜单
	 * 
	 * @param menu
	 * @param pidMenuMap
	 * @return
	 */
	private MyMenu initMymenu(SysMenu menu, Map<String, List<SysMenu>> pidMenuMap) {
		MyMenu mm = new MyMenu();
		mm.setIcon(menu.getIcon());
		mm.setTitle(menu.getText());
		mm.setJump(menu.getUrl());
		mm.setType(menu.getType());
		mm.setName(menu.getCode());

		List<SysMenu> childs = pidMenuMap.get(String.valueOf(menu.getId()));

		if (CollectionUtils.isEmpty(childs)) {
			mm.setHavsub("0");
			return mm;
		}
		mm.setHavsub("1");
		List<MyMenu> childMenu = new ArrayList<MyMenu>();
		childs.forEach(m -> {
			childMenu.add(initMymenu(m, pidMenuMap));
		});
		mm.setList(childMenu);
		return mm;
	}

}
