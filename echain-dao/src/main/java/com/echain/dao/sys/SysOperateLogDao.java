package com.echain.dao.sys;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.echain.domain.sys.SysOperateLog;

public interface SysOperateLogDao extends BaseMapper<SysOperateLog> {

	/**
	 * 批量插入
	 * 
	 * @param list
	 * @return
	 */
	@Insert("<script> insert into sys_operate_log (level,node,memo,ip,create_pin,create_time) values "
			+ "  <foreach collection='list' item='item' separator=',' > "
			+ "  (#{item.level},#{item.node},#{item.memo},#{item.ip},#{item.createPin}, <if test='item.createTime == null'>now()</if> <if test='item.createTime != null'>#{item.createTime}</if>)"
			+ "  </foreach> </script>")
	public int batchInsert(@Param(value = "list") List<SysOperateLog> list);
}
