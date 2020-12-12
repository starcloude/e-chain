package com.echain.service.business;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.echain.common.enums.business.user.UserMachineStateEnum;
import com.echain.common.enums.business.user.UserWalletLogTypeEnum;
import com.echain.dao.business.user.UserMachineDao;
import com.echain.domain.business.user.UserMachine;
import com.echain.domain.business.user.UserWallet;

@Service
public class UserMachineService {

	@Autowired
	private UserMachineDao userMachineDao;

	@Autowired
	private UserWalletService userWalletService;

	/**
	 * 插入
	 * 
	 * @param enty
	 * @return
	 */
	@Transactional
	public Long insert(UserMachine enty) {
		int nCount = userMachineDao.insert(enty);
		if (nCount == 1) {
			//买矿机 ,扣可用
			UserWallet userWallet= new UserWallet();
			userWallet.setId(enty.getUserId());
			userWallet.setUsableAmount(enty.getPrice().negate());
			userWallet.setUpdatePin(enty.getCreatePin());
			
			String memo = "购买产品["+enty.getId()+"],扣除币数："+enty.getPrice();
			//购买成功后，上级机器不为空，给上级children_count+1
			if(userWalletService.update(userWallet,UserWalletLogTypeEnum.ORDER,memo)==1) {
				if(enty.getParentMachineId() != null) {
					userMachineDao.updateChildrenCount(enty);
				}
			}else {
				enty.setState(UserMachineStateEnum.FAIL.getCode());
				userMachineDao.updateById(enty);
				return -1L;
			}
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
	public int update(UserMachine enty) {
		int nCount = userMachineDao.updateById(enty);
		return nCount;
	}

	/**
	 * 根据ID获取
	 * 
	 * @param id
	 * @return
	 */
	public UserMachine get(Long id) {
		return userMachineDao.selectById(id);
	}

	/**
	 * 生成invitedCode
	 * 
	 * @return
	 */
	public String createInvitedCode(String invitedCode) {
		if(invitedCode == null || StringUtils.isBlank(invitedCode)) {
			invitedCode = RandomStringUtils.randomAlphabetic(6);
		}
		UserMachine enty = new UserMachine();
		enty.setInvitedCode(invitedCode.toUpperCase());
		List<UserMachine> list = this.select(enty);
		if(list.size() > 0) {
			invitedCode = RandomStringUtils.randomAlphabetic(6);
			this.createInvitedCode(invitedCode.toUpperCase());
		}
		return invitedCode.toUpperCase();
	}
	
	/**
	 * 分页查询
	 * 
	 * @param page
	 * @param enty
	 * @return
	 */
	public IPage<UserMachine> select(IPage<UserMachine> page, UserMachine enty) {
		return userMachineDao.selectPage(page, toWrapper(enty));
	}

	/**
	 * 根据实体查询对象信息
	 * @param enty
	 * @return
	 */
	public List<UserMachine> select(UserMachine enty) {
		return userMachineDao.selectList(limitFiled(toWrapper(enty)));
	}
	
	private LambdaQueryWrapper<UserMachine> limitFiled(LambdaQueryWrapper<UserMachine> query) {
		return query.select(UserMachine::getId,UserMachine::getUserId,UserMachine::getParentUserId,UserMachine::getParentMachineId,UserMachine::getCode,UserMachine::getMachineId,UserMachine::getPrice,UserMachine::getProfit,UserMachine::getMultiple,UserMachine::getInvitedCode,UserMachine::getChildrenCount,UserMachine::getState,UserMachine::getIcon,UserMachine::getBeginTime,UserMachine::getEndTime,UserMachine::getYn,UserMachine::getCreateTime,UserMachine::getCreatePin,UserMachine::getUpdatePin,UserMachine::getUpdatePin);
	}
	
	/**
	 * 根据invitedCode 获取
	 * @param invitedCode
	 * @return
	 */
	public UserMachine getByInvitedCode(UserMachine enty) {
		return userMachineDao.selectOne(new QueryWrapper<UserMachine>().lambda()
			//invitedCode
			.eq(UserMachine::getInvitedCode, enty.getInvitedCode())
			);
	}
	
	/**
	 * 根据ID和原始状态修改实体
	 * @param enty
	 * @param oldState
	 * @return
	 */
	public boolean updateByIdAndOldStatus(UserMachine enty,UserMachineStateEnum oldState) {
		//1更新状态为成功
		LambdaUpdateWrapper<UserMachine> updateWrapper = new UpdateWrapper<UserMachine>().lambda().eq(UserMachine::getId, enty.getId()).eq(UserMachine::getState,oldState.getCode());
		return userMachineDao.update(enty, updateWrapper) == 1;
	}
	
	/**
	 * 查询条件
	 * 
	 * @param enty
	 * @return
	 */
	private LambdaQueryWrapper<UserMachine> toWrapper(UserMachine enty) {
		return new QueryWrapper<UserMachine>().lambda()
				//Id
				.eq(enty.getId() != null, UserMachine::getId, enty.getId())
				//userId
				.eq(enty.getUserId() != null, UserMachine::getUserId, enty.getUserId())
				//parentUserId
				.eq(enty.getParentUserId() != null, UserMachine::getParentUserId, enty.getParentUserId())
				//parentMachineId
				.eq(enty.getParentMachineId() != null, UserMachine::getParentMachineId, enty.getParentMachineId())
				//code
				.eq(StringUtils.isNotBlank(enty.getCode()), UserMachine::getCode, enty.getCode())
				//multiple
				.eq(enty.getMultiple() != null, UserMachine::getMultiple, enty.getMultiple())
				//invitedCode
				.eq(StringUtils.isNotBlank(enty.getInvitedCode()), UserMachine::getInvitedCode, enty.getInvitedCode())
				//state
				.eq(enty.getState() != null ,UserMachine::getState,enty.getState())
				//矿机ID
				.eq(enty.getMachineId() != null ,UserMachine::getMachineId,enty.getMachineId())
				//yn
				.eq(enty.getYn() != null ,UserMachine::getYn,enty.getYn())
				//时间范围
				.ge(enty.getBeginTime() != null, UserMachine::getBeginTime, enty.getBeginTime())
				.le(enty.getEndTime() != null, UserMachine::getEndTime, enty.getEndTime())
				//createPin
				.eq(StringUtils.isNotBlank(enty.getCreatePin()),UserMachine::getCreatePin, enty.getCreatePin())
				//createTime 倒叙
				.orderByDesc(UserMachine::getCreateTime);
	}
	
	/**
	 * 用户矿机过期
	 * @return
	 */
	public boolean expireUserMachine() {
		LambdaUpdateWrapper<UserMachine> updateWrapper = new UpdateWrapper<UserMachine>().lambda()
				.eq(UserMachine::getState,UserMachineStateEnum.RUNNING.getCode())
				.le(UserMachine::getEndTime, new Date())
				.last("limit 200")
				;
		UserMachine enty = new UserMachine();
		enty.setState(UserMachineStateEnum.STOPED.getCode());
		enty.setUpdatePin("worker");
		enty.setUpdateTime(new Date());
		return userMachineDao.update(enty, updateWrapper)>0;
	}
	
	/**
	 * 查询运行中的矿机
	 * @param childrenCount
	 * @param startId
	 * @param size
	 * @return
	 */
	public List<UserMachine> selectRunningMachine(Integer childrenCount ,long startId,long size) {
		LambdaQueryWrapper<UserMachine> queryWrapper = new QueryWrapper<UserMachine>().lambda()
		.eq(UserMachine::getState,UserMachineStateEnum.RUNNING.getCode())
		.gt(UserMachine::getId, startId)
		.ge(childrenCount!=null && childrenCount>0 ,UserMachine::getChildrenCount, childrenCount)
		.last("limit "+size)
		;
		return userMachineDao.selectList(queryWrapper);
	}
}
