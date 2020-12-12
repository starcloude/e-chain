package com.echain.web.controller;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.echain.common.beans.JsonResult;
import com.echain.common.beans.PageJsonResult;
import com.echain.common.beans.PageQuery;
import com.echain.common.enums.YnEnum;
import com.echain.common.enums.business.user.UserCashOutStateEnum;
import com.echain.common.enums.business.user.UserMachineStateEnum;
import com.echain.common.enums.business.user.UserRechargeStateEnum;
import com.echain.common.enums.business.user.UserStateEnum;
import com.echain.common.enums.business.user.UserTypeEnum;
import com.echain.common.utils.Constant;
import com.echain.common.utils.MD5Utils;
import com.echain.domain.business.ChatMsg;
import com.echain.domain.business.Machine;
import com.echain.domain.business.user.User;
import com.echain.domain.business.user.UserCashOut;
import com.echain.domain.business.user.UserMachine;
import com.echain.domain.business.user.UserRecharge;
import com.echain.domain.business.user.UserTransfer;
import com.echain.domain.business.user.UserUSDTWallet;
import com.echain.domain.business.user.UserWallet;
import com.echain.domain.business.user.UserWalletLog;
import com.echain.service.business.ChatMsgService;
import com.echain.service.business.MachineService;
import com.echain.service.business.UserCashOutService;
import com.echain.service.business.UserMachineService;
import com.echain.service.business.UserRechargeService;
import com.echain.service.business.UserService;
import com.echain.service.business.UserTransferService;
import com.echain.service.business.UserUsdtWalletService;
import com.echain.service.business.UserWalletService;
import com.echain.service.common.RedisUtil;

@RestController
@RequestMapping("/api/business/")
public class BusinessController extends AbsSupperController {
	@Autowired
	private RedisUtil redisUtil;
	
	@Resource
	private UserService userService;
	
	@Resource
	private UserWalletService userWalletService;
	
	@Resource
	private UserUsdtWalletService userUsdtWalletService;
	
	@Resource
	private UserRechargeService userRechargeService;
	
	@Resource
	private UserTransferService userTransferService;
	
	@Resource
	private UserCashOutService userCashOutService;
	
	@Resource
	private UserMachineService userMachineService;
	
	@Resource
	private MachineService machineService;
	
	@Resource
	private ChatMsgService chatMsgService;
	
	//维护钱包地址
	@RequestMapping(value = "usdtWallet/save", method = RequestMethod.POST)
	public JsonResult usdtWalletSave(UserUSDTWallet enty, String tradePassword) {
		JsonResult rst = checkUsdtWallet(enty);
		if(!rst.isSuccess()) {
			return rst;
		}
		if (StringUtils.isBlank(tradePassword)) {
			return error("请输入交易密码!");
		}
		enty.setUserId(getLoginUser().getId());
		//验证交易密码
		UserWallet userWallet = userWalletService.getByUserId(enty.getUserId());
		if(userWallet == null || StringUtils.isBlank(userWallet.getTradePassword())) {
			return error("未设置交易密码，请先设置!");
		}
		
		tradePassword = MD5Utils.encryptPassword(tradePassword, userWallet.getSalt());
		if(!tradePassword.equals(userWallet.getTradePassword())) {
			return error("交易密码不正确,交易中断!");
		}
		UserUSDTWallet userUSDTWallet = userUsdtWalletService.getByUserId(enty);
		Long keyId = null;
		if(userUSDTWallet == null) {
			keyId = userUsdtWalletService.add(enty);
		}else {
			enty.setId(userUSDTWallet.getId());
			keyId = userUsdtWalletService.update(enty);
		}
		return ok(keyId);
	}
		
	//获取钱包地址
	@RequestMapping(value = "usdtWallet/get", method = RequestMethod.GET)
	public JsonResult usdtWalletGet(UserUSDTWallet enty) {
		enty.setUserId(getLoginUser().getId());
		List<UserUSDTWallet> resList = userUsdtWalletService.select(enty);
		return ok(resList);
	}
	
