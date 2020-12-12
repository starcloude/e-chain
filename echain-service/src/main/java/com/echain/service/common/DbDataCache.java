package com.echain.service.common;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.echain.domain.business.user.User;
import com.echain.domain.sys.SysMenu;
import com.echain.domain.sys.SysRole;
import com.echain.domain.sys.SysUser;
import com.echain.service.business.UserService;
import com.echain.service.sys.SysMenuService;
import com.echain.service.sys.SysRoleService;
import com.echain.service.sys.SysUserService;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("dbDataCache")
public class DbDataCache {

	@Autowired
	private SysRoleService roleService;

	@Autowired
	private SysMenuService menuService;

	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private UserService userService;
	
	private LoadingCache<Long, SysRole> roleCache = CacheBuilder.newBuilder().maximumSize(1000)
			.expireAfterWrite(120, TimeUnit.MINUTES).build(new CacheLoader<Long, SysRole>() {
				public SysRole load(Long id) {
					try {
						return roleService.get(id);
					} catch (Exception ex) {
						log.error("Role.缓存 发生异常! ", ex);
						return null;
					}
				}
			});


	private LoadingCache<Long, List<SysMenu>> menuCache = CacheBuilder.newBuilder().maximumSize(1000)
			.expireAfterWrite(120, TimeUnit.MINUTES).build(new CacheLoader<Long, List<SysMenu>>() {
				public List<SysMenu> load(Long rid) {
					try {
						return menuService.selectByRoleId(rid);
					} catch (Exception ex) {
						log.error("SysMenu.缓存 发生异常! ", ex);
						return null;
					}
				}
			});

	/**
	 * User Cache
	 */
	private LoadingCache<Long, SysUser> userCache = CacheBuilder.newBuilder().maximumSize(1000)
			.expireAfterWrite(120, TimeUnit.MINUTES).build(new CacheLoader<Long, SysUser>() {
				public SysUser load(Long id) {
					try {
						return sysUserService.select(id);
					} catch (Exception ex) {
						log.error("SysUser.缓存 发生异常! ", ex);
						return null;
					}
				}
			});
	
	
	private LoadingCache<Long, User> vUserCache = CacheBuilder.newBuilder().maximumSize(1000)
			.expireAfterWrite(120, TimeUnit.MINUTES).build(new CacheLoader<Long, User>() {
				public User load(Long id) {
					try {
						return userService.get(id);
					} catch (Exception ex) {
						log.error("SysUser.缓存 发生异常! ", ex);
						return null;
					}
				}
			});
	
	

	/**
	 * 根据角色ID提取角色信息
	 * 
	 * @param id
	 * @return
	 */
	public SysRole getRole(Long id) {
		try {
			return roleCache.get(id);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 根据角色提取权限菜单
	 * 
	 * @param rid
	 * @return
	 */
	public List<SysMenu> getRoleMenu(Long rid) {
		try {
			return menuCache.get(rid);
		} catch (Exception e) {
			return null;
		}
	}


	/**
	 * 根据用户ID提取缓存信息
	 * 
	 * @param id
	 * @return
	 */
	public SysUser getUser(Long id) {
		try {
			return userCache.get(id);
		} catch (Exception ex) {
			log.error("提取用户缓存发生异常!");
			return null;
		}
	}
	
	public User getVuser(Long id) {
		try {
			return vUserCache.get(id);
		} catch (Exception ex) {
			log.error("提取Vuser缓存发生异常!");
			return null;
		}
	}
}
