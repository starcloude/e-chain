package com.echain.web.controller.sys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.echain.common.beans.JsonResult;
import com.echain.common.beans.PageJsonResult;
import com.echain.common.enums.SystemWorkerEnum;
import com.echain.common.enums.TurnEnum;
import com.echain.common.enums.log.LogKeyEnum;
import com.echain.common.enums.log.LogTypeEnum;
import com.echain.common.utils.Constant;
import com.echain.domain.sys.SysOperateLog;
import com.echain.domain.sys.SysUser;
import com.echain.service.common.RedisUtil;
import com.echain.web.beans.AdminWalletVo;
import com.echain.web.beans.SystemConfigVo;
import com.echain.web.controller.AbsSupperController;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

@RestController
@RequestMapping(value = "/api/system/config/")
public class ConfigControler extends AbsSupperController {

	@Autowired
	private RedisUtil redisUtil;

	private final String promitionNameSpace = "config:";
	
	@RequiresPermissions(promitionNameSpace+"view")
	@GetMapping({"index"})
	public JsonResult index() {
		SysUser sessionUser = getLoginUser();
		operateLoger.info(new SysOperateLog(sessionUser, "系统配置", "index"));
		List<String> optLog = Lists.newArrayList();
		Stream.of(LogTypeEnum.values()).forEach(s -> {
	            String v = redisUtil.get(LogKeyEnum.OPERATE.getName() + "_" + s.getName() + "_TURN");
	            if (TurnEnum.ON.getCode().equals(v)) {
	                optLog.add(s.getName());
	            }
	        });
		SystemConfigVo vo = new SystemConfigVo();
		vo.setOptlog(optLog.stream().collect(Collectors.joining(",")));
		vo.setExchangeRate(redisUtil.get(Constant.getUsdtExchangeRate()));
		vo.setLimitChildrenCount(redisUtil.get(Constant.getLimitChildrenCount()));
		vo.setTeamLeaderCommissionRate(redisUtil.get(Constant.getTeamLeaderCommissionRate()));
		vo.setMinTransferCount(redisUtil.get(Constant.getMinTransferCount()));
		vo.setTransferRate(redisUtil.get(Constant.getTransferRate()));
		vo.setMinTransferAmount(redisUtil.get(Constant.getMinTransferAmount()));
		vo.setMaxTransferAmount(redisUtil.get(Constant.getMaxTransferAmount()));
		vo.setMinCashOutCount(redisUtil.get(Constant.getMinCashOutCount()));
		vo.setCashOutRate(redisUtil.get(Constant.getCashOutRate()));
		vo.setMinCashOutAmount(redisUtil.get(Constant.getMinCashOutAmount()));
		vo.setMaxCashOutAmount(redisUtil.get(Constant.getMaxCashOutAmount()));
		JsonResult result = JsonResult.OK();
		result.setResult(vo);
		return result;
	}
	
	@RequiresPermissions(promitionNameSpace+"edit")
	@PostMapping({"index"})
	public JsonResult save(SystemConfigVo vo) {
		if(vo == null) {
			return JsonResult.Error("错误的参数!");
		}
		SysUser sessionUser = getLoginUser();
		operateLoger.info(new SysOperateLog(sessionUser, "系统配置", "保存"));
		//操作日志
		Set<String> optLog = Sets.newHashSet(vo.getOptlog().split(","));
		if(optLog == null) {
			optLog = Sets.newHashSet();
		}
		for(LogTypeEnum s : LogTypeEnum.values()) {
			redisUtil.set(LogKeyEnum.OPERATE.getName() + "_" + s.getName() + "_TURN", optLog.contains(s.getName()) ? TurnEnum.ON.getCode() :TurnEnum.OFF.getCode());
		}
		//USDT汇率
		if(StringUtils.isNotBlank(vo.getExchangeRate())) {
			redisUtil.set(Constant.getUsdtExchangeRate(), vo.getExchangeRate());
		}
		//矿机下级上限
		if(StringUtils.isNotBlank(vo.getLimitChildrenCount())) {
			redisUtil.set(Constant.getLimitChildrenCount(), vo.getLimitChildrenCount());
		}
		//团队长提成比例
		if(StringUtils.isNotBlank(vo.getTeamLeaderCommissionRate())) {
			redisUtil.set(Constant.getTeamLeaderCommissionRate(), vo.getTeamLeaderCommissionRate());
		}
		//最小划转数量
		if(StringUtils.isNotBlank(vo.getMinTransferCount())) {
			redisUtil.set(Constant.getMinTransferCount(), vo.getMinTransferCount());
		}
		//划转手续费率
		if(StringUtils.isNotBlank(vo.getTransferRate())) {
			redisUtil.set(Constant.getTransferRate(), vo.getTransferRate());
		}
		//划转手续费下限
		if(StringUtils.isNotBlank(vo.getMinTransferAmount())) {
			redisUtil.set(Constant.getMinTransferAmount(), vo.getMinTransferAmount());
		}
		//划转手续费上限
		if(StringUtils.isNotBlank(vo.getMaxTransferAmount())) {
			redisUtil.set(Constant.getMaxTransferAmount(), vo.getMaxTransferAmount());
		}
		//最小提现数量
		if(StringUtils.isNotBlank(vo.getMinCashOutCount())) {
			redisUtil.set(Constant.getMinCashOutCount(), vo.getMinCashOutCount());
		}
		//提现手续费率
		if(StringUtils.isNotBlank(vo.getCashOutRate())) {
			redisUtil.set(Constant.getCashOutRate(), vo.getCashOutRate());
		}
		//提现手续费下限
		if(StringUtils.isNotBlank(vo.getMinCashOutAmount())) {
			redisUtil.set(Constant.getMinCashOutAmount(), vo.getMinCashOutAmount());
		}
		//提现手续费上限
		if(StringUtils.isNotBlank(vo.getMaxCashOutAmount())) {
			redisUtil.set(Constant.getMaxCashOutAmount(), vo.getMaxCashOutAmount());
		}
		return JsonResult.OK();
	}
	
