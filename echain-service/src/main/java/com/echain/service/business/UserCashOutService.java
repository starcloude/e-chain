package com.echain.service.business;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.echain.common.enums.business.user.UserCashOutStateEnum;
import com.echain.common.enums.business.user.UserWalletLogTypeEnum;
import com.echain.common.exceptions.BusiException;
import com.echain.common.utils.Constant;
import com.echain.dao.business.user.UserCashOutDao;
import com.echain.domain.business.user.UserCashOut;
import com.echain.domain.business.user.UserWallet;
import com.echain.service.common.RedisUtil;

@Service
public class UserCashOutService {

	@Autowired
	private UserCashOutDao userCashOutDao;
		
	@Autowired
	private UserWalletService userWalletService;
	
	@Autowired
	private RedisUtil redisUtil;
	
	/**
	 * 插入
	 * @param enty
	 * @return
	 */
	@Transactional
	public Long insert(UserCashOut enty, UserWallet userwallet) {
		//判断用户是否有机器已挂满子机
		String isMachineReturned = redisUtil.get(Constant.isMachineReturned(enty.getUserId()));
		if(!"1".equals(isMachineReturned)) {//没有挂满的，提现冻结一半金额作为不可提现金额存入redis
			enty.setUsdt(enty.getUsdt().divide(new BigDecimal("2"),4));
			//累加不可体现金额
			redisUtil.incrByFloat(Constant.frozenCashOut(enty.getUserId()), enty.getUsdt().doubleValue());
		}
		
		//计算手续费
		String cashOutRate = redisUtil.get(Constant.getCashOutRate());//费率
		String minCashOutAmount = redisUtil.get(Constant.getMinCashOutAmount());//下限
		String maxCashOutAmount = redisUtil.get(Constant.getMaxCashOutAmount());//上限
		if(StringUtils.isBlank(cashOutRate)) {
			enty.setFee(BigDecimal.ZERO);
		}else {
			BigDecimal feeAmount = enty.getUsdt().multiply(new BigDecimal(cashOutRate));
			if(feeAmount.compareTo(new BigDecimal(minCashOutAmount)) == -1)//小于下限取下限
				feeAmount = new BigDecimal(minCashOutAmount);
			if(feeAmount.compareTo(new BigDecimal(maxCashOutAmount)) == 1)//大于上限取上限
				feeAmount = new BigDecimal(maxCashOutAmount);
			enty.setFee(feeAmount);
		}
		
		//插入提现记录
		if(userCashOutDao.insert(enty)!=1) {
			throw new BusiException("插入不唯一");
		}
		
		//扣可用  + 冻结
		//恢复金额 = 提现金额 + 手续费
		BigDecimal amount = enty.getUsdt().add(enty.getFee());
		//转账金额大于可用金额，终止提现
		if(amount.compareTo(userwallet.getUsableAmount())>0) {
			throw new BusiException("提现金额加手续费大于可用金额了!");
		}
		
		//解冻 并恢复可用量
		UserWallet wallet = new UserWallet();
		wallet.setId(enty.getUserId());
		//加冻结
		wallet.setFrozenAmount(amount);
		//扣可用
		wallet.setUsableAmount(amount.negate());
		//updatePin
		wallet.setUpdatePin(enty.getCreatePin());
		
		if(userWalletService.update(wallet, UserWalletLogTypeEnum.CASHOUT,"申请提现:["+enty.getId()+"]")!=1) {
			throw new BusiException("更新不唯一,请刷新后重试! - 余额");
		}
		return enty.getId();
	}
	/**
	 * 更新
	 * 
	 * @param enty
	 * @return
	 */
	public int update(UserCashOut enty) {
		int nCount = userCashOutDao.updateById(enty);
		return nCount;
	}
	
	/**
	 * 根据ID获取
	 * 
	 * @param id
	 * @return
	 */
	public UserCashOut get(Long id) {
		return userCashOutDao.selectById(id);
	}
	
	/**
	 * 分页查询
	 * 
	 * @param page
	 * @param enty
	 * @return
	 */
	public IPage<UserCashOut> select(IPage<UserCashOut> page, UserCashOut enty) {
		return userCashOutDao.selectPage(page, toWrapper(enty));
	}
	
