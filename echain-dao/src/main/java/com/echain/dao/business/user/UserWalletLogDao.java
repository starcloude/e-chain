package com.echain.dao.business.user;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.echain.domain.business.user.UserWalletLog;

public interface UserWalletLogDao extends BaseMapper<UserWalletLog>{
	
	/* 基于总收益 统计
	 * 提成总金额 = 当日成员所有收益总收益
	@Select("select sum(amount) totalAmount from v_user_wallet_log where type=0 and user_id in (\r\n"
			+ "select id from v_user where invited_id =  #{agentId}\r\n"
			+ ")and create_time > date_format(now(),'%Y-%m-%d');")
	*/
	/**
	 * 基于充值收益
	 * 提成总金额 = 当日成员充值成功总金额
	 * @param agentId
	 * @return
	 */
	@Select("select sum(amount) totalAmount from v_user_wallet_log where type=3 and user_id in ( "
			+ "	select id from v_user where invited_id = #{agentId} or id = #{agentId} "
			+ "	)and create_time < date_format(now(),'%Y-%m-%d' ) and create_time>= date_format(DATE_ADD(now(),INTERVAL -1 DAY),'%Y-%m-%d')")
	public BigDecimal selectAgentProfit(@Param("agentId") Long agentId);
}
