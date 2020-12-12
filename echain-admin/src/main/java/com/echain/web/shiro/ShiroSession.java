package com.echain.web.shiro;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.echain.common.utils.Json;

public class ShiroSession {

	/**
	 * 将一些数据放到ShiroSession中,以便于其它地方使用
	 * 比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到
	 */
	public static void set(String key, Object value) {
		Session session = getSession();
		if (null != session) {
			session.setAttribute(key, Json.toJSON(value));
		}
	}

	/**
	 * 获取session 值
	 * 
	 * @param key
	 * @return
	 */
	public static String get(String key) {
		Session session = getSession();
		if (null != session) {
			Object sessionValue = session.getAttribute(key);
			if (sessionValue != null) {
				return sessionValue.toString();
			}
		}
		return null;
	}

	/**
	 * 获取session对象
	 * 
	 * @param     <T>
	 * @param key
	 * @param cls
	 * @return
	 */
	public static <T> T get(String key, Class<T> cls) {
		String sessionValue = get(key);
		if (StringUtils.isEmpty(sessionValue)) {
			return null;
		}
		return Json.parse(sessionValue, cls);
	}

	/**
	 * 获取session对象
	 * 
	 * @return
	 */
	private static Session getSession() {
		Subject currentUser = getCurrentUser();
		if (null != currentUser) {
			return currentUser.getSession();
		}
		return null;
	}
	
	/**
	 * 获取当前登录的用户信息
	 * @return
	 */
	public static Subject getCurrentUser() {
		return SecurityUtils.getSubject();
	}

	/**
	 * 退出
	 */
	public static void logout() {
		getCurrentUser().logout();
	}
	
	/**
	 * 是否有权限
	 * @param permissions
	 * @return
	 */
	public static boolean[] hasPermission(String ... permissions) {
		return getCurrentUser().isPermitted(permissions);
	}
}