	/**
	 * 查询条件
	 * @param enty
	 * @return
	 */
	private Wrapper<UserCashOut> toWrapper(UserCashOut enty) {
		return new QueryWrapper<UserCashOut>().lambda()
			//Id
			.eq(enty.getId() != null, UserCashOut::getId, enty.getId())
			//userId
			.eq(enty.getUserId() != null, UserCashOut::getUserId, enty.getUserId())
			//chain
			.eq(StringUtils.isNotBlank(enty.getChain()), UserCashOut::getChain, enty.getChain())
			//address
			.eq(StringUtils.isNotBlank(enty.getAddress()), UserCashOut::getAddress, enty.getAddress())
			//state
			.eq(enty.getState() != null ,UserCashOut::getState,enty.getState())
			//memo
			.like(StringUtils.isNotBlank(enty.getMemo()) ,UserCashOut::getMemo,enty.getMemo())
			//时间范围
			.ge(enty.getCreateTime() != null, UserCashOut::getCreateTime, enty.getCreateTime())
			.le(enty.getCreateTimeEnd() != null, UserCashOut::getCreateTime, enty.getCreateTimeEnd())
			//createPin
			.eq(StringUtils.isNotBlank(enty.getCreatePin()),UserCashOut::getCreatePin, enty.getCreatePin())
			//createTime 倒叙
			.orderByDesc(UserCashOut::getCreateTime)
			;
	}

	
	private boolean updateByOldStatus(UserCashOut enty,UserCashOutStateEnum oldState) {
		//1更新状态为成功
		LambdaUpdateWrapper<UserCashOut> updateWrapper = new UpdateWrapper<UserCashOut>().lambda().eq(UserCashOut::getId, enty.getId()).eq(UserCashOut::getState,oldState.getCode());
		return userCashOutDao.update(enty, updateWrapper) == 1;
	}
	
	/**
	 * 成功
	 * @param enty
	 */
	public void success(UserCashOut enty) {
		enty.setState(UserCashOutStateEnum.SUCCESS.getCode());
		if(!updateByOldStatus(enty, UserCashOutStateEnum.INIT)) {
			throw new BusiException("更新不唯一,请刷新后重试!");
		}
		//查询DB记录
		UserCashOut dbCashOut = get(enty.getId());
		//恢复金额 = 提现币数 + 手续费
		BigDecimal amount = dbCashOut.getUsdt().add(dbCashOut.getFee());
		
		//实际扣除提现金额
		UserWallet wallet = new UserWallet();
		wallet.setId(enty.getUserId());
		
		//减冻结
		wallet.setFrozenAmount(amount.negate());
		//可用不变
		//减总额
		wallet.setTotalAmount(amount.negate());
		//updatePin
		wallet.setUpdatePin(enty.getUpdatePin());
		
		if(userWalletService.update(wallet, UserWalletLogTypeEnum.CASHOUT,"提现成功,金额转账到钱包!单号:["+dbCashOut.getId()+"]")!=1) {
			throw new BusiException("更新不唯一,请刷新后重试! - 余额");
		}
	}
	
	/**
	 * 失败
	 * @param enty
	 */
	@Transactional
	public void fail(UserCashOut enty) {
		//查询DB记录
		UserCashOut dbCashOut = get(enty.getId());
		if(dbCashOut == null || UserCashOutStateEnum.INIT.getCode() != dbCashOut.getState()) {
			throw new BusiException("记录不存在,或者次记录不处于待处理状态!");
		}
		enty.setState(UserCashOutStateEnum.FAIL.getCode());
		if(!updateByOldStatus(enty, UserCashOutStateEnum.INIT)) {
			throw new BusiException("更新不唯一,请刷新后重试!");
		}
		
		//恢复金额 = 提现币数 + 手续费
		BigDecimal amount = dbCashOut.getUsdt().add(dbCashOut.getFee());
		
		//解冻 并恢复可用量
		UserWallet wallet = new UserWallet();
		wallet.setId(enty.getUserId());
		
		//减冻结
		wallet.setFrozenAmount(amount.negate());
		//加可用
		wallet.setUsableAmount(amount);
		//updatePin
		wallet.setUpdatePin(enty.getUpdatePin());
		
		if(userWalletService.update(wallet, UserWalletLogTypeEnum.CASHOUT,"提现失败,金额原路退回!单号:["+dbCashOut.getId()+"]")!=1) {
			throw new BusiException("更新不唯一,请刷新后重试! - 余额");
		}
		
		//如果有提现redis冻结 ,就释放
		String frozenCashOut = redisUtil.get(Constant.frozenCashOut(dbCashOut.getUserId()));
		if(StringUtils.isNotBlank(frozenCashOut)) {
			//冻结金额>0
			if(new BigDecimal(frozenCashOut).compareTo(BigDecimal.ZERO)>0) {
				redisUtil.incrByFloat(Constant.frozenCashOut(dbCashOut.getUserId()), dbCashOut.getUsdt().negate().doubleValue());
			}
		}
	}
}
