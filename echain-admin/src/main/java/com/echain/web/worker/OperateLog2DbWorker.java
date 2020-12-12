package com.echain.web.worker;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import com.echain.common.enums.log.LogKeyEnum;
import com.echain.common.utils.Json;
import com.echain.domain.sys.SysOperateLog;
import com.echain.service.log.OperateLogService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OperateLog2DbWorker extends AbstractRedisWorker {

	private String level;
	
	@Value("${worker.logger.maxCount:10000}")
	private Integer maxCount;
	
	@Resource
	private OperateLogService operateLogService;
	
	/**
	 * 用于加锁
	 */
	@Override
	protected String getWorkerKey() {
		return LogKeyEnum.OPERATE.getName()+"_"+level+"_LOCK";
	}
	
	/**
	 * 弹出队列
	 * @return
	 */
	private String getKey() {
		return LogKeyEnum.OPERATE.getName()+"_"+level;
	}
	
	@Override
	protected void exec() {
		int idx = 0;
		String key = getKey();
		List<SysOperateLog> logs = new ArrayList<SysOperateLog>();
		String content = redisUtil.lRightPop(key);
		while(StringUtils.isNotBlank(content) && idx<=maxCount) {
			idx++;
			try {
				SysOperateLog log = Json.parse(content,SysOperateLog.class);
				log = filter(log);
				if(log == null) {
					continue;
				}
				logs.add(log);
			}catch(Exception ex) {
				log.error("将JSON转换为对象发生异常! {} - OperateLog",content);
				continue;
			}
			content = redisUtil.lRightPop(key);
		}
		log.info("Operate日志收集worker 级别 {} - 总记录数:{}",level,logs.size());
		operateLogService.batchInsert(logs);
	}

	private SysOperateLog filter(SysOperateLog log) {
		if(log == null) {
			return null;
		}
		if(StringUtils.isNotBlank(log.getMemo()) && log.getMemo().length()>5000) {
			log.setMemo(log.getMemo().substring(0, 4999));
		}
		
		log.setLevel(level);
		return log;
	}
	
	@Override
	protected Logger getLogger() {
		return log;
	}

	public void setLevel(String level) {
		this.level = level;
	}
}
