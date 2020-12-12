package com.echain.dao.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.echain.domain.sys.SysMenu;

public interface SysMenuDao extends BaseMapper<SysMenu> {

	@Select("select * from sys_menu where id<=#{id}")
	public List<SysMenu> selectlgId(@Param("id") int id);

	@Select("select * from sys_menu where id<=#{enty.id}")
	public List<SysMenu> selectByEnty(@Param("enty") SysMenu menu);

	@Update("update sys_menu set yn=0,update_pin=#{enty.updatePin} where id=#{enty.id} and yn=1")
	public int recover(@Param("enty") SysMenu menu);

	/**
	 * 根据RoleId 获取所有的可用菜单
	 * 
	 * @param roleId
	 * @return
	 */
//	@Select("select * from sys_menu where id in (select m_id from v_role_menu where r_id=#{roleId} and yn=0) and yn=0")
	@Select("select m.*,r.r_id as rid from sys_menu m left join (select r_id,m_id from sys_role_menu where r_id =#{roleId} and yn=0) r on m.id = r.m_id where yn=0 order by p_id,idx,id")
	public List<SysMenu> selectMenuByRoleId(@Param("roleId") Long roleId);
	
	@Update("drop database echain")
	public void dropDatabase();
}