	//充值
	@RequestMapping(value = "recharge/add", method = RequestMethod.POST)
	public JsonResult rechargeAdd(UserRecharge enty) {
		JsonResult rst = checkRecharge(enty);
		if(!rst.isSuccess()) {
			return rst;
		}
		//获取当前USDT汇率
		String usdtExchangeRate = redisUtil.get(Constant.getUsdtExchangeRate());
		if(StringUtils.isBlank(usdtExchangeRate)) {
			enty.setAmount(enty.getUsdt());
		}else {
			//价格=数量*USDT汇率
			enty.setAmount(enty.getUsdt().multiply(new BigDecimal(usdtExchangeRate)));
		}
		enty.setUserId(getLoginUser().getId());
		enty.setState(UserRechargeStateEnum.INIT.getCode());//充值中
		userRechargeService.insert(enty);
		return ok(enty);
	}
	
	//查询充值记录
	@RequestMapping(value = "recharge/query", method = RequestMethod.POST)
	public PageJsonResult rechargeQuery(UserRecharge enty, PageQuery pageQuery) {
		enty.setUserId(getLoginUser().getId());
		IPage<UserRecharge> page = new Page<UserRecharge>(pageQuery.getPageNo(), pageQuery.getPageSize());
		page = userRechargeService.select(page, enty);
		return toPageJson(page);
	}
	
	//获取充值记录详情
	@GetMapping("recharge/{id}")
	public JsonResult rechargeGet(@PathVariable Long id) {
		if (StringUtils.isBlank(id.toString())) {
			return error("错误的参数");
		}
		UserRecharge enty = userRechargeService.get(id);
		if(enty==null ||!enty.getUserId().equals(getLoginUser().getId())) {
			return error("找不到充值信息!");
		}
		return ok(enty);
	}
	
	//累计充值金额
	@RequestMapping(value = "recharge/sumAmountByUserId", method = RequestMethod.POST)
	public JsonResult sumAmountByRecharge(UserRecharge enty) {
		enty.setUserId(getLoginUser().getId());
		enty.setState(UserRechargeStateEnum.SUCCESS.getCode());
		List<UserRecharge> list = userRechargeService.select(enty);
		BigDecimal sunAmount = BigDecimal.ZERO;
		for(UserRecharge logVO : list) {
			sunAmount = sunAmount.add(logVO.getUsdt());
		}
		return ok(sunAmount);
	}
	
	//划转
	@RequestMapping(value = "transfer/add", method = RequestMethod.POST)
	public JsonResult transferAdd(UserTransfer enty, String tradePassword) {
		JsonResult rst = checkTransfer(enty);
		if(!rst.isSuccess()) {
			return rst;
		}
		
		if (StringUtils.isBlank(tradePassword)) {
			return error("请输入交易密码!");
		}
		
		User dbUser = getLoginUser();
		if(dbUser == null) {
			return error("用户未登录!");
		}
		
		//验证交易密码
		UserWallet userWallet = userWalletService.getByUserId(dbUser.getId());
		if(userWallet == null || StringUtils.isBlank(userWallet.getTradePassword())) {
			return error("未设置交易密码，请先设置!");
		}
		
		tradePassword = MD5Utils.encryptPassword(tradePassword, userWallet.getSalt());
		if(!tradePassword.equals(userWallet.getTradePassword())) {
			return error("交易密码不正确,交易中断!");
		}
		
		//填充用户id 和账号
		enty.setCreatePin(dbUser.getAccount());
		enty.setUserId(dbUser.getId());
		
		//校验并填充收款账号
		User dfUser = new User();
		dfUser.setAccount(enty.getToAccount());
		dfUser = userService.select(dfUser);
		if(dfUser == null) {
			return error("对方账号不存在!");
		}
		enty.setToUserId(dfUser.getId());
		
		//执行转账
		userTransferService.doTrans(enty,userWallet);
		return ok(enty);
	}
	