	@RequiresPermissions(promitionNameSpace+"worker:view")
	@GetMapping({"worker/index"})
	public JsonResult workerIndex() {
		SysUser sessionUser = getLoginUser();
		operateLoger.info(new SysOperateLog(sessionUser, "系统配置-worker配置", "index"));
		
		List<Map<String, String>> workerNodes = new ArrayList<Map<String, String>>();
        for (SystemWorkerEnum e : SystemWorkerEnum.values()) {
            Map<String, String> node = new HashMap<String, String>();
            node.put("code", e.getCode());
            node.put("name", e.getName());
            String turn = redisUtil.get(String.format("%s_TURN", e.getCode()));
            turn = TurnEnum.OFF.getCode().equals(turn) ? TurnEnum.OFF.getCode() : TurnEnum.ON.getCode();
            node.put("turn", turn);
            workerNodes.add(node);
        }
        
        PageJsonResult result = new PageJsonResult(true);
		result.setCurrent(1);
		result.setSize(10);
		result.setTotal(workerNodes.size());
		result.setResult(workerNodes);
		return result;
	}
	
	@RequiresPermissions(promitionNameSpace+"worker:edit")
	@PostMapping({"worker/index"})
	public JsonResult saveWorker(HttpServletRequest request) {
		StringBuilder sbd = new StringBuilder();
		for (SystemWorkerEnum e : SystemWorkerEnum.values()) {
			String value = request.getParameter(e.getCode());
			if (StringUtils.isBlank(value)) {
				continue;
			}
			value = TurnEnum.OFF.getCode().equals(value) ? TurnEnum.OFF.getCode() : TurnEnum.ON.getCode();
			sbd.append(e.getCode()).append(":").append(value).append("\r\n");
			String key = String.format("%s_TURN", e.getCode());
			redisUtil.set(key, value);
		}
		
		if (StringUtils.isBlank(sbd.toString())) {
			return JsonResult.Error("错误的参数!");
		}

		SysUser user = getLoginUser();
		operateLoger.info(new SysOperateLog(user, "系统配置-worker配置", sbd.toString()));
		return JsonResult.OK();
	}
	@RequiresPermissions(promitionNameSpace+"editWallet")
	@GetMapping({"adminWallet"})
	public JsonResult adminWallet() {
		SysUser sessionUser = getLoginUser();
		operateLoger.info(new SysOperateLog(sessionUser, "平台钱包查询", "adminWallet"));
		AdminWalletVo vo = new AdminWalletVo();
		vo.setErc20(redisUtil.get(Constant.getAddressByERC20()));
		vo.setTrc20(redisUtil.get(Constant.getAddressByTRC20()));
		vo.setOmni(redisUtil.get(Constant.getAddressByOMNI()));
		JsonResult result = JsonResult.OK();
		result.setResult(vo);
		return result;
	}
	
	@RequiresPermissions(promitionNameSpace+"editWallet")
	@PostMapping({"adminWallet"})
	public JsonResult saveAdminWallet(AdminWalletVo vo) {
		if(vo == null) {
			return JsonResult.Error("错误的参数!");
		}
		SysUser sessionUser = getLoginUser();
		operateLoger.info(new SysOperateLog(sessionUser, "平台钱包设置", "保存"));
		//收币钱包-ERC20
		if(StringUtils.isNotBlank(vo.getErc20())) {
			redisUtil.set(Constant.getAddressByERC20(), vo.getErc20());
		}
		//收币钱包-TRC20
		if(StringUtils.isNotBlank(vo.getTrc20())) {
			redisUtil.set(Constant.getAddressByTRC20(), vo.getTrc20());
		}
		//收币钱包-OMNI
		if(StringUtils.isNotBlank(vo.getOmni())) {
			redisUtil.set(Constant.getAddressByOMNI(), vo.getOmni());
		}
		return JsonResult.OK();
	}
}
