package com.echain.dao.business.user;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.echain.domain.business.user.UserMachine;

public interface UserMachineDao extends BaseMapper<UserMachine>{

	@Update("<script>update v_user_machine set children_count = IFNULL(children_count,0)+1 where id = #{enty.parentMachineId}</script>")
	int updateChildrenCount(@Param("enty") UserMachine enty);
	
}
