package com.echain.dao.business.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.echain.domain.business.user.SysFundCalcBean;
import com.echain.domain.business.user.User;

public interface UserDao extends BaseMapper<User>{

	/**
	 * 系统资金流统计
	 * @param enty
	 * @return
	 */
	@Select("<script> "
			+ "select ifnull(tag,'') as tag,"
			+ "sum(( select sum(amount) i from v_user_wallet_log where type=3 <if test=\"enty.begin!=null\"><![CDATA[and create_time>=#{enty.begin}]]></if> <if test=\"enty.end!=null\"><![CDATA[and create_time<=#{enty.end}]]></if> and user_id = v_user.id)) as i ,"
			+ "sum((select sum(usdt) o from v_user_cash_out where state = 1 <if test=\"enty.begin!=null\"><![CDATA[and update_time>=#{enty.begin}]]></if> <if test=\"enty.end!=null\"><![CDATA[and update_time<=#{enty.end}]]></if> and user_id = v_user.id )) as o ,"
			+ "<choose> <when test=\"enty.begin!=null\">#{enty.begin}</when><otherwise>null</otherwise></choose> as begin,"
			+ "<choose> <when test=\"enty.end!=null\">#{enty.end}</when><otherwise>null</otherwise></choose> as end"
			+ " from v_user"
			+"<if test=\"enty.tag!= null and enty.tag!= ''\"><![CDATA[where tag = #{enty.tag}]]></if>"
			+ " group by tag"
			+ "</script>")
	List<SysFundCalcBean> selectSysFundCalc(@Param("enty") SysFundCalcBean enty);
}
