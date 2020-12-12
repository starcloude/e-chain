package com.echain.service.business;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.echain.common.enums.business.user.UserWalletLogTypeEnum;
import com.echain.common.utils.Constant;
import com.echain.common.utils.MD5Utils;
import com.echain.dao.business.user.UserWalletDao;
import com.echain.dao.business.user.UserWalletLogDao;
import com.echain.domain.business.user.UserMachine;
import com.echain.domain.business.user.UserWallet;
import com.echain.domain.business.user.UserWalletLog;
import com.echain.service.common.RedisCache;
import com.echain.service.common.RedisUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserWalletService {

	@Autowired
	private UserWalletDao userWalletDao;
	
	@Autowired
	private UserWalletLogDao userWalletLogDao;
	
	@Autowired
	private RedisUtil redisUtil;
	
	@Autowired
	private RedisCache redisCache;
	
	protected Random random = new Random();

	/**
	 * 将db的余额,同步到redis中
	 * @param qrUserId
	 */
	public void syncAmount(Long userId) {
		UserWallet amount = getByUserId(userId);
		if(amount != null) {
			redisUtil.set(Constant.getUserAmountKey(amount.getId()), String.valueOf(amount.getUsableAmount().doubleValue()));
		}
	}
	/**
	 * 插入
	 * @param enty
	 * @return
	 */
	public Long initAmount(UserWallet enty) {
		int nCount = userWalletDao.insert(enty);
		if (nCount == 1) {
			redisUtil.incrByFloat(Constant.getUserAmountKey(enty.getId()), enty.getUsableAmount().floatValue());
			return enty.getId();
		}
		return null;
	}
	
	/**
	 * 更新余额
	 * @param enty 钱包
	 * @param logType 日志类型,如果可用金额不变动,此类型可为空
	 * @param memo	日志备注,如果可用金额不变动,此类型可为空
	 * @return
	 */
	@Transactional
	public int update(UserWallet enty,UserWalletLogTypeEnum logType,String memo) {
		int nCount = userWalletDao.updateByUserId(enty);
		//更新记录 >0 && 金额变动 ,就写日志
		if(logType != null && nCount>0 && enty.getUsableAmount()!= null && enty.getUsableAmount().compareTo(BigDecimal.ZERO)!=0) {
			UserWalletLog walletLog = new UserWalletLog();
			//如果余额变动, 那么久变动 redis的的值
			redisUtil.incrByFloat(Constant.getUserAmountKey(enty.getId()), enty.getUsableAmount().floatValue());
			//变动金额
			walletLog.setAmount(enty.getUsableAmount());
			//变动类型
			walletLog.setType(logType.getCode());
			//用户Id
			walletLog.setUserId(enty.getId());
			//备注
			walletLog.setMemo(memo);
			//创建人,如果创建人是空的 ,那么就取更新人
			walletLog.setCreatePin(StringUtils.isNotBlank(enty.getCreatePin())?enty.getCreatePin():enty.getUpdatePin());
			//新增日志
			userWalletLogDao.insert(walletLog);
		}
		return nCount;
	}
	
	/**
	 * 根据ID获取
	 * 
	 * @param id
	 * @return
	 */
	public UserWallet get(Long id) {
		return userWalletDao.selectById(id);
	}
	
	/**
	 * 根据UserId 获取
	 * @param userId
	 * @return
	 */
	public UserWallet getByUserId(Long userId) {
		return userWalletDao.selectOne(new QueryWrapper<UserWallet>().lambda()
			//userId
			.eq(UserWallet::getId, userId));
	}
	
	/**
	 * 分页查询
	 * 
	 * @param page
	 * @param enty
	 * @return
	 */
	public IPage<UserWallet> select(IPage<UserWallet> page, UserWallet enty) {
		return userWalletDao.selectPage(page, toWrapper(enty));
	}
	
	/**
	 * 日志分页
	 * @param page
	 * @param enty
	 * @return
	 */
	public IPage<UserWalletLog> selectLogs(IPage<UserWalletLog> page, UserWalletLog enty) {
		return userWalletLogDao.selectPage(page, toWrapper(enty));
	}
	
	/**
	 * 根据实体查询对象信息
	 * @param enty
	 * @return
	 */
	public List<UserWalletLog> select(UserWalletLog enty) {
		return userWalletLogDao.selectList(limitFiled(toWrapper(enty)));
	}
	
	private LambdaQueryWrapper<UserWalletLog> limitFiled(LambdaQueryWrapper<UserWalletLog> query) {
		return query.select(UserWalletLog::getId,UserWalletLog::getUserId,UserWalletLog::getAmount,UserWalletLog::getType,UserWalletLog::getMemo,UserWalletLog::getCreateTime,UserWalletLog::getCreatePin);
	}

	/**
	 * 查询条件
	 * @param enty
	 * @return
	 */
	private Wrapper<UserWallet> toWrapper(UserWallet enty) {
		return new QueryWrapper<UserWallet>().lambda()
				//ID
				.eq(enty.getId() != null ,UserWallet::getId,enty.getId());
	}
	
	/**
	 * 日志查询
	 * @param enty
	 * @return
	 */
	private LambdaQueryWrapper<UserWalletLog> toWrapper(UserWalletLog enty) {
		return new QueryWrapper<UserWalletLog>().lambda()
			//Id
			.eq(enty.getId() != null, UserWalletLog::getId, enty.getId())
			//userId
			.eq(enty.getUserId() != null, UserWalletLog::getUserId, enty.getUserId())
			//Type
			.eq(enty.getType() != null ,UserWalletLog::getType,enty.getType())
			//时间范围
			.ge(enty.getCreateTime() != null, UserWalletLog::getCreateTime, enty.getCreateTime())
			.le(enty.getCreateTimeEnd() != null, UserWalletLog::getCreateTime, enty.getCreateTimeEnd())
			//createPin
			.eq(StringUtils.isNotBlank(enty.getCreatePin()),UserWalletLog::getCreatePin, enty.getCreatePin())
			//ID 倒叙
			.orderByDesc(UserWalletLog::getId);
	}
	
	/**
	 * 代理收益计算
	 * @param agentId
	 */
	@Transactional
	public boolean calcAgentProfit(Long agentId) {
		BigDecimal amount = userWalletLogDao.selectAgentProfit(agentId);
		log.warn("代理[{}]下所有用户收益 [{}]!",agentId,amount);
		if(amount == null || amount.compareTo(BigDecimal.ZERO)<=0) {
			return false;
		}
		
		//乘以比例 TODO
		amount = amount.multiply(getAgentProfitPct());
		
		if(amount == null || amount.compareTo(BigDecimal.ZERO)<=0) {
			log.warn("代理[{}]不计算收益! 收益比例不正常! {}",agentId,amount);
			return false;
		}
		
		UserWallet enty = new UserWallet();
		enty.setId(agentId);
		enty.setTotalAmount(amount);
		enty.setUsableAmount(amount);
		enty.setUpdatePin("calcAgentProfit");
		if(update(enty, UserWalletLogTypeEnum.AGENT_REBATE,"代理收益")!=1) {
			return false;
		}
		
		long expireTs = getDailyExpireSeconds();
		//累加当日收益
		String dailyKey = Constant.getAgentDailyProfit(agentId);
		redisUtil.incrByFloat(dailyKey, amount.doubleValue());
		redisUtil.expire(dailyKey, expireTs, TimeUnit.SECONDS);
		//累加总收益
		redisUtil.incrByFloat(Constant.getAgentProfit(agentId), amount.doubleValue());
		return true;
	}
	
	private BigDecimal getAgentProfitPct() {
		return redisCache.getBigDecimal(Constant.getTeamLeaderCommissionRate(),new BigDecimal("0.01"));
	}
	
	/**
	 * 重置交易密码
	 * @param wallet
	 * @return
	 */
	public boolean resetTradePwd(UserWallet wallet) {
		UserWallet db = get(wallet.getId());
		if(db == null) {
			log.warn("用户[{}]找不到钱包信息,是否初始化了!",wallet.getId()); 
			return false;
		}
		String pwd = MD5Utils.encryptPassword(wallet.getTradePassword(), db.getSalt());
		wallet = new UserWallet();
		wallet.setId(db.getId());
		wallet.setTradePassword(pwd);
		wallet.setUpdatePin(wallet.getUpdatePin());
		return update(wallet, null,null)>0;
	}

	/**
	 * 计算单台矿机的收益
	 * @param machine
	 * @return
	 */
	@Transactional
	public boolean calcUserMachineProfit(UserMachine machine) {
		//开始累加收益 单日收益 * 倍数
		BigDecimal amount = machine.getProfit().multiply(machine.getMultiple());
		if(amount.compareTo(BigDecimal.ZERO)<=0){
			return false;
		}
		
		//加总,加可用
		UserWallet wallet = new UserWallet();
		wallet.setId(machine.getUserId());
		wallet.setTotalAmount(amount);
		wallet.setUsableAmount(amount);
		wallet.setUpdatePin("worker");
		if(update(wallet, UserWalletLogTypeEnum.PROFIT,"矿机：["+machine.getId()+"] 收益 ["+amount+"] ")!=1) {
			return false;
		}
		
		long expireTs = getDailyExpireSeconds();
		
		//累加当日收益
		String dailyKey = Constant.getUserMachineDailyProfit(machine.getUserId());
		redisUtil.incrByFloat(dailyKey, amount.doubleValue());
		redisUtil.expire(dailyKey, expireTs, TimeUnit.SECONDS);
		
		//累加总收益
		redisUtil.incrByFloat(Constant.getUserMachineProfit(machine.getUserId()), amount.doubleValue());
		
		return true;
	}
	
	@Transactional
	public boolean calcMachineReturn(UserMachine machine){
		//返回金额
		BigDecimal amount = machine.getPrice();
		if(amount.compareTo(BigDecimal.ZERO)<=0){
			return false;
		}
		
		//加总,加可用
		UserWallet wallet = new UserWallet();
		wallet.setId(machine.getUserId());
		wallet.setTotalAmount(amount);
		wallet.setUsableAmount(amount);
		wallet.setUpdatePin("worker");
		
		if(update(wallet, UserWalletLogTypeEnum.REWARD,"矿机：["+machine.getId()+"] 返还 ["+amount+"] ")!=1) {
			return false;
		}
		
		long expireTs = getDailyExpireSeconds();
		
		//累加当日收益
		String dailyKey = Constant.getMachineDailyReturn(machine.getUserId());
		redisUtil.incrByFloat(dailyKey, amount.doubleValue());
		redisUtil.expire(dailyKey, expireTs, TimeUnit.SECONDS);
		
		//累加总收益
		redisUtil.incrByFloat(Constant.getMachineReturn(machine.getUserId()), amount.doubleValue());
		
		//是否返还过矿机金额打标
		redisUtil.set(Constant.isMachineReturned(machine.getUserId()), "1");
		
		//释放提现冻结金额
		redisUtil.delete(Constant.frozenCashOut(machine.getUserId()));
		
		return true;
	}
	
	private long getDailyExpireSeconds() {
		return (LocalDate.now().plusDays(1).atStartOfDay(ZoneOffset.ofHours(8)).toInstant().toEpochMilli() - System.currentTimeMillis() )/1000 + random.nextInt(10);
	}
}