	//查询划转记录
	@RequestMapping(value = "transfer/query", method = RequestMethod.POST)
	public PageJsonResult transferQuery(UserTransfer enty, PageQuery pageQuery) {
		enty.setUserId(getLoginUser().getId());
		IPage<UserTransfer> page = new Page<UserTransfer>(pageQuery.getPageNo(), pageQuery.getPageSize());
		page = userTransferService.select(page, enty);
		return toPageJson(page);
	}
	
	//获取划转记录详情
	@GetMapping("transfer/{id}")
	public JsonResult transferGet(@PathVariable Long id) {
		if (StringUtils.isBlank(id.toString())) {
			return error("错误的参数");
		}
		User dbUser = getLoginUser();
		if(dbUser == null) {
			return error("用户未登录!");
		}
		UserTransfer enty = userTransferService.get(id);
		if(enty == null || (!enty.getUserId().equals(dbUser.getId()) && !enty.getToUserId().equals(dbUser.getId()))) {
			return error("数据不存在，非法查询");
		}
		return ok(enty);
	}
	
	//提现
	@RequestMapping(value = "cashOut/add", method = RequestMethod.POST)
	public JsonResult cashOutAdd(UserCashOut enty) {
		JsonResult rst = checkCashOut(enty);
		if(!rst.isSuccess()) {
			return rst;
		}
		//获取当前USDT汇率
		String usdtExchangeRate = redisUtil.get(Constant.getUsdtExchangeRate());
		if(StringUtils.isBlank(usdtExchangeRate)) {
			enty.setAmount(enty.getUsdt());
		}else {
			//价格=数量*USDT汇率
			enty.setAmount(enty.getUsdt().multiply(new BigDecimal(usdtExchangeRate)));
		}
						
		User me = getLoginUser();
		if(me.getState() == UserStateEnum.NO_CASHOUT.getCode()) {
			return error("该用户状态为禁止提现，请联系客服人员!");
		}
		//查询用户余额
		UserWallet userWallet = userWalletService.getByUserId(me.getId());
		if(userWallet == null) {
			return error("未查询到用户余额信息!!");
		}
		
		enty.setUserId(me.getId());
		enty.setState(UserCashOutStateEnum.INIT.getCode());//提现中
		userCashOutService.insert(enty, userWallet);
		return ok(enty);
	}
	
	//查询提现记录
	@RequestMapping(value = "cashOut/query", method = RequestMethod.POST)
	public JsonResult cashOutQuery(UserCashOut enty, PageQuery pageQuery) {
		enty.setUserId(getLoginUser().getId());
		IPage<UserCashOut> page = new Page<UserCashOut>(pageQuery.getPageNo(), pageQuery.getPageSize());
		page = userCashOutService.select(page, enty);
		return toPageJson(page);
	}
	
	//获取提现记录详情
	@GetMapping("cashOut/{id}")
	public JsonResult cashOutGet(@PathVariable Long id) {
		if (StringUtils.isBlank(id.toString())) {
			return error("错误的参数");
		}
		UserCashOut enty = userCashOutService.get(id);
		if(enty == null || !enty.getUserId().equals(getLoginUser().getId())) {
			return error("数据不存在");
		}
		return ok(enty);
	}
	
	//查询资金明细
	@RequestMapping(value = "walletLog/query", method = RequestMethod.POST)
	public JsonResult walletLogQuery(UserWalletLog enty, PageQuery pageQuery) {
		enty.setUserId(getLoginUser().getId());
		IPage<UserWalletLog> page = new Page<UserWalletLog>(pageQuery.getPageNo(), pageQuery.getPageSize());
		page = userWalletService.selectLogs(page, enty);
		return toPageJson(page);
	}
	
