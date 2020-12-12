package com.echain.service.business;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.echain.dao.business.user.UserUSDTWalletDao;
import com.echain.domain.business.user.UserUSDTWallet;

@Service
public class UserUsdtWalletService {

	@Autowired
	private UserUSDTWalletDao userUSDTWalletDao;

	/**
	 * 新增
	 * @param enty
	 * @return
	 */
	public Long add(UserUSDTWallet enty) {
		int nCount = userUSDTWalletDao.insert(enty);
		if (nCount == 1) {
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
	public Long update(UserUSDTWallet enty) {
		int nCount = userUSDTWalletDao.updateById(enty);
		if(nCount == 1) {
			return enty.getId();
		}
		return null;
	}
	
	/**
	 * 根据ID获取
	 * 
	 * @param id
	 * @return
	 */
	public UserUSDTWallet get(Long id) {
		return userUSDTWalletDao.selectById(id);
	}
	
	/**
	 * 根据UserId 获取
	 * @param userId
	 * @return
	 */
	public UserUSDTWallet getByUserId(UserUSDTWallet enty) {
		return userUSDTWalletDao.selectOne(new QueryWrapper<UserUSDTWallet>().lambda()
			//userId
			.eq(UserUSDTWallet::getUserId, enty.getUserId())
			//chain
			.eq(StringUtils.isNotBlank(enty.getChain()), UserUSDTWallet::getChain, enty.getChain()));
	}
	
	/**
	 * 根据实体查询对象信息
	 * @param enty
	 * @return
	 */
	public List<UserUSDTWallet> select(UserUSDTWallet enty) {
		return userUSDTWalletDao.selectList(limitFiled(toWrapper(enty)));
	}
	
	/**
	 * 分页查询
	 * 
	 * @param page
	 * @param enty
	 * @return
	 */
	public IPage<UserUSDTWallet> select(IPage<UserUSDTWallet> page, UserUSDTWallet enty) {
		return userUSDTWalletDao.selectPage(page, toWrapper(enty));
	}
	
	private LambdaQueryWrapper<UserUSDTWallet> limitFiled(LambdaQueryWrapper<UserUSDTWallet> query) {
		return query.select(UserUSDTWallet::getId,UserUSDTWallet::getUserId,UserUSDTWallet::getChain,UserUSDTWallet::getAddress,UserUSDTWallet::getCreateTime,UserUSDTWallet::getCreatePin,UserUSDTWallet::getUpdatePin,UserUSDTWallet::getUpdatePin);
	}

	/**
	 * 查询条件
	 * @param enty
	 * @return
	 */
	private LambdaQueryWrapper<UserUSDTWallet> toWrapper(UserUSDTWallet enty) {
		return new QueryWrapper<UserUSDTWallet>().lambda()
			//ID
			.eq(enty.getId() != null ,UserUSDTWallet::getId,enty.getId())
			//userID
			.eq(enty.getUserId() != null ,UserUSDTWallet::getUserId,enty.getUserId())
			//chain
			.eq(StringUtils.isNotBlank(enty.getChain()) ,UserUSDTWallet::getChain,enty.getChain())
			//address
			.eq(StringUtils.isNotBlank(enty.getAddress()) ,UserUSDTWallet::getAddress,enty.getAddress())
			//ID 倒叙
			.orderByDesc(UserUSDTWallet::getId);
	}

}
