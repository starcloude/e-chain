package com.echain.web.controller.business;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.echain.common.beans.JsonResult;
import com.echain.domain.business.user.SysFundCalcBean;
import com.echain.domain.sys.SysOperateLog;
import com.echain.domain.sys.SysUser;
import com.echain.service.business.UserService;
import com.echain.web.controller.AbsSupperController;

@RestController
@RequestMapping(value = "/api/business/syscalc")
public class SysCalcController extends AbsSupperController {

	@Autowired
	private UserService userService;

	private final String promitionNameSpace = "syscalc:";
	
	@RequiresPermissions(promitionNameSpace+"view")
	@RequestMapping(value = "query", method = RequestMethod.POST)
	public JsonResult query(SysFundCalcBean param) {
		SysUser user = getLoginUser();
		operateLoger.info(new SysOperateLog(user, "资金统计", "index"));
		List<SysFundCalcBean> list = userService.selectSysFundCalc(param);
		if(CollectionUtils.isEmpty(list)) {
			return ok();
		}
		return ok(list.stream().filter(e->{return StringUtils.isNotBlank(e.getTag()) || e.getI()!=null || e.getO()!= null;}).collect(Collectors.toList()));
	}
}
