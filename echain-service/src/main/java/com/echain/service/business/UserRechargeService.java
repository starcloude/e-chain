package com.echain.service.business;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.echain.common.enums.business.user.UserRechargeStateEnum;
import com.echain.common.enums.business.user.UserWalletLogTypeEnum;
import com.echain.common.exceptions.BusiException;
import com.echain.dao.business.user.UserRechargeDao;
import com.echain.domain.business.user.UserRecharge;
import com.echain.domain.business.user.UserWallet;

@Service
public class UserRechargeService {

	@Autowired
	private UserRechargeDao userRechargeDao;
	
	@Autowired
	private UserWalletService userWalletService;
	
	/**
	 * 插入
	 * @param enty
	 * @return
	 */
	@Transactional
	public Long insert(UserRecharge enty) {
		int nCount = userRechargeDao.insert(enty);
		if (nCount == 1) {
			/* 申请充值不影响金额，无需记录日志*/
			/* UserWalletLog log = new UserWalletLog();
			log.setUserId(enty.getUserId());
			log.setAmount(enty.getUsdt());
			log.setMemo("申请充值:["+enty.getId()+"]");
			log.setType(UserWalletLogTypeEnum.RECHARGE.getCode());
			log.setCreatePin(enty.getUpdatePin());
			userWalletLogDao.insert(log);*/
			return enty.getId();
		}
		return null;
	}
	/**
	 * 更新
	 * 
	 * @param enty
	 * @return
	 */
	public int update(UserRecharge enty) {
		int nCount = userRechargeDao.updateById(enty);
		return nCount;
	}
	
	/**
	 * 根据ID获取
	 * 
	 * @param id
	 * @return
	 */
	public UserRecharge get(Long id) {
		return userRechargeDao.selectById(id);
	}
	
	/**
	 * 分页查询
	 * 
	 * @param page
	 * @param enty
	 * @return
	 */
	public IPage<UserRecharge> select(IPage<UserRecharge> page, UserRecharge enty) {
		return userRechargeDao.selectPage(page, toWrapper(enty));
	}
	
	/**
	 * 根据实体查询对象信息
	 * @param enty
	 * @return
	 */
	public List<UserRecharge> select(UserRecharge enty) {
		return userRechargeDao.selectList(limitFiled(toWrapper1(enty)));
	}
	
	private LambdaQueryWrapper<UserRecharge> limitFiled(LambdaQueryWrapper<UserRecharge> query) {
		return query.select(UserRecharge::getId,UserRecharge::getUserId,UserRecharge::getAmount,UserRecharge::getUsdt,UserRecharge::getChain,UserRecharge::getAddress,UserRecharge::getState,UserRecharge::getSerialNo,UserRecharge::getMemo,UserRecharge::getCreateTime,UserRecharge::getCreatePin);
	}
	
	/**
	 * 查询条件
	 * @param enty
	 * @return
	 */
	private Wrapper<UserRecharge> toWrapper(UserRecharge enty) {
		return new QueryWrapper<UserRecharge>().lambda()
			//Id
			.eq(enty.getId() != null, UserRecharge::getId, enty.getId())
			//userId
			.eq(enty.getUserId() != null, UserRecharge::getUserId, enty.getUserId())
			//chain
			.eq(StringUtils.isNotBlank(enty.getChain()) ,UserRecharge::getChain,enty.getChain())
			//address
			.eq(StringUtils.isNotBlank(enty.getAddress()) ,UserRecharge::getAddress,enty.getAddress())
			//state
			.eq(enty.getState() != null ,UserRecharge::getState,enty.getState())
			//serialNo
			.eq(StringUtils.isNotBlank(enty.getSerialNo()) ,UserRecharge::getSerialNo,enty.getSerialNo())
			//memo
			.like(StringUtils.isNotBlank(enty.getMemo()) ,UserRecharge::getMemo,enty.getMemo())
			//时间范围
			.ge(enty.getCreateTime() != null, UserRecharge::getCreateTime, enty.getCreateTime())
			.le(enty.getCreateTimeEnd() != null, UserRecharge::getCreateTime, enty.getCreateTimeEnd())
			//createPin
			.eq(StringUtils.isNotBlank(enty.getCreatePin()),UserRecharge::getCreatePin, enty.getCreatePin())
			//createTime 倒叙
			.orderByDesc(UserRecharge::getCreateTime);
	}
	
