package com.echain.web.worker;

import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.echain.common.enums.TurnEnum;
import com.echain.common.utils.AddressUtils;
import com.echain.service.common.RedisUtil;

public abstract class AbstractRedisWorker {

	protected Random random = new Random();

	@Autowired
	protected RedisUtil redisUtil;

	// 最大休息时间 单位秒 默认10秒
	@Value("${worker.default.maxsleeptime:1000}")
	protected int defaultMaxSleepTime;

	// worker key过期时间 单位秒 ,默认60秒
	@Value("${worker.default.keytimeout:20}")
	protected long keyTimeOut;

	@Value("${worker.turn:on}")
	private String workerTurn;

	protected boolean isWorkerOpen() {
		if (TurnEnum.ON.getCode().equals(workerTurn)) {
			String switchKey = String.format("%s_TURN", getWorkerKey());
			String value = redisUtil.get(switchKey);
			//默认是开启
			if(StringUtils.isBlank(value)){
				value = TurnEnum.ON.getCode();
			}
			// 如果是off,就是关了
			return TurnEnum.ON.getCode().equals(value);
		}
		return false;
	}

	/**
	 * 休眠时间 单位秒
	 * 
	 * @return
	 */
	protected int getSleepTime() {
		return random.nextInt(defaultMaxSleepTime);
	}

	/**
	 * worker key 过期时间 单位秒
	 * 
	 * @return
	 */
	protected long getWorkerKeyTimeOut() {
		return keyTimeOut;
	}

	/**
	 * 防止并发 ,休眠
	 */
	private void execSleep() {
		// 防止并发 ,随机休眠
		int sleepTime = getSleepTime();
		// 如果休眠时间<0 那么就跳过
		if (sleepTime <= 0) {
			return;
		}
		try {
			Thread.sleep(sleepTime);
		} catch (Exception ex) {
			getLogger().error("Thread Sleep error!", ex);
		}
	}

	/**
	 * 设置redisvalue ,如果失败,返回值老值
	 * 
	 * @param key
	 * @param value
	 * @param timeOut
	 * @param timeUnit
	 * @return
	 */
	private boolean lock(String key, String value, Long timeOut) {
		return (redisUtil.lock(key, value, timeOut));
	}

	/***
	 * 执行worker
	 */
	public final void worker() {
		// 如果开关是关闭的 ,那么就不执行
		if (!isWorkerOpen()) {
			getLogger().info("{} ---  worker 关闭..不执行!!", getWorkerKey());
			return;
		}
		// 休眠
		execSleep();
		// 服务的KEY
		String key = getWorkerKey();
		// 本机IP+processId
		String localValue = AddressUtils.getProcessId();
		if (StringUtils.isBlank(localValue)) {
			getLogger().error("******************************* KEY:{} 获取本机IP发生异常! worker 不执行", key);
			return;
		}
		try {
			// redis key ,并返回redis中的key
			if (!lock(key, localValue, getWorkerKeyTimeOut())) {
				getLogger().info("******************************* KEY:{} 本次不执行!! ", key);
				return;
			}
			getLogger().info("KEY:{} 开始执行!", key);
			// 执行worker
			exec();
		} catch (Exception ex) {
			getLogger().error("******************************* KEY:{} 执行失败! ", key, ex);
		} finally {
			unlock(key, localValue);
			// getLogger().info("\n\nKEY: "+key +" \t\t " + redisUtil.get(key)
			// +"\t\t"+localValue);
		}
	}

	/**
	 * 释放分布式锁
	 * 
	 * @param key   锁
	 * @param value 请求标识
	 * @return 是否释放成功
	 */
	private boolean unlock(String key, String value) {
		return redisUtil.releaseLock(key, value);
	}

	/**
	 * 获取worker的唯一Key
	 * 
	 * @return
	 */
	protected abstract String getWorkerKey();

	/**
	 * 底层执行worker
	 */
	protected abstract void exec();

	/**
	 * logger 日志
	 * 
	 * @return
	 */
	protected abstract Logger getLogger();
}