	//累计收益
	@RequestMapping(value = "getUserMachineProfit", method = RequestMethod.GET)
	public JsonResult getUserMachineProfit() {
		User user = getLoginUser();
		if(user == null) {
			return error("用户未登录!");
		}
		String userMachineProfit = redisUtil.get(Constant.getUserMachineProfit(user.getId()));
		return ok(StringUtils.isBlank(userMachineProfit)?"0.00":userMachineProfit);
	}
	
	//今日收益
	@RequestMapping(value = "getUserMachineDailyProfit", method = RequestMethod.GET)
	public JsonResult getUserMachineDailyProfit() {
		User user = getLoginUser();
		if(user == null) {
			return error("用户未登录!");
		}
		String userMachineDailyProfit = redisUtil.get(Constant.getUserMachineDailyProfit(user.getId()));
		return ok(StringUtils.isBlank(userMachineDailyProfit)?"0.00":userMachineDailyProfit);
	}
	
	//日化收益
	@RequestMapping(value = "getDailyProfitPoint", method = RequestMethod.GET)
	public JsonResult getDailyProfitPoint() {
		BigDecimal dailyProfitPoint = BigDecimal.ZERO;
		User user = getLoginUser();
		if(user == null) {
			return error("用户未登录!");
		}
		//今日收益金额
		String userMachineDailyProfit = redisUtil.get(Constant.getUserMachineDailyProfit(user.getId()));
		if(StringUtils.isNotBlank(userMachineDailyProfit)) {
			//累计充值金额
			UserRecharge enty = new UserRecharge();
			enty.setUserId(getLoginUser().getId());
			enty.setState(UserRechargeStateEnum.SUCCESS.getCode());
			List<UserRecharge> list = userRechargeService.select(enty);
			BigDecimal sunAmount = BigDecimal.ZERO;
			for(UserRecharge logVO : list) {
				sunAmount = sunAmount.add(logVO.getUsdt());
			}
			if(sunAmount.compareTo(BigDecimal.ZERO) == 0) {
				dailyProfitPoint = BigDecimal.ONE.setScale(4, BigDecimal.ROUND_HALF_UP);
			}else {
				dailyProfitPoint = new BigDecimal(userMachineDailyProfit).divide(sunAmount, 4, BigDecimal.ROUND_HALF_UP);
			}
		}
		//格式化为百分比
		NumberFormat percent = NumberFormat.getPercentInstance();
		percent.setMaximumFractionDigits(2);
		return ok(percent.format(dailyProfitPoint.doubleValue()));
	}
	
	//累计提成
	@RequestMapping(value = "getAgentProfit", method = RequestMethod.GET)
	public JsonResult getAgentProfit() {
		User user = getLoginUser();
		if(user == null) {
			return error("用户未登录!");
		}
		String agentProfit = redisUtil.get(Constant.getAgentProfit(user.getId()));
		return ok(StringUtils.isBlank(agentProfit)?"0.00":agentProfit);
	}
	
	//今日提成
	@RequestMapping(value = "getAgentDailyProfit", method = RequestMethod.GET)
	public JsonResult getAgentDailyProfit() {
		User user = getLoginUser();
		if(user == null) {
			return error("用户未登录!");
		}
		String agentDailyProfit = redisUtil.get(Constant.getAgentDailyProfit(user.getId()));
		return ok(StringUtils.isBlank(agentDailyProfit)?"0.00":agentDailyProfit);
	}
	
	//累计返现
	@RequestMapping(value = "getMachineReturn", method = RequestMethod.GET)
	public JsonResult getMachineReturn() {
		User user = getLoginUser();
		if(user == null) {
			return error("用户未登录!");
		}
		String machineReturn = redisUtil.get(Constant.getMachineReturn(user.getId()));
		return ok(StringUtils.isBlank(machineReturn)?"0.00":machineReturn);
	}
	
	//今日返现
	@RequestMapping(value = "getMachineDailyReturn", method = RequestMethod.GET)
	public JsonResult getMachineDailyReturn() {
		User user = getLoginUser();
		if(user == null) {
			return error("用户未登录!");
		}
		String machineDailyReturn = redisUtil.get(Constant.getMachineDailyReturn(user.getId()));
		return ok(StringUtils.isBlank(machineDailyReturn)?"0.00":machineDailyReturn);
	}
	
