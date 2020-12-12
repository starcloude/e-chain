package com.echain.service.sys;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.echain.dao.sys.SysUserDao;
import com.echain.domain.sys.SysUser;

@Service
public class SysUserService {

	@Autowired
	private SysUserDao sysUserDao;

	/**
	 * 根据User对象获取User对象
	 * 
	 * @param user
	 * @return
	 */
	public SysUser select(SysUser user) {
		return sysUserDao.selectOne(toWrapper(user));
	}

	/**
	 * 查询用户列表
	 * 
	 * @param user
	 * @return
	 */
	public List<SysUser> selectList(SysUser user) {
		return sysUserDao.selectList(toWrapper(user));
	}

	/**
	 * 查询总数
	 * 
	 * @param user
	 * @return
	 */
	public Integer selectCount(SysUser user) {
		return sysUserDao.selectCount(toWrapper(user));
	}

	/**
	 * 分页查询
	 * 
	 * @param page
	 * @param user
	 * @return
	 */
	public IPage<SysUser> selectUsers(IPage<SysUser> page, SysUser user) {
		return sysUserDao.selectPage(page, toWrapper(user));
	}

	/**
	 * 根据主键ID获取对象
	 * 
	 * @param id
	 * @return
	 */
	public SysUser select(Long id) {
		return sysUserDao.selectById(id);
	}

	/**
	 * 插入User对象
	 * 
	 * @param user
	 * @return
	 */
	public Long add(SysUser user) {
		int nCount = sysUserDao.insert(user);
		if (nCount != 0) {
			return user.getId();
		}
		return null;
	}

	/**
	 * 根据ID修改
	 * 
	 * @param user
	 * @return
	 */
	public int updateById(SysUser user) {
		return sysUserDao.updateById(user);
	}

	/**
	 * 类型转换
	 * 
	 * @return
	 */
	public Wrapper<SysUser> toWrapper(SysUser enty) {
		return new QueryWrapper<SysUser>().lambda()
				// 账号
				.eq(StringUtils.isNotBlank(enty.getNo()), SysUser::getNo, enty.getNo())

				// 名称
				.like(StringUtils.isNotBlank(enty.getName()), SysUser::getName, enty.getName())

				// id
				.eq(enty.getId() != null && enty.getId() > 0, SysUser::getId, enty.getId())

				// 密码
				.eq(StringUtils.isNotBlank(enty.getPwd()), SysUser::getPwd, enty.getPwd())

				// 角色ID
				.eq(enty.getRid() != null && enty.getRid() > 0, SysUser::getRid, enty.getRid())

				// YN
				.eq(enty.getYn() != null && enty.getYn() >= 0, SysUser::getYn, enty.getYn());
	
	}

}