	/**
	 * 查询条件
	 * @param enty
	 * @return
	 */
	private LambdaQueryWrapper<UserRecharge> toWrapper1(UserRecharge enty) {
		return new QueryWrapper<UserRecharge>().lambda()
			//Id
			.eq(enty.getId() != null, UserRecharge::getId, enty.getId())
			//userId
			.eq(enty.getUserId() != null, UserRecharge::getUserId, enty.getUserId())
			//chain
			.eq(StringUtils.isNotBlank(enty.getChain()) ,UserRecharge::getChain,enty.getChain())
			//address
			.eq(StringUtils.isNotBlank(enty.getAddress()) ,UserRecharge::getAddress,enty.getAddress())
			//state
			.eq(enty.getState() != null ,UserRecharge::getState,enty.getState())
			//serialNo
			.eq(StringUtils.isNotBlank(enty.getSerialNo()) ,UserRecharge::getSerialNo,enty.getSerialNo())
			//memo
			.like(StringUtils.isNotBlank(enty.getMemo()) ,UserRecharge::getMemo,enty.getMemo())
			//时间范围
			.ge(enty.getCreateTime() != null, UserRecharge::getCreateTime, enty.getCreateTime())
			.le(enty.getCreateTimeEnd() != null, UserRecharge::getCreateTime, enty.getCreateTimeEnd())
			//createPin
			.eq(StringUtils.isNotBlank(enty.getCreatePin()),UserRecharge::getCreatePin, enty.getCreatePin())
			//createTime 倒叙
			.orderByDesc(UserRecharge::getCreateTime);
	}

	/**
	 * 更新状态
	 * @param enty
	 * @param oldState
	 * @return
	 */
	private boolean updateByOldStatus(UserRecharge enty,UserRechargeStateEnum oldState) {
		//1更新状态为成功
		LambdaUpdateWrapper<UserRecharge> updateWrapper = new UpdateWrapper<UserRecharge>().lambda().eq(UserRecharge::getId, enty.getId()).eq(UserRecharge::getState,oldState.getCode());
		return userRechargeDao.update(enty, updateWrapper) == 1;
	}
	
	/**
	 * 成功
	 * @param enty
	 */
	@Transactional
	public void success(UserRecharge enty) {
		UserRecharge dbEntity = get(enty.getId());
		if(dbEntity == null || UserRechargeStateEnum.INIT.getCode() != dbEntity.getState()) {
			throw new BusiException("充值失败! 记录不存在,或者次记录不处于待处理状态!");
		}
		
		enty.setState(UserRechargeStateEnum.SUCCESS.getCode());
		if(!updateByOldStatus(enty, UserRechargeStateEnum.INIT)) {
			throw new BusiException("充值失败! 更新不唯一,请刷新后重试!");
		}
		
		//加余额
		//恢复金额 = 充值币数
		BigDecimal amount = dbEntity.getUsdt();
		
		UserWallet wallet = new UserWallet();
		wallet.setId(enty.getUserId());
		//加总额
		wallet.setTotalAmount(amount);
		//加可用
		wallet.setUsableAmount(amount);
		//updatePin
		wallet.setUpdatePin(enty.getUpdatePin());
		
		if(userWalletService.update(wallet, UserWalletLogTypeEnum.RECHARGE,"充值成功!单号:["+dbEntity.getId()+"]")!=1) {
			throw new BusiException("充值失败! 更新不唯一,请刷新后重试! - 余额");
		}
	}
	
	/**
	 * 失败
	 * @param enty
	 */
	public void fail(UserRecharge enty) {
		enty.setState(UserRechargeStateEnum.FAIL.getCode());
		if(!updateByOldStatus(enty, UserRechargeStateEnum.INIT)) {
			throw new BusiException("更新不唯一,请刷新后重试!");
		}
		//充值失败不影响金额，无需记录日志
		/*UserRecharge dbEntity = get(enty.getId());
		//恢复金额 = 充值币数
		BigDecimal amount = dbEntity.getUsdt();
		UserWalletLog log = new UserWalletLog();
		log.setUserId(enty.getUserId());
		log.setAmount(amount);
		log.setMemo("充值失败!单号:["+dbEntity.getId()+"]");
		log.setType(UserWalletLogTypeEnum.RECHARGE.getCode());
		log.setCreatePin(enty.getUpdatePin());
		userWalletLogDao.insert(log);*/
	}
	
}
