package com.echain.web.shiro;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.echain.common.utils.MD5Utils;
import com.echain.domain.sys.SysMenu;
import com.echain.domain.sys.SysRole;
import com.echain.domain.sys.SysUser;
import com.echain.service.common.DbDataCache;
import com.echain.service.sys.SysUserService;

public class CustomRealm extends AuthorizingRealm {

	@Resource
	private DbDataCache dbDataCache;
	
	@Resource(name = "sysUserService")
	private SysUserService userService;

	/**
	 * 授权
	 * 
	 * @param principalCollection
	 * @return
	 */
	@Override
	public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		List<String> permissionList = new ArrayList<String>();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

		// 设置角色
		SysUser user = ShiroSession.get("user", SysUser.class);
		if (user != null) {
			SysRole role = dbDataCache.getRole(user.getRid());
			if (role != null) {
				info.addRole(role.getName());
				List<SysMenu> menuList = dbDataCache.getRoleMenu(role.getId());
				if (!CollectionUtils.isEmpty(menuList)) {
					menuList.forEach(m -> {
						if (StringUtils.isNotBlank(m.getCode()) && (m.getRid() != null)
								&& m.getRid().equals(role.getId())) {
							permissionList.add(m.getCode());
						}
					});
				}
			}
		}

		info.addStringPermissions(permissionList);
		return info;
	}

	/**
	 * 认证
	 * 
	 * @param authenticationToken
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	public AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
			throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		// 获取登录账号
		String userName = token.getUsername();
		if (StringUtils.isBlank(userName)) {
			throw new AuthenticationException("账号不允许为空!");
		}

		// 查询user
		SysUser user = new SysUser();
		user.setNo(userName);

		user = userService.select(user);
		if (user == null) {
			throw new AuthenticationException("用户不存在!");
		}
		
		if(user.getYn()!=0) {
			throw new AuthenticationException("账号被禁用,请联系管理员!");
		}
		// 加密
		String pwd = MD5Utils.encryptPassword(new String(token.getPassword()), user.getSalt());
		
		String dbPwd = user.getPwd();
		token.setPassword(pwd.toCharArray());
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userName, dbPwd, this.getName());

		SysUser u = new SysUser();
		u.setNo(user.getNo());
		u.setName(user.getName());
		u.setId(user.getId());
		u.setRid(user.getRid());

		// 存入session
		ShiroSession.set("user", u);

		SysRole role = dbDataCache.getRole(user.getRid());
		if (role != null) {
			ShiroSession.set("user_role", role);
		}

		return info;
	}
}