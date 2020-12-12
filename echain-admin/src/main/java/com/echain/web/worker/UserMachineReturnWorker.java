package com.echain.web.worker;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.echain.common.enums.SystemWorkerEnum;
import com.echain.common.utils.Constant;
import com.echain.common.utils.Json;
import com.echain.domain.business.user.UserMachine;
import com.echain.service.business.UserMachineService;
import com.echain.service.business.UserWalletService;
import com.echain.service.common.RedisUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserMachineReturnWorker extends AbstractRedisWorker {
	
	@Autowired
	private UserMachineService userMachineService;
	
	@Autowired
	private RedisUtil redisUtil;
	
	@Autowired
	private UserWalletService userWalletService;
	
	private int limit = 200;
	/**
	 * 用于加锁
	 */
	@Override
	protected String getWorkerKey() {
		return SystemWorkerEnum.UserMachineReturnWorker.getCode();
	}
	
	private Integer getMaxChildrenCount() {
		String value = redisUtil.get(Constant.getLimitChildrenCount());
		Integer nValue = 7;
		try {
			if(StringUtils.isNotBlank(value)) {
				nValue = Integer.parseInt(value);
			}
		}catch(Exception ex) {
			log.error("strValue [{}] to int error",value);
		}
		return nValue;
	}
	
	@Override
	protected void exec() {
		List<UserMachine> userMachineList = null;
		Integer maxChildrenCount = getMaxChildrenCount();
		long startId = 0L;
		//查询所有运行中的
		while(CollectionUtils.isNotEmpty(userMachineList = userMachineService.selectRunningMachine(maxChildrenCount,startId, limit))) {
			for(UserMachine m : userMachineList) {
				startId = m.getId();
				if(!isCanCalc(m)) {
					log.warn("矿机:[{}] 不计算收益!",Json.toJSON(m));
					continue;
				}
				if(userWalletService.calcMachineReturn(m)) {
					tag(m);
				}
			}
			
			try {
				Thread.sleep(20L);
			} catch (InterruptedException e) {
				log.error("sleep Exception ex",e);
			}
		}
	}
	
	/**
	 * 是否允许算
	 * @param userMachine
	 * @return
	 */
	private boolean isCanCalc(UserMachine userMachine) {
		//如果==null
		if(userMachine==null) {
			return false;
		}
		//截止时间<当前时间,已经过期了的
		if(userMachine.getEndTime() == null || userMachine.getEndTime().before(new Date())) {
			return false;
		}
		
		//已经算过了
		if("1".equals(redisUtil.get(getUserMachineCalcUniqueKey(userMachine)))){
			return false;
		}
		
		return true;
	}
	
	//打标
	private void tag(UserMachine userMachine) {
		redisUtil.setEx(getUserMachineCalcUniqueKey(userMachine), "1", 360, TimeUnit.DAYS);
	}
	
	//唯一key
	private String getUserMachineCalcUniqueKey(UserMachine userMachine) {
		return String.format("USER_MACHINE_CALC_RETURN_UNIQUE_%s", userMachine.getId());
	}
	
	@Override
	protected Logger getLogger() {
		return log;
	}
}
