package com.echain.service.business;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.echain.dao.business.user.UserDao;
import com.echain.domain.business.user.SysFundCalcBean;
import com.echain.domain.business.user.User;
import com.echain.domain.business.user.UserWallet;

@Service
public class UserService {
	@Resource
	private UserDao userDao;
	
	@Resource
	private UserWalletService userWalletService;
	
	/**
	 * 插入
	 * @param enty
	 * @return
	 */
	@Transactional
	public Long add(User enty) {
		int nCount = userDao.insert(enty);
		if (nCount == 1) {
			//初始化用户钱包
			UserWallet userWallet = new UserWallet();
			userWallet.setId(enty.getId());
			userWallet.setSalt(RandomStringUtils.randomNumeric(8));
			userWallet.setUsableAmount(BigDecimal.ZERO);
			userWallet.setTotalAmount(BigDecimal.ZERO);
			userWallet.setFrozenAmount(BigDecimal.ZERO);
			userWalletService.initAmount(userWallet);
			return enty.getId();
		}
		return null;
	}
	
	/**
	 * 查询总记录数
	 * @param enty
	 * @return
	 */
	public Integer selectCount(User enty) {
		return userDao.selectCount(toWrapper(enty));
	}
	
	/**
	 * 根据User对象获取User对象
	 * 
	 * @param user
	 * @return
	 */
	public User select(User enty) {
		return userDao.selectOne(toWrapper(enty));
	}

	/**
	 * 根据实体查询对象信息
	 * @param enty
	 * @return
	 */
	public List<User> selectList(User enty) {
		return userDao.selectList(toWrapper(enty));
	}
	
	/**
	 * 更新
	 * 
	 * @param enty
	 * @return
	 */
	public int update(User enty) {
		return userDao.updateById(enty);
	}
	
	/**
	 * 根据ID获取
	 * 
	 * @param id
	 * @return
	 */
	public User get(Long id) {
		return userDao.selectById(id);
	}
	
	/**
	 * 分页查询
	 * 
	 * @param page
	 * @param enty
	 * @return
	 */
	public IPage<User> select(IPage<User> page, User enty) {
		return userDao.selectPage(page, toWrapper(enty));
	}

	/**
	 * 查询条件
	 * @param enty
	 * @return
	 */
	private Wrapper<User> toWrapper(User enty) {
		return new QueryWrapper<User>().lambda()
			//id
			.eq(enty.getId() != null, User::getId, enty.getId())
			// account
			.eq(StringUtils.isNotBlank(enty.getAccount()), User::getAccount, enty.getAccount())
			// nickName
			.eq(StringUtils.isNotBlank(enty.getNickName()), User::getNickName, enty.getNickName())
			// accountType
			.eq(enty.getAccountType() != null, User::getAccountType, enty.getAccountType())
			//YN
			.eq(enty.getYn() != null && enty.getYn()>=0, User::getYn, enty.getYn())
			//状态
			.eq(enty.getState() != null && enty.getState()>=0, User::getState, enty.getState())
			//类型 0 普通; 1 代理
			.eq(enty.getType()!=null,User::getType, enty.getType())
			// 邀请ID
			.eq(enty.getInvitedId() != null, User::getInvitedId, enty.getInvitedId());
	}
	
	/**
	 * 资金流统计
	 * @param param
	 * @return
	 */
	public List<SysFundCalcBean> selectSysFundCalc(SysFundCalcBean param){
		return userDao.selectSysFundCalc(param);
	}
}
