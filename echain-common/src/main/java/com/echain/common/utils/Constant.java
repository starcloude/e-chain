package com.echain.common.utils;

import java.time.LocalDate;

public class Constant {

	/**
	 * 验证码key
	 */
	public static String getCaptchaCodeKey(String location,String code) {
		return String.format("CAPTCHA_%s_%s_KEY", location,code);
	}
	
	/**
	 *  用户余额KEY
	 * @param userId
	 * @return
	 */
	public static String getUserAmountKey(Long userId) {
		return String.format("user_amount_%s", userId);
	}
	
	/**
	 * USDT汇率
	 */
	public static String getUsdtExchangeRate() {
		return "usdt_exchange_rate";
	}
	
	/**
	 *  矿机下级上限
	 */
	public static String getLimitChildrenCount() {
		return "limit_children_count";
	}
	
	/**
	 *  团队长提成比例
	 */
	public static String getTeamLeaderCommissionRate() {
		return "team_leader_commission_rate";
	}
	
	/**
	 *  最小划转数量
	 */
	public static String getMinTransferCount() {
		return "min_transfer_count";
	}
	
	/**
	 *  划转手续费率
	 */
	public static String getTransferRate() {
		return "transfer_rate";
	}
	
	/**
	 *  划转手续费下限
	 */
	public static String getMinTransferAmount() {
		return "min_transfer_amount";
	}
	
	/**
	 *  划转手续费上限
	 */
	public static String getMaxTransferAmount() {
		return "max_transfer_amount";
	}
	
	/**
	 *  最小提现数量
	 */
	public static String getMinCashOutCount() {
		return "min_cash_out_count";
	}
	
	/**
	 * 提现手续费率
	 */
	public static String getCashOutRate() {
		return "cash_out_rate";
	}
	
	/**
	 * 提现手续费下限
	 */
	public static String getMinCashOutAmount() {
		return "min_cash_out_amount";
	}
	
	/**
	 * 提现手续费上限
	 */
	public static String getMaxCashOutAmount() {
		return "max_cash_out_amount";
	}
	
	/**
	 * 收币钱包-ERC20
	 */
	public static String getAddressByERC20() {
		return "address_by_erc20";
	}
	
	/**
	 * 收币钱包-TRC20
	 */
	public static String getAddressByTRC20() {
		return "address_by_trc20";
	}
	
	/**
	 * 收币钱包-OMNI
	 */
	public static String getAddressByOMNI() {
		return "address_by_omni";
	}
	
	/**
	 * 每日矿机收益KEY
	 * @param userId
	 * @return
	 */
	public static String getUserMachineDailyProfit(Long userId) {
		return String.format("user_machine_daily_profit_%s_%s", userId,LocalDate.now().toString());
	}
	
	/**
	 * 用户矿机累计收益
	 * @param userId
	 * @return
	 */
	public static String getUserMachineProfit(Long userId) {
		return String.format("user_machine_profit_%s", userId);
	}
	
	/**
	 * 每日代理收益
	 * @param userId
	 * @return
	 */
	public static String getAgentDailyProfit(Long userId) {
		return String.format("agent_daily_profit_%s_%s", userId,LocalDate.now().toString());
	}
	/**
	 * 代理累计收益
	 * @param userId
	 * @return
	 */
	public static String getAgentProfit(Long userId) {
		return String.format("agent_profit_%s", userId);
	}
	
	/**
	 * 每日返还奖励
	 * @param userId
	 * @return
	 */
	public static String getMachineDailyReturn(Long userId) {
		return String.format("machine_daily_return_%s_%s", userId,LocalDate.now().toString());
	}
	
	/**
	 * 返还奖励累计
	 * @param userId
	 * @return
	 */
	public static String getMachineReturn(Long userId) {
		return String.format("machine_return_%s", userId);
	}
	
	/**
	 * 是否返还过矿机购买金币
	 * 是否满过7台
	 * @param userId
	 * @return
	 */
	public static String isMachineReturned(Long userId) {
		return String.format("machine_returned_%s", userId);
	}
	
	/**
	 * 冻结金额的rediskey
	 * @param userId
	 * @return
	 */
	public static String frozenCashOut(Long userId) {
		return String.format("frozen_cash_out_%s", userId);
	}
	
	/**
	 * 网站系统的总开关
	 * @return
	 */
	public static String getWebSiteTurn() {
		return "sys_website_turn";
	}
}
