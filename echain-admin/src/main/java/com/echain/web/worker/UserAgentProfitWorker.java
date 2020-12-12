package com.echain.web.worker;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.echain.common.enums.SystemWorkerEnum;
import com.echain.common.enums.YnEnum;
import com.echain.common.enums.business.user.UserStateEnum;
import com.echain.common.enums.business.user.UserTypeEnum;
import com.echain.domain.business.user.User;
import com.echain.service.business.UserService;
import com.echain.service.business.UserWalletService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserAgentProfitWorker extends AbstractRedisWorker {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserWalletService userWalletService;
	
	
	/**
	 * 用于加锁
	 */
	@Override
	protected String getWorkerKey() {
		return SystemWorkerEnum.UserAgentProfitWorker.getCode();
	}
	
	@Override
	protected void exec() {
		//查询所有的代理用户 类型是代理,yn=有效,state=正常
		User enty = new User();
		enty.setType(UserTypeEnum.AGENT.getCode());
		enty.setYn(YnEnum.YES.getCode());
		enty.setState(UserStateEnum.NORMAL.getCode());
		
		List<User> uList = userService.selectList(enty);
		if(CollectionUtils.isEmpty(uList)) {
			log.warn("没有代理用户,不计算!");
			return;
		}
		for(User u : uList) {
			if(!isCanCalc(u)) {
				log.warn("用户[{}]已经本次不计算!",u.getId());
				continue;
			}
			try {
				if(userWalletService.calcAgentProfit(u.getId())) {
					tag(u); 
				}
			}catch(Exception ex) {
				log.error("代理[{}]算收益发生异常!",u.getId(),ex);
			}
		}
	}
	
	
	
	/**
	 * 是否允许算
	 * @param userMachine
	 * @return
	 */
	private boolean isCanCalc(User user) {
		//如果==null
		if(user==null) {
			return false;
		}
		
		//已经算过了
		if("1".equals(redisUtil.get(getAgentProfitCalcUniqueKey(user)))){
			return false;
		}
		
		return true;
	}
	
	//打标
	private void tag(User user) {
		LocalDate localDate = LocalDate.now().plusDays(1);
		long expireTs = (localDate.atStartOfDay(ZoneOffset.ofHours(8)).toInstant().toEpochMilli() - System.currentTimeMillis() )/1000 + random.nextInt(20);
		redisUtil.setEx(getAgentProfitCalcUniqueKey(user), "1", expireTs, TimeUnit.SECONDS);
	}
	
	//唯一key
	private String getAgentProfitCalcUniqueKey(User user) {
		return String.format("AGENT_PROFIT_CALC_UNIQUE_%s", user.getId());
	}
	
	@Override
	protected Logger getLogger() {
		return log;
	}
}