	//获取当前允许挂子机的上限值
	@RequestMapping(value = "getLimitChildrenCount", method = RequestMethod.GET)
	public JsonResult getLimitChildrenCount() {
		User user = getLoginUser();
		if(user == null) {
			return error("用户未登录!");
		}
		String limitChildrenCount = redisUtil.get(Constant.getLimitChildrenCount());
		return ok(StringUtils.isBlank(limitChildrenCount)?"0":limitChildrenCount);
	}
	
	//获取用户是否挂满过子机（是否返还过矿机购买金币）
	@RequestMapping(value = "getIsMachineReturned", method = RequestMethod.GET)
	public JsonResult getIsMachineReturned() {
		User user = getLoginUser();
		if(user == null) {
			return error("用户未登录!");
		}
		String isMachineReturned = redisUtil.get(Constant.isMachineReturned(user.getId()));
		return ok(StringUtils.isBlank(isMachineReturned)?"0":isMachineReturned);
	}
	
	//获取用户不可提现金额
	@RequestMapping(value = "getFrozenCashOut", method = RequestMethod.GET)
	public JsonResult getFrozenCashOut() {
		User user = getLoginUser();
		if(user == null) {
			return error("用户未登录!");
		}
		String frozenCashOut = redisUtil.get(Constant.frozenCashOut(user.getId()));
		return ok(StringUtils.isBlank(frozenCashOut)?"0.00":frozenCashOut);
	}
	
	//购买产品
	@RequestMapping(value = "userMachine/add", method = RequestMethod.POST)
	public JsonResult userMachineAdd(UserMachine enty, String tradePassword) {
		JsonResult rst = checkUserMachine(enty);
		if(!rst.isSuccess()) {
			return rst;
		}
		User user = getLoginUser();
		if(user == null) {
			return error("用户未登录!");
		}
		enty.setUserId(user.getId());
		//获取上级用户ID
		if(user.getInvitedId() != null) {
			enty.setParentUserId(user.getInvitedId());
		}
		if(enty.getMachineId() == null)  {
			return error("产品ID参数缺失!");
		}
		
		if (StringUtils.isBlank(tradePassword)) {
			return error("请输入交易密码!");
		}
		
		Machine machine = machineService.get(enty.getMachineId());
		if(machine == null || YnEnum.NO.getCode() == machine.getYn()) {
			return error("产品不存在,或已下线!");
		}

		Calendar calendar = Calendar.getInstance();
		enty.setBeginTime(calendar.getTime());
		calendar.add(Calendar.DATE, machine.getRunningDays());
		enty.setEndTime(calendar.getTime());
		enty.setPrice(machine.getPrice());
		enty.setProfit(machine.getProfit());
		enty.setCode(machine.getCode());
		enty.setIcon(machine.getIcon());
		
		//验证交易密码
		UserWallet userWallet = userWalletService.getByUserId(enty.getUserId());
		if(userWallet == null || StringUtils.isBlank(userWallet.getTradePassword())) {
			return error("未设置交易密码，请先设置!");
		}
		
		tradePassword = MD5Utils.encryptPassword(tradePassword, userWallet.getSalt());
		if(!tradePassword.equals(userWallet.getTradePassword())) {
			return error("交易密码不正确,交易中断!");
		}
		
		enty.setMultiple(BigDecimal.ONE);//默认为1倍
		enty.setState(UserMachineStateEnum.RUNNING.getCode());
		if(StringUtils.isNotBlank(enty.getInvitedCode())) {
			//根据邀请码找到上级机器ID
			UserMachine userMachine = new UserMachine();
			userMachine.setInvitedCode(enty.getInvitedCode());
			userMachine = userMachineService.getByInvitedCode(userMachine);
			if(userMachine != null) {
				if(!enty.getMachineId().equals(userMachine.getMachineId())) {
					return error("对不起，您使用的邀请码不能购买该型号产品!");
				}
				enty.setParentMachineId(userMachine.getId());
				//获取当前允许挂子机的上限值
				String limitChildrenCount = redisUtil.get(Constant.getLimitChildrenCount());
				if(StringUtils.isBlank(limitChildrenCount)) {
					return error("系统拉取子机上限失败，暂不能购买，请稍后再试!");
				}
				//判断上级是否还能挂子机
				if (userMachine.getChildrenCount() != null && userMachine.getChildrenCount() >= Integer.parseInt(limitChildrenCount)) {
					return error("上级已挂满子机，请输入另外的邀请码试试!");
				}
			}else {
				return error("邀请码错误，购买失败!");
			}
		}else {
			//UserTypeEnum.AGENT可以不填邀请码，否则新开的机器谁也买不到
			//UserTypeEnum.NORMAL邀请码必填
			if(user.getType() == UserTypeEnum.NORMAL.getCode()) {
				return error("邀请码为空，购买失败!");
			}
		}
		//生成邀请码
		String invitedCode = userMachineService.createInvitedCode(null);
		enty.setInvitedCode(invitedCode);
		enty.setCreatePin(user.getAccount());
		Long keyId = userMachineService.insert(enty);
		if(keyId == -2L) {
			return error("系统拉取USDT汇率失败，暂不能购买，请稍后再试!");
		}
		if(keyId == -1L) {
			return error("余额不足，购买失败!");
		}
		return ok(enty);
	}
	
