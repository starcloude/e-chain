package com.echain.service.log;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.echain.dao.sys.SysOperateLogDao;
import com.echain.domain.sys.SysOperateLog;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("operateLogService")
public class OperateLogService {

	@Resource
	private SysOperateLogDao operateLogDao;

	/**
	 * 分页查询
	 * 
	 * @param page
	 * @param enty
	 * @return
	 */
	public IPage<SysOperateLog> select(IPage<SysOperateLog> page, SysOperateLog enty) {
		return operateLogDao.selectPage(page, toWrapper(enty));
	}

	/**
	 * 批量插入
	 * 
	 * @param logs
	 */
	public void batchInsert(List<SysOperateLog> logs) {
		if (CollectionUtils.isEmpty(logs)) {
			return;
		}

		List<SysOperateLog> dblogs = new ArrayList<SysOperateLog>();
		int maxCount = 1000;
		int idx = 0;
		for (SysOperateLog log : logs) {
			dblogs.add(log);
			idx++;
			if (idx % maxCount == 0) {
				doInsert(dblogs);
				dblogs = new ArrayList<SysOperateLog>();
			}
		}
		doInsert(dblogs);
	}

	private void doInsert(List<SysOperateLog> logs) {
		if (CollectionUtils.isEmpty(logs)) {
			return;
		}
		try {
			operateLogDao.batchInsert(logs);
		} catch (Exception ex) {
			log.error("批量插入日志失败! {} ", ex.getMessage(), ex);
		}
	}

	private Wrapper<SysOperateLog> toWrapper(SysOperateLog enty) {
		return new QueryWrapper<SysOperateLog>().lambda()
				// level
				.eq(StringUtils.isNotBlank(enty.getLevel()), SysOperateLog::getLevel, enty.getLevel())
				// node
				.like(StringUtils.isNotBlank(enty.getNode()), SysOperateLog::getNode, enty.getNode())
				// memo
				.like(StringUtils.isNotBlank(enty.getMemo()), SysOperateLog::getMemo, enty.getMemo())
				// createPin
				.eq(StringUtils.isNotBlank(enty.getCreatePin()), SysOperateLog::getCreatePin, enty.getCreatePin())
				//时间范围
				.ge(enty.getCreateTime() != null, SysOperateLog::getCreateTime, enty.getCreateTime())
				.le(enty.getCreateTimeEnd() != null, SysOperateLog::getCreateTime, enty.getCreateTimeEnd())
				// ID 倒叙
				//.orderByDesc(SysOperateLog::getId)
				;
	}
}