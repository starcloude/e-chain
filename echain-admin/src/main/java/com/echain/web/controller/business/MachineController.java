package com.echain.web.controller.business;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.echain.common.beans.JsonResult;
import com.echain.common.beans.PageJsonResult;
import com.echain.common.beans.PageQuery;
import com.echain.common.enums.YnEnum;
import com.echain.common.utils.Json;
import com.echain.domain.business.Machine;
import com.echain.domain.sys.SysOperateLog;
import com.echain.domain.sys.SysUser;
import com.echain.service.business.MachineService;
import com.echain.web.controller.AbsSupperController;

@RestController
@RequestMapping(value = "/api/business/machine/", method = { RequestMethod.POST, RequestMethod.GET })
public class MachineController extends AbsSupperController {

	@Autowired
	private MachineService machineService;

	private final String promitionNameSpace = "machine:";
	
	@RequiresPermissions(promitionNameSpace+"view")
	@RequestMapping(value = "query", method = RequestMethod.POST)
	public PageJsonResult query(Machine entity,PageQuery pageQuery) {
		if (entity == null) {
			entity = new Machine();
		}
		SysUser user = getLoginUser();
		operateLoger.info(new SysOperateLog(user, "矿机管理", "index"));
		Page<Machine> iPageQuery = new Page<Machine>(pageQuery.getPageNo(), pageQuery.getPageSize());
		IPage<Machine> page = machineService.select(iPageQuery, entity);
		return toPageJson(page);
	}

	/**
	 * check
	 * @param entity
	 * @return
	 */
	protected JsonResult checkEntity(Machine entity) {
		
		if(entity == null || StringUtils.isAnyBlank(entity.getCode(),entity.getIcon()) || entity.getPrice() == null || entity.getProfit() == null || entity.getRunningDays() == null
				||entity.getRunningDays().intValue()<=0 || entity.getProfit().compareTo(BigDecimal.ZERO)<=0 || entity.getPrice().compareTo(BigDecimal.ZERO)<=0)
		{
			return error("错误的参数!");
		}
		return null;
	}
	
	/**
	 * 成功
	 * @param entity
	 * @return
	 */
	@RequiresPermissions(promitionNameSpace+"edit")
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public JsonResult edit(Machine entity) {
		JsonResult rst = checkEntity(entity);
		if(rst != null) {
			return rst;
		}
		SysUser user = getLoginUser();
		operateLoger.info(new SysOperateLog(user, "矿机管理-编辑", Json.toJSON(entity)));
		
		int nCount =0;
		if(entity.getId() == null || entity.getId() == 0) {
			entity.setCreatePin(user.getNo());
			nCount = machineService.insert(entity);
		}else {
			entity.setUpdatePin(user.getNo());
			nCount = machineService.update(entity);
		}
		if(nCount!=1) {
			return error("保存失败,请联系管理员");
		}
		return ok();
	}
	
	/**
	 * 删除恢复
	 * @param id
	 * @param yn
	 * @return
	 */
	@RequiresPermissions(promitionNameSpace+"delete")
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public JsonResult delete(Long id, Integer yn) {
		YnEnum eYn = YnEnum.get(yn);
		if (eYn == null) {
			return error("错误的参数!");
		}
		Machine entity = new Machine();
		entity.setId(id);	
		entity.setYn(eYn.getCode());
		try {
			SysUser user = getLoginUser();
			operateLoger.info(new SysOperateLog(user, "矿机管理-删除/恢复", Json.toJSON(entity)));
			entity.setUpdatePin(user.getNo());
			machineService.update(entity);
			return ok();
		} catch (Exception ex) {
			return error("删除发生异常,请联系管理员!");
		}
	}
}