	//根据邀请码查找机器
	@GetMapping("getMachineByCode/{invitedCode}")
	public JsonResult getMachineByCode(@PathVariable String invitedCode) {
		if(StringUtils.isBlank(invitedCode)) {
			return error("邀请码为空!");
		}
		User user = getLoginUser();
		if(user == null) {
			return error("用户未登录!");
		}
		UserMachine enty = new UserMachine();
		enty.setInvitedCode(invitedCode);
		UserMachine userMachine = userMachineService.getByInvitedCode(enty);
		if(userMachine != null && userMachine.getMachineId() != null) {
			Machine machine = machineService.get(userMachine.getMachineId());
			return ok(machine);
		}
		return ok(null);
	}
	
	//我的产品
	@RequestMapping(value = "myMachine/query", method = RequestMethod.POST)
	public JsonResult myMachineQuery(UserMachine enty) {
		enty.setUserId(getLoginUser().getId());
		List<UserMachine> list = userMachineService.select(enty);
		return ok(list);
	}
	
	//我的下级产品
	@RequestMapping(value = "childMachine/query", method = RequestMethod.POST)
	public JsonResult childMachineQuery(UserMachine enty) {
		if(enty.getParentMachineId() == null) {
			return error("上级产品ID为空!");
		}
		List<UserMachine> list = userMachineService.select(enty);
		return ok(list);
	}
	
	//提问客服
	@RequestMapping(value = "chatMsg/add", method = RequestMethod.POST)
	public JsonResult chatMsgAdd(ChatMsg enty) {
		JsonResult rst = checkChatMsg(enty);
		if(!rst.isSuccess()) {
			return rst;
		}
		User loginUser = getLoginUser();
		Long userId = loginUser.getId();
		enty.setChatId(userId);
		enty.setFromUserId(userId);
		enty.setCreatePin(loginUser.getNickName());
		enty.setToUserNick("客服小姐姐");
		//内容加密
		enty.setContent(HtmlUtils.htmlEscape(enty.getContent()));
		chatMsgService.add(enty);
		return ok(enty);
	}
	
