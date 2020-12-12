package com.echain.service.sys;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.echain.dao.sys.SysRoleDao;
import com.echain.domain.sys.SysRole;

@Service
public class SysRoleService {

	@Autowired
	private SysRoleDao sysRoleDao;

	/**
	 * 插入
	 * 
	 * @param enty
	 * @return
	 */
	public Long insert(SysRole enty) {
		int nCount = sysRoleDao.insert(enty);
		if (nCount == 1) {
			return enty.getId();
		}
		return null;
	}

	/**
	 * 更新
	 * 
	 * @param enty
	 * @return
	 */
	public int update(SysRole enty) {
		return sysRoleDao.updateById(enty);
	}

	/**
	 * 根据ID获取
	 * 
	 * @param id
	 * @return
	 */
	public SysRole get(Long id) {
		return sysRoleDao.selectById(id);
	}

	/**
	 * 查询所有
	 * 
	 * @param enty
	 * @return
	 */
	public List<SysRole> select(SysRole enty) {
		return sysRoleDao.selectList(toWrapper(enty));
	}

	/**
	 * 分页查询
	 * 
	 * @param page
	 * @param enty
	 * @return
	 */
	public IPage<SysRole> select(IPage<SysRole> page, SysRole enty) {
		return sysRoleDao.selectPage(page, toWrapper(enty));
	}

	private Wrapper<SysRole> toWrapper(SysRole enty) {
		return new QueryWrapper<SysRole>().lambda()
				// 名称
				.like(StringUtils.isNotBlank(enty.getName()), SysRole::getName, enty.getName())
				// id
				.eq(enty.getId() != null && enty.getId() > 0, SysRole::getId, enty.getId())
				// YN
				.eq(enty.getYn() != null && enty.getYn() >= 0, SysRole::getYn, enty.getYn());
	}

}
