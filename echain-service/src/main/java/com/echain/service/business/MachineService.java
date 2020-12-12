package com.echain.service.business;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.echain.dao.business.MachineDao;
import com.echain.domain.business.Machine;

@Service
public class MachineService {

	@Autowired
	private MachineDao machineDao;
	
	//@Autowired
	//private RedisUtil redisUtil;

	/**
	 * 插入
	 * @param enty
	 * @return
	 */
	public int insert(Machine enty) {
		return machineDao.insert(enty);
	}
	/**
	 * 更新
	 * 
	 * @param enty
	 * @return
	 */
	public int update(Machine enty) {
		return machineDao.updateById(enty);
	}
	
	/**
	 * 根据ID获取
	 * 
	 * @param id
	 * @return
	 */
	public Machine get(Long id) {
		return machineDao.selectById(id);
	}
	
	/**
	 * 分页查询
	 * 
	 * @param page
	 * @param enty
	 * @return
	 */
	public IPage<Machine> select(IPage<Machine> page, Machine enty) {
		return machineDao.selectPage(page, toWrapper(enty));
	}
	
	/**
	 * 根据User对象获取User对象
	 * 
	 * @param user
	 * @return
	 */
	public Machine select(Machine enty) {
		return machineDao.selectOne(toWrapper(enty));
	}
	
	/**
	 * 查询条件
	 * @param enty
	 * @return
	 */
	private Wrapper<Machine> toWrapper(Machine enty) {
		return new QueryWrapper<Machine>().lambda()
			//Id
			.eq(enty.getId() != null, Machine::getId, enty.getId())
			//code
			.eq(StringUtils.isNotBlank(enty.getCode()), Machine::getCode, enty.getCode())
			//running_days
			.eq(enty.getRunningDays() != null ,Machine::getRunningDays,enty.getRunningDays())
			//yn
			.eq(enty.getYn() != null ,Machine::getYn,enty.getYn())
			//时间范围
			.ge(enty.getCreateTime() != null, Machine::getCreateTime, enty.getCreateTime())
			.le(enty.getCreateTimeEnd() != null, Machine::getCreateTime, enty.getCreateTimeEnd())
			//createPin
			.eq(StringUtils.isNotBlank(enty.getCreatePin()),Machine::getCreatePin, enty.getCreatePin())
			//getId 倒叙
			.orderByDesc(Machine::getId);
	}

}
