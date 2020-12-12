package com.echain.dao.sys;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.echain.domain.sys.SysRoleMenu;

public interface SysRoleMenuDao extends BaseMapper<SysRoleMenu> {

	/**
	 * 批量插入
	 * 
	 * @param list
	 * @return
	 */
	@Insert("<script> insert into sys_role_menu (r_id,m_id,create_pin,create_time,yn) values "
			+ "  <foreach collection='list' item='item' separator=',' > "
			+ "  (#{item.rid},#{item.mid},#{item.createPin},now(),0)" + "  </foreach> </script>")
	public int batchInsert(@Param(value = "list") List<SysRoleMenu> list);

	/**
	 * 批量删除
	 * 
	 * @param roleId
	 * @param updatePin
	 * @return
	 */
	@Update("update sys_role_menu set yn=1 ,update_pin=#{updatePin},update_time=now() where r_id =#{roleId} and yn=0")
	public int deleteByRoleId(@Param(value = "roleId") Long roleId, @Param(value = "updatePin") String updatePin);
}
