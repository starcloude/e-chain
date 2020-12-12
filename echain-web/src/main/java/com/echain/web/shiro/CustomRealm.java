package com.echain.web.shiro;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.echain.common.enums.YnEnum;
import com.echain.common.enums.business.user.UserStateEnum;
import com.echain.common.utils.MD5Utils;
import com.echain.domain.business.user.User;
import com.echain.service.business.UserService;
import com.echain.service.common.DbDataCache;

public class CustomRealm extends AuthorizingRealm {

	@Resource
	private DbDataCache dbDataCache;
	
	@Autowired
	private UserService userService;

	/**
	 * 授权
	 * 
	 * @param principalCollection
	 * @return
	 */
	@Override
	public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		return null;
//		List<String> permissionList = new ArrayList<String>();
//		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//
//		// 设置角色
//		User user = ShiroSession.get("user", User.class);
//		/*if (user != null) {
//			SysRole role = dbDataCache.getRole(user.getRid());
//			if (role != null) {
//				info.addRole(role.getName());
//				List<SysMenu> menuList = dbDataCache.getRoleMenu(role.getId());
//				if (!CollectionUtils.isEmpty(menuList)) {
//					menuList.forEach(m -> {
//						if (StringUtils.isNotBlank(m.getCode()) && (m.getRid() != null)
//								&& m.getRid().equals(role.getId())) {
//							permissionList.add(m.getCode());
//						}
//					});
//				}
//			}
//		}*/
//
//		info.addStringPermissions(permissionList);
//		return info;
	}

	/**
	 * 认证
	 * 
	 * @param authenticationToken
	 * @return
	 */
	@Override
	public AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken){
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		// 获取登录账号
		String userName = token.getUsername();
		if (StringUtils.isBlank(userName)) {
			throw new AuthenticationException("账号输入不完整!");
		}

		// 查询user
		User user = new User();
		user.setAccount(userName);

		user = userService.select(user);
		if (user == null) {
			throw new AuthenticationException("用户不存在!");
		}
		
		if(user.getState() == UserStateEnum.NO_LOGIN.getCode()) {
            throw new AuthenticationException("该用户禁止登录!");
		}
		if(user.getYn() == YnEnum.NO.getCode()) {
            throw new AuthenticationException("该用户为无效，禁止登录");
		}
		
		// 加密
		String pwd = MD5Utils.encryptPassword(new String(token.getPassword()), user.getSalt());
		
		String dbPwd = user.getPassword();
		token.setPassword(pwd.toCharArray());
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userName, dbPwd, this.getName());

//		User u = new User();
//		u.setAccount(user.getAccount());
//		u.setNickName(user.getNickName());
//		u.setId(user.getId());
//		u.setAccountType(user.getAccountType());
//		u.setInvitedId(user.getInvitedId());
//		u.setType(user.getType());
//		u.setState(user.getState());
//		u.setYn(user.getYn());

		// 存入session
		ShiroSession.set("user", user);

		return info;
	}
}