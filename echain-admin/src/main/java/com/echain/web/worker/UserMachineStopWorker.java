package com.echain.web.worker;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.echain.common.enums.SystemWorkerEnum;
import com.echain.service.business.UserMachineService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserMachineStopWorker extends AbstractRedisWorker {
	
	@Autowired
	private UserMachineService userMachineService;
	
	/**
	 * 用于加锁
	 */
	@Override
	protected String getWorkerKey() {
		return SystemWorkerEnum.UserMachineStopWorker.getCode();
	}
	
	@Override
	protected void exec() {
		while(userMachineService.expireUserMachine()) {
			try {
				Thread.sleep(20L);
			} catch (InterruptedException e) {
				log.error("sleep Exception ex",e);
			}
		}
	}
	
	@Override
	protected Logger getLogger() {
		return log;
	}
}
