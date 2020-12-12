package com.echain.dao.business.user;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.echain.domain.business.user.UserWallet;

public interface UserWalletDao extends BaseMapper<UserWallet>{

	@Update("<script>update v_user_wallet "
			+ "<set>\r\n" + 
			"			<if test=\"enty.totalAmount!= null\"><![CDATA[total_amount = total_amount + #{enty.totalAmount},]]></if>\r\n" + 
			"			<if test=\"enty.usableAmount!= null\"><![CDATA[usable_amount = usable_amount + #{enty.usableAmount},]]></if>\r\n" + 
			"			<if test=\"enty.frozenAmount!= null\"><![CDATA[frozen_amount = frozen_amount + #{enty.frozenAmount},]]></if>\r\n" +
			"			<if test=\"enty.tradePassword!= null\"><![CDATA[trade_password = #{enty.tradePassword},]]></if>\r\n" +
			"			<if test=\"enty.updatePin!= null\"><![CDATA[update_pin = #{enty.updatePin},]]></if>\r\n" +
			" </set> where id = #{enty.id} <if test=\"enty.usableAmount!= null and enty.usableAmount lt 0\"> and usable_amount >= (0-(#{enty.usableAmount}))</if> </script>")
	int updateByUserId(@Param("enty") UserWallet enty);
	
}
