package com.echain.service.business;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.echain.dao.business.user.UserAuthenticationDao;
import com.echain.dao.business.user.UserWalletDao;
import com.echain.domain.business.user.UserAuthentication;
import com.echain.domain.business.user.UserWallet;

//@Slf4j
@Service
public class UserAuthenticationService {
	@Resource
	private UserAuthenticationDao userAuthenticationDao;
	
	@Resource
	private UserWalletDao userWalletDao;
	
	//@Autowired
	//private RedisUtil redisUtil;
	
	/**
	 * 插入
	 * @param enty
	 * @return
	 */
	@Transactional
	public Long add(UserAuthentication enty) {
		int nCount = userAuthenticationDao.insert(enty);
		if (nCount == 1) {
			//完成认证时给用户钱包开户
			UserWallet amount = userWalletDao.selectById(enty.getId());
			if(amount == null) {
				amount = new UserWallet();
				amount.setId(enty.getId());
				amount.setTotalAmount(BigDecimal.ZERO);
				amount.setUsableAmount(BigDecimal.ZERO);
				amount.setFrozenAmount(BigDecimal.ZERO);
				amount.setSalt(RandomStringUtils.randomNumeric(8));
				userWalletDao.insert(amount);
			}
			return enty.getId();
		}
		return null;
	}
	
	/**
	 * 查询总记录数
	 * @param enty
	 * @return
	 */
	public Integer selectCount(UserAuthentication enty) {
		return userAuthenticationDao.selectCount(toWrapper(enty));
	}
	
	/**
	 * 根据实体查询对象信息
	 * @param enty
	 * @return
	 */
	public List<UserAuthentication> select(UserAuthentication enty) {
		return userAuthenticationDao.selectList(limitFiled(toWrapper(enty)));
	}
	
	/**
	 * 更新
	 * 
	 * @param enty
	 * @return
	 */
	public int update(UserAuthentication enty) {
		int nRst = userAuthenticationDao.updateById(enty);
		return nRst;
	}
	
	/**
	 * 根据ID获取
	 * 
	 * @param id
	 * @return
	 */
	public UserAuthentication get(Long id) {
		return userAuthenticationDao.selectById(id);
	}
	
	/**
	 * 分页查询
	 * 
	 * @param page
	 * @param enty
	 * @return
	 */
	public IPage<UserAuthentication> select(IPage<UserAuthentication> page, UserAuthentication enty) {
//		LambdaQueryWrapper<UserAuthentication> query = limitFiled(toWrapper(enty));
//		return userAuthenticationDao.selectPage(page, query);
		return userAuthenticationDao.selectPage(page, toWrapper(enty));
	}
	
	private LambdaQueryWrapper<UserAuthentication> limitFiled(LambdaQueryWrapper<UserAuthentication> query) {
		return query.select(UserAuthentication::getId,UserAuthentication::getRealName,UserAuthentication::getIdCardNo,UserAuthentication::getIdCardImg1,UserAuthentication::getIdCardImg2,UserAuthentication::getIdCardWithUser,UserAuthentication::getState,UserAuthentication::getMemo,UserAuthentication::getCreateTime,UserAuthentication::getCreatePin,UserAuthentication::getUpdatePin,UserAuthentication::getUpdatePin);
	}
	
	/**
	 * 查询条件
	 * @param enty
	 * @return
	 */
	private LambdaQueryWrapper<UserAuthentication> toWrapper(UserAuthentication enty) {
		return new QueryWrapper<UserAuthentication>().lambda()
			// id
			.eq(enty.getId() != null, UserAuthentication::getId, enty.getId())
			// realName
			.eq(StringUtils.isNotBlank(enty.getRealName()), UserAuthentication::getRealName, enty.getRealName())
			// idCardNo
			.eq(StringUtils.isNotBlank(enty.getIdCardNo()), UserAuthentication::getIdCardNo, enty.getIdCardNo())
			// memo
			.eq(StringUtils.isNotBlank(enty.getMemo()), UserAuthentication::getMemo, enty.getMemo())
			// state
			.eq(enty.getState() != null, UserAuthentication::getState, enty.getState())
			//createTime 倒叙
			.orderByDesc(UserAuthentication::getCreateTime);
	}
	
	//校验
	public boolean isImageOk(UserAuthentication code) {
		if(code == null ||code.getId() == null || StringUtils.isAnyBlank(code.getIdCardImg1(),code.getIdCardImg2())) {
			return false;
		}
		return true;
	}
}