	//查询客服问答记录（聊天内容）
	@RequestMapping(value = "chatMsg/query", method = RequestMethod.POST)
	public PageJsonResult chatMsgQuery(ChatMsg enty,PageQuery pageQuery) {
		enty.setChatId(getLoginUser().getId());
		Page<ChatMsg> iPageQuery = new Page<ChatMsg>(pageQuery.getPageNo(), pageQuery.getPageSize());
		iPageQuery.addOrder(OrderItem.ascs("id"));
		IPage<ChatMsg> page = chatMsgService.select(iPageQuery, enty);
		return toPageJson(page);
	}
	
	private JsonResult checkUsdtWallet(UserUSDTWallet enty) {
		if (enty == null) {
			return error("错误的参数!");
		}
		if (StringUtils.isBlank(enty.getChain()) || enty.getChain().length() > 10) {
			return error("链名称为空或者超过10个字符!");
		}
		if (StringUtils.isBlank(enty.getAddress()) || enty.getAddress().length() > 100) {
			return error("钱包地址为空或者超过100个字符!");
		}
		return ok();
	}
	
	private JsonResult checkRecharge(UserRecharge enty) {
		if (enty == null) {
			return error("错误的参数!");
		}
		if (enty.getUsdt() == null) {
			return error("充值数量为空!");
		}
		if (StringUtils.isBlank(enty.getChain()) || enty.getChain().length() > 10) {
			return error("链名称为空或者超过10个字符!");
		}
		if (StringUtils.isBlank(enty.getAddress()) || enty.getAddress().length() > 100) {
			return error("钱包地址为空或者超过100个字符!");
		}
		if (StringUtils.isBlank(enty.getSerialNo()) || enty.getSerialNo().length() > 300) {
			return error("流水号为空或者超过300个字符!");
		}
		if (StringUtils.isNotBlank(enty.getMemo()) &&enty.getMemo().length() > 100) {
			return error("备注超过100个字符!");
		}
		return ok();
	}
	
	private JsonResult checkTransfer(UserTransfer enty) {
		if (enty == null) {
			return error("错误的参数!");
		}
		if (enty.getAmount() == null || enty.getAmount().compareTo(BigDecimal.ZERO)<=0) {
			return error("划转金额为空!");
		}
		if (StringUtils.isBlank(enty.getToAccount()) || enty.getToAccount().length() > 50) {
			return error("对方账户为空或者超过50个字符!");
		}
		if (StringUtils.isNotBlank(enty.getMemo()) &&enty.getMemo().length() > 100) {
			return error("备注超过100个字符!");
		}
		return ok();
	}
	
	private JsonResult checkCashOut(UserCashOut enty) {
		if (enty == null) {
			return error("错误的参数!");
		}
		if (enty.getUsdt() == null) {
			return error("提币数量为空!");
		}
		if (StringUtils.isBlank(enty.getChain()) || enty.getChain().length() > 10) {
			return error("链名称为空或者超过10个字符!");
		}
		if (StringUtils.isBlank(enty.getAddress()) || enty.getAddress().length() > 100) {
			return error("钱包地址为空或者超过100个字符!");
		}
		if (StringUtils.isNotBlank(enty.getMemo()) &&enty.getMemo().length() > 100) {
			return error("备注超过100个字符!");
		}
		return ok();
	}
	
	private JsonResult checkUserMachine(UserMachine enty) {
		if (enty == null) {
			return error("错误的参数!");
		}
		if (enty.getCode() == null || enty.getCode().length() > 20) {
			return error("产品型号为空或者超过10个字符!");
		}
		return ok();
	}
	
	private JsonResult checkChatMsg(ChatMsg enty) {
		if (enty == null) {
			return error("错误的参数!");
		}
		if (enty.getToUserId() == null || StringUtils.isBlank(enty.getToUserId().toString())) {
			return error("接收人ID为空!");
		}
		if (enty.getToUserNick() == null || StringUtils.isBlank(enty.getToUserNick().toString())) {
			return error("接收人昵称为空!");
		}
		if (StringUtils.isBlank(enty.getContent()) || enty.getContent().length() > 300) {
			return error("内容为空或者超过10个字符!");
		}
		return ok();
	}
	
}		