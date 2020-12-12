package com.echain.service.sys;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.echain.dao.sys.SysMenuDao;
import com.echain.dao.sys.SysRoleMenuDao;
import com.echain.domain.sys.SysMenu;
import com.echain.domain.sys.SysRoleMenu;

@Service
public class SysMenuService {

	@Autowired
	private SysMenuDao sysMenuDao;

	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;

	/**
	 * 插入
	 * 
	 * @param menu
	 * @return
	 */
	public Long insert(SysMenu menu) {
		int nCount = sysMenuDao.insert(menu);
		if (nCount == 1) {
			return menu.getId();
		}
		return null;
	}

	/**
	 * 更新
	 * 
	 * @param menu
	 * @return
	 */
	public int update(SysMenu menu) {
		return sysMenuDao.updateById(menu);
	}

	/**
	 * 根据ID获取
	 * 
	 * @param id
	 * @return
	 */
	public SysMenu get(Long id) {
		return sysMenuDao.selectById(id);
	}

	/**
	 * 查询所有
	 * 
	 * @param menu
	 * @return
	 */
	public List<SysMenu> select(SysMenu menu) {
		return sysMenuDao.selectList(toWrapper(menu));
	}

	/**
	 * 分页查询
	 * 
	 * @param page
	 * @param menu
	 * @return
	 */
	public IPage<SysMenu> select(IPage<SysMenu> page, SysMenu menu) {
		return sysMenuDao.selectPage(page, toWrapper(menu));
	}

	/**
	 * 根据角色获取所有的菜单信息
	 * 
	 * @param roleId
	 * @return
	 */
	public List<SysMenu> selectByRoleId(Long roleId) {
		return sysMenuDao.selectMenuByRoleId(roleId);
	}

	/**
	 * 保存角色权限
	 * 
	 * @param roleId
	 * @param mids
	 * @param operatePin
	 * @return
	 */
	@Transactional
	public int saveRoleMenu(Long roleId, List<Long> mids, String operatePin) {
		// 删除所有
		sysRoleMenuDao.deleteByRoleId(roleId, operatePin);

		// 保存
		if (CollectionUtils.isEmpty(mids)) {
			return 0;
		}
		List<SysRoleMenu> list = mids.stream().map(m -> {
			SysRoleMenu r = new SysRoleMenu();
			r.setMid(m);
			r.setCreatePin(operatePin);
			r.setRid(roleId);
			return r;
		}).collect(Collectors.toList());
		return sysRoleMenuDao.batchInsert(list);
	}

	/**
	 * 类型转换
	 * 
	 * @param enty
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Wrapper<SysMenu> toWrapper(SysMenu enty) {
		return new QueryWrapper<SysMenu>().lambda()
			// 类型
			.eq(enty.getType() != null && enty.getType() >= 0, SysMenu::getType, enty.getType())

			// id
			.eq(enty.getId() != null && enty.getId() > 0, SysMenu::getId, enty.getId())

			// 上级ID
			.eq(enty.getPid() != null && enty.getPid() > 0, SysMenu::getPid, enty.getPid())

			// YN
			.eq(enty.getYn() != null && enty.getYn() >= 0, SysMenu::getYn, enty.getYn())
			.orderByAsc(SysMenu::getPid, SysMenu::getIdx, SysMenu::getId);
		
	}
}
