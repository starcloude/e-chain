package com.echain.service.business;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.echain.common.enums.business.user.UserWalletLogTypeEnum;
import com.echain.common.exceptions.BusiException;
import com.echain.common.utils.Constant;
import com.echain.dao.business.user.UserTransferDao;
import com.echain.domain.business.user.UserTransfer;
import com.echain.domain.business.user.UserWallet;
import com.echain.service.common.RedisUtil;

@Service
public class UserTransferService {

	@Autowired
	private UserTransferDao userTransferDao;
	
	@Autowired
	private UserWalletService userWalletService;
	
	@Autowired
	private RedisUtil redisUtil;
	
	/**
	 * 根据ID获取
	 * 
	 * @param id
	 * @return
	 */
	public UserTransfer get(Long id) {
		return userTransferDao.selectById(id);
	}
	
	/**
	 * 分页查询
	 * 
	 * @param page
	 * @param enty
	 * @return
	 */
	public IPage<UserTransfer> select(IPage<UserTransfer> page, UserTransfer enty) {
		return userTransferDao.selectPage(page, toWrapper(enty));
	}
	
	/**
	 * 查询条件
	 * @param enty
	 * @return
	 */
	private Wrapper<UserTransfer> toWrapper(UserTransfer enty) {
		return new QueryWrapper<UserTransfer>().lambda()
			//Id
			.eq(enty.getId() != null, UserTransfer::getId, enty.getId())
			//userId
			//.eq(enty.getUserId() != null, UserTransfer::getUserId, enty.getUserId())
			//toUserId
			.eq(enty.getToUserId() != null, UserTransfer::getToUserId, enty.getToUserId())
			//toAccount
			//.eq(StringUtils.isNotBlank(enty.getToAccount()), UserTransfer::getToAccount, enty.getToAccount())
			//memo
			.like(StringUtils.isNotBlank(enty.getMemo()) ,UserTransfer::getMemo,enty.getMemo())
			//时间范围
			.ge(enty.getCreateTime() != null, UserTransfer::getCreateTime, enty.getCreateTime())
			.le(enty.getCreateTimeEnd() != null, UserTransfer::getCreateTime, enty.getCreateTimeEnd())
			//createPin
			.eq(StringUtils.isNotBlank(enty.getCreatePin()),UserTransfer::getCreatePin, enty.getCreatePin())
			//如果userId!=null and (user_id = enty.getUserId() or to_user_id = enty.getUserId())
			.and(enty.getUserId() != null, wrapper->{
				wrapper.eq(UserTransfer::getUserId, enty.getUserId()).or().eq(UserTransfer::getToUserId, enty.getUserId());
			})
			//createTime 倒叙
			.orderByDesc(UserTransfer::getCreateTime);
	}
	
	@Transactional
	public void doTrans(UserTransfer enty, UserWallet userwallet) {
		if(enty.getAmount().compareTo(BigDecimal.ZERO)<=0) {
			throw new BusiException("转账金额不能小于等于0");
		}
		
		//计算手续费
		String transferRate = redisUtil.get(Constant.getTransferRate());//费率
		String minTransferAmount = redisUtil.get(Constant.getMinTransferAmount());//下限
		String maxTransferAmount = redisUtil.get(Constant.getMaxTransferAmount());//上限
		if(StringUtils.isBlank(transferRate)) {
			enty.setFee(BigDecimal.ZERO);
		}else {
			BigDecimal feeAmount = enty.getAmount().multiply(new BigDecimal(transferRate));
			if(feeAmount.compareTo(new BigDecimal(minTransferAmount)) == -1)//小于下限取下限
				feeAmount = new BigDecimal(minTransferAmount);
			if(feeAmount.compareTo(new BigDecimal(maxTransferAmount)) == 1)//大于上限取上限
				feeAmount = new BigDecimal(maxTransferAmount);
			enty.setFee(feeAmount);
		}
		
		//金额 = 金额+手续费
		BigDecimal amount =  enty.getAmount().add(enty.getFee());
		//转账金额大于可用金额，终止转账
		if(amount.compareTo(userwallet.getUsableAmount())>0) {
			throw new BusiException("转账金额加手续费大于可用金额了!");
		}
		
		// 1================== 转出人
		//转出人 - 扣可用
		UserWallet fromUser = new UserWallet();
		fromUser.setId(enty.getUserId());
		//-可用
		fromUser.setUsableAmount(amount.negate());
		//-总额
		fromUser.setTotalAmount(amount.negate());
		
		fromUser.setUpdatePin(String.valueOf(enty.getUserId()));
		String memo = "转给["+enty.getToAccount()+"]金额：["+enty.getAmount()+"] 手续费:["+enty.getFee()+"]";
		if(userWalletService.update(fromUser, UserWalletLogTypeEnum.TRANSFER,memo)!=1) {
			throw new BusiException("扣减金额发生异常!");
		}
		
		// 2================== 收款人
		//收款人 + 总额 +可用
		UserWallet toUser = new UserWallet();
		toUser.setId(enty.getToUserId());
		//+可用
		toUser.setUsableAmount(enty.getAmount());
		//+总额
		toUser.setTotalAmount(enty.getAmount());
		
		toUser.setUpdatePin(String.valueOf(enty.getUserId()));
		memo = "收到["+enty.getCreatePin()+"] 转账金额：["+enty.getAmount()+"]";
		if(userWalletService.update(toUser, UserWalletLogTypeEnum.TRANSFER,memo)!=1) {
			throw new BusiException("加金额发生异常!");
		}
		
		// 3================== 插入转账记录表
		enty.setMemo("转给"+enty.getToAccount());
		if(userTransferDao.insert(enty)!=1) {
			throw new BusiException("转账发生异常!");
		}
	
	}
}
