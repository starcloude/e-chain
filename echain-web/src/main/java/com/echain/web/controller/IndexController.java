package com.echain.web.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.echain.common.beans.JsonResult;
import com.echain.common.beans.PageQuery;
import com.echain.common.enums.YnEnum;
import com.echain.common.enums.business.user.UserAccountTypeEnum;
import com.echain.common.enums.business.user.UserAuthenticationStateEnum;
import com.echain.common.enums.business.user.UserQuestionEnum;
import com.echain.common.enums.business.user.UserStateEnum;
import com.echain.common.enums.business.user.UserTypeEnum;
import com.echain.common.enums.business.user.UserWalletLogTypeEnum;
import com.echain.common.utils.AesEncodeUtil;
import com.echain.common.utils.Constant;
import com.echain.common.utils.Json;
import com.echain.common.utils.MD5Utils;
import com.echain.common.utils.RequestClientUtil;
import com.echain.common.utils.SqlFilterUtil;
import com.echain.domain.business.Machine;
import com.echain.domain.business.user.User;
import com.echain.domain.business.user.UserAuthentication;
import com.echain.domain.business.user.UserQuestion;
import com.echain.domain.business.user.UserQuestionRequest;
import com.echain.domain.business.user.UserWallet;
import com.echain.domain.sys.SysOperateLog;
import com.echain.domain.sys.SysUser;
import com.echain.service.business.FileService;
import com.echain.service.business.MachineService;
import com.echain.service.business.UserAuthenticationService;
import com.echain.service.business.UserQuestionService;
import com.echain.service.business.UserService;
import com.echain.service.business.UserWalletService;
import com.echain.service.common.RedisUtil;
import com.echain.web.shiro.ShiroSession;
import com.google.common.collect.Maps;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/")
public class IndexController extends AbsSupperController {
	@Autowired
	private RedisUtil redisUtil;
	
	@Resource
	private UserService userService;
	
	@Resource
	private UserAuthenticationService userAuthenticationService;
	
	@Resource
	private UserQuestionService userQuestionService;
	
	@Resource
	private UserWalletService userWalletService;
	
	@Resource
	private MachineService machineService;
	
	@Resource
	private FileService fileService;
	
	//用户登录
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public JsonResult login(String userName, String userPwd, String r, String captchaCode, HttpServletRequest request) {
		userName = SqlFilterUtil.convertValue(userName);
		if (StringUtils.isAnyBlank(userName,userPwd,r,captchaCode)) {
			return error("参数错误,请勿外站提交!");
		}
		if (r.length() > 10) {
			r = r.substring(0, 9);
		}
		if (captchaCode.length() < 4) {
			return error("验证码错误或已过期,请刷新后重试!");
		}
		String captChaCodeKey = Constant.getCaptchaCodeKey(r, captchaCode);
		String dbCaptChaCode = redisUtil.get(captChaCodeKey);
		if (!captchaCode.equals(dbCaptChaCode)) {
			return error("验证码错误或已过期,请刷新后重试!");
		}

		// 删除验证码
		redisUtil.delete(captChaCodeKey);

		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(userName, userPwd);
		try {
            subject.login(token);
        } catch (IncorrectCredentialsException e) {
            log.error("账号或密码不正确!", e);
            return error("账号或密码不正确!");
        } catch (AuthenticationException e) {
            log.error("用户登录发生异常!", e);
            return error(e.getMessage());
        } catch (Exception e) {
            log.error("系统异常,请联系管理员!", e);
            return error("系统异常,请联系管理员!");
        }

		String ip = RequestClientUtil.getRemoteIP(request);
		User sessionUser = getLoginUser();
		SysUser sysuser = new SysUser();
		sysuser.setIp(ip);
		sysuser.setNo(sessionUser.getAccount());
		operateLoger.info(new SysOperateLog(sysuser, "用户登录", "login"));
		return ok(sessionUser);
	}

	//用户退出登录
	@RequestMapping(value = "/logout")
	public JsonResult logout() {
		ShiroSession.logout();
		return ok();
	}
	
	@RequestMapping(value = "/session")
	public JsonResult session() {
		Map<String, Object> rstMap = Maps.newConcurrentMap();
		User user = getLoginUser();
		rstMap.put("user", user);
		return ok(rstMap);
	}
	
	//用户注册
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public JsonResult register(User enty, String r, String captchaCode) {
		JsonResult rst = checkUser(enty);
		if(!rst.isSuccess()) {
			return rst;
		}
		if (StringUtils.isBlank(r)) {
			return error("缺少参数：时间戳,请勿外站提交!");
		}
		if (r.length() > 10) {
			r = r.substring(0, 9);
		}
		if (StringUtils.isBlank(captchaCode)) {
			return error("缺少参数：验证码,请勿外站提交!");
		}
		if (captchaCode.length() < 4) {
			return error("验证码错误或已过期,请刷新后重试!");
		}
		String captChaCodeKey = Constant.getCaptchaCodeKey(r, captchaCode);
		String dbCaptChaCode = redisUtil.get(captChaCodeKey);
		if (!captchaCode.equals(dbCaptChaCode)) {
			return error("验证码错误或已过期,请刷新后重试!");
		}

		// 删除验证码
		redisUtil.delete(captChaCodeKey);
		
		try {
			User query = new User();
			query.setAccount(enty.getAccount());
			Integer nCount = userService.selectCount(query);
			if (nCount != null && nCount > 0) {
				return error("账号:[" + enty.getAccount() + "]已经存在了!");
			}
			if(enty.getInvitedId() != null) {
				User query1 = userService.get(enty.getInvitedId());
				if(query1 == null || query1.getYn()==YnEnum.NO.getCode()) {
					return error("此邀请码:[" + enty.getInvitedId() + "]不是有效的邀请码!");
				}
				//如果上级有分组，通过邀请来注册的用户自动归于上级的分组
				if(query1 != null && StringUtils.isNotBlank(query1.getTag())) {
					enty.setTag(query1.getTag());
				}
			}
			//设置密码
			enty.setSalt(RandomStringUtils.randomNumeric(8));
			if(StringUtils.isNotBlank(enty.getPassword())) {
				String pwd = MD5Utils.encryptPassword(enty.getPassword(), enty.getSalt());
				enty.setPassword(pwd);
			}else {
				if (enty.getPassword().length() < 3 || enty.getPassword().length() > 30) {
					return error("密码少于3个字符或超过30个字符!");
				}
			}
			//注册类型-普通用户
			enty.setType(UserTypeEnum.NORMAL.getCode());
			//用户类型-手机号码
			enty.setAccountType(UserAccountTypeEnum.PHONE.getCode());
			//状态-正常
			enty.setState(UserStateEnum.NORMAL.getCode());
			SysUser sysuser = new SysUser();
			sysuser.setNo(enty.getAccount());
			operateLoger.info(new SysOperateLog(sysuser, "用户注册", Json.toJSON(enty)));
			enty.setCreatePin(enty.getAccount());
			userService.add(enty);
			ShiroSession.set("registerUserId", enty.getId());
			log.info("registerUserId:"+ShiroSession.get("registerUserId"));
			return ok(enty);
		} catch (Exception ex) {
			return error("用户注册发生异常,请联系管理员!");
		}
	}
	
	//获取问题选项
	@RequestMapping(value = "question/select", method = RequestMethod.GET)
	public JsonResult questionSelect() {
		Map<String,Object> jo = Maps.newHashMap();
		for(UserQuestionEnum e : UserQuestionEnum.values()){
			jo.put(String.valueOf(e.getCode()), e.getName());
		}
		return ok(jo);
	}
	
	//设置问题
	@RequestMapping(value = "question/add", method = RequestMethod.POST)
	public JsonResult questionAdd(@RequestBody UserQuestionRequest enty) {
		log.info("questionId:"+Json.toJSON(enty.getQuestionId()));
		log.info("answer:"+Json.toJSON(enty.getAnswer()));
		if(enty.getQuestionId() == null || enty.getQuestionId().length != 3 || enty.getAnswer() == null || enty.getAnswer().length != 3) {
			return error("需要设置三个问题和答案，缺一不可!");
		}
		User user = getLoginUser();//登录后修改问题信息
		if(user == null) {
			Long userId = Long.parseLong(ShiroSession.get("registerUserId"));
			user = userService.get(userId);//注册时提交问题信息
			if(user == null) {
				return error("您还未提交注册信息!");
			}
		}
		try {
			int res = userQuestionService.save(user.getId(),enty.getQuestionId(),enty.getAnswer());
			if(res != 3) {
				return error("设置问题答案发生异常!");
			}
			SysUser sysuser = new SysUser();
			sysuser.setNo(user.getAccount());
			operateLoger.info(new SysOperateLog(sysuser, "设置问题答案", user.getId()+"/"+Json.toJSON(enty)));
			return ok();
		} catch (Exception ex) {
			return error("设置问题答案发生异常,请联系管理员!");
		}
	}
	
	//获取用户问题
	@RequestMapping(value = "question/query", method = RequestMethod.GET)
	public JsonResult questionQuery() {
		User user = getLoginUser();
		if (user == null) {
			return error("用户未登录!");
		}
		UserQuestion enty = new UserQuestion();
		enty.setUserId(user.getId());
		List<UserQuestion> list = userQuestionService.select(enty);
		Map<String,String> map = Maps.newHashMap();
		list.forEach(m -> {
			map.put(m.getQuestionId().toString(),UserQuestionEnum.getName(m.getQuestionId().intValue()));
		});
		return ok(map);
	}
	
	//用户忘记密码
	@RequestMapping(value = "forgetPwd", method = RequestMethod.POST)
	public JsonResult forgetPwd(@RequestBody UserQuestionRequest enty) {
		if(StringUtils.isBlank(enty.getAccount()) || StringUtils.isBlank(enty.getPassword()) || enty.getPassword().length() > 30) {
			return error("参数错误,请勿外站提交!");
		}
		if(enty.getQuestionId() == null || enty.getAnswer() == null  || enty.getQuestionId().length != 3 || enty.getAnswer().length != enty.getQuestionId().length) {
			return error("需要回答三个问题，才可以修改密码哦!");
		}

		try {
			
			//查询用户
			User queryParam = new User();
			queryParam.setAccount(enty.getAccount());
			List<User> listUser = userService.selectList(queryParam);
			if (CollectionUtils.isEmpty(listUser)) {
				return error("账号:[" + enty.getAccount() + "]不存在!");
			}
			
			User user = listUser.get(0);
			
			//查询问题
			UserQuestion questionParam = new  UserQuestion();
			questionParam.setUserId(user.getId());
			Map<Long,UserQuestion> questionMap = userQuestionService.select(questionParam).stream().collect(Collectors.toMap(UserQuestion::getQuestionId, e->{return e;}));
			
			//验证问题答案
			for(int i=0;i<enty.getQuestionId().length;i++) {
				Long questionId  = enty.getQuestionId()[i];
				String answer = enty.getAnswer()[i];
				UserQuestion uq = questionMap.get(questionId);
				if(uq == null || !answer.equals(uq.getAnswer())) {
					return error("问题验证未通过，不能修改密码!");
				}
			}
			
			//密码加密
			String password  = MD5Utils.encryptPassword(enty.getPassword(), user.getSalt());
			user.setPassword(password);
			
			//账号/YN,不能改
			user.setSalt(null);
			user.setAccount(null);
			user.setYn(null);
		
			user.setUpdatePin(enty.getAccount());
			
			SysUser sysuser = new SysUser();
			sysuser.setNo(enty.getAccount());
			operateLoger.info(new SysOperateLog(sysuser, "用户通过回答问题修改了密码", Json.toJSON(enty)));
			//执行更新
			if(userService.update(user)==1) {
				return ok();
			}
			return error("系统异常,请联系管理员!");
		} catch (Exception ex) {
			return error("用户通过回答问题修改密码时发生异常,请联系管理员!");
		}
	}
	
	//用户修改密码
	@RequestMapping(value = "updatePwd", method = RequestMethod.POST)
	public JsonResult updatePwd(User enty,String oldPwd) {
		try {
			if(enty == null || StringUtils.isAnyBlank(enty.getPassword())) {
				return error("新密码不允许为空!");
			}
			
			User user = getLoginUser();
			if (user == null) {
				return error("用户未登录!");
			}
			if (StringUtils.isBlank(enty.getAccount()) || enty.getAccount().length() > 20) {
				return error("账号为空或者超过20个字符为空!");
			}
			
			User dbUser = userService.get(user.getId());
			
			if (dbUser == null) {
				return error("账号:[" + enty.getAccount() + "]不存在!");
			}
			
			String saltStr = dbUser.getSalt();
			
			oldPwd = MD5Utils.encryptPassword(oldPwd, saltStr);
			if(!oldPwd.equals(dbUser.getPassword())) {
				return error("原密码不正确，请输入正确的原密码!");
			}
			String pwd = enty.getPassword();
			// 如果密码不为空,那么就需要根据salt 重新加密生成加密密码
			if (StringUtils.isNotBlank(pwd)) {
				if (pwd.length() > 30) {
					return error("密码超过30个字符!");
				}
				pwd = MD5Utils.encryptPassword(pwd, saltStr);
			} else {
				return error("密码不能为空!");
			}
			
			enty.setPassword(pwd);
			enty.setId(user.getId());
			
			//账号/YN,不能改
			enty.setAccount(null);
			enty.setYn(null);
			enty.setUpdatePin(user.getAccount());
			
			SysUser sysuser = new SysUser();
			sysuser.setNo(user.getAccount());
			operateLoger.info(new SysOperateLog(sysuser, "用户修改密码", Json.toJSON(enty)));
			userService.update(enty);
			return ok();
		} catch (Exception ex) {
			return error("用户修改密码时发生异常,请联系管理员!");
		}
	}
	
	//上传认证信息
	@RequestMapping(value = "card/save", method = RequestMethod.POST)
	public JsonResult cardSave(UserAuthentication enty) {
		try {
			UserAuthentication enty0 = userAuthenticationService.get(enty.getId());
			if (enty0 != null) {
				return error("您已认证，无需重复认证!");
			}
			if (StringUtils.isBlank(enty.getRealName()) || enty.getRealName().length() > 15) {
				return error("真实姓名为空或者超过15个字符!");
			}
			if (StringUtils.isBlank(enty.getIdCardNo()) || enty.getIdCardNo().length() != 18) {
				return error("身份证号码为空或者不等于18个字符!");
			}
			enty.setState(UserAuthenticationStateEnum.INIT.getCode());
	        
			if (StringUtils.isAnyBlank(enty.getIdCardImg1(),enty.getIdCardImg2())) {
				return error("请上传身份证正反面!");
			}
			User user = getLoginUser();//登录后修改认证信息
			if(user == null) {
				Long userId = Long.parseLong(ShiroSession.get("registerUserId"));
				user = userService.get(userId);//注册时提交认证信息
				if(user == null) {
					return error("您还未提交注册信息!");
				}
			}
			enty.setId(user.getId());
			enty.setUpdatePin(user.getAccount());
			SysUser sysuser = new SysUser();
			sysuser.setNo(user.getAccount());
			operateLoger.info(new SysOperateLog(sysuser, user.getId()+"上传身份证", Json.toJSON(enty)));
			userAuthenticationService.add(enty);
			return ok();
		} catch (Exception ex) {
			return error("上传身份证发生异常,请联系管理员!");
		}
	}
	
	//获取认证信息
	@GetMapping("card/get")
	public JsonResult cardGet() {
		User user = getLoginUser();
		if (user == null) {
			return error("用户未登录!");
		}
		Map<String,Object> map = Maps.newHashMap();
		try {
			UserAuthentication code = userAuthenticationService.get(user.getId());
			if (code == null) {
				return error("数据不存在!");
			}
			map.put("data", code);
		} catch (Exception ex) {
			return error("获取认证信息发生异常,请联系管理员!");
		}
		return ok(map);
	}
	
	//设置支付密码
	@RequestMapping(value = "setTradePassword", method = RequestMethod.POST)
	public JsonResult setTradePassword(UserWallet enty,String oldPwd) {
		try {
			User user = getLoginUser();
			if (user == null) {
				return error("用户未登录!");
			}
			
			String pwd = enty.getTradePassword();
			// 如果密码不为空,那么就需要根据salt 重新加密生成加密密码
			if (StringUtils.isBlank(pwd)) {
				return error("密码不能为空!");
			}
			if (pwd.length() > 30) {
				return error("密码超过30个字符!");
			}
			
			UserWallet userWallet = userWalletService.getByUserId(user.getId());
			if(userWallet==null) {
				//初始化用户钱包
				userWallet = new UserWallet();
				userWallet.setId(user.getId());
				userWallet.setSalt(RandomStringUtils.randomNumeric(8));
				userWallet.setTradePassword(MD5Utils.encryptPassword(enty.getTradePassword(), userWallet.getSalt()));
				userWallet.setUsableAmount(BigDecimal.ZERO);
				userWalletService.initAmount(userWallet);
			}else {
				//只有再原始密码为空的情况下,才允许不输入原始密码
				if(StringUtils.isNotBlank(userWallet.getTradePassword()) && StringUtils.isBlank(oldPwd)) {
					return error("请输入原密码!");
				}
				
				if(StringUtils.isNotBlank(userWallet.getTradePassword()) && StringUtils.isNotBlank(oldPwd)) {
					oldPwd = MD5Utils.encryptPassword(oldPwd, userWallet.getSalt());
					if(!oldPwd.equals(userWallet.getTradePassword())) {
						return error("原密码不正确，请输入正确的原密码!");
					}
				}
				pwd = MD5Utils.encryptPassword(pwd, userWallet.getSalt());
				userWallet = new UserWallet();
				userWallet.setId(user.getId());
				userWallet.setTradePassword(pwd);
				userWallet.setUpdatePin(user.getAccount());
				userWalletService.update(userWallet, null,null);
			}
			SysUser sysuser = new SysUser();
			sysuser.setNo(user.getAccount());
			operateLoger.info(new SysOperateLog(sysuser, "用户设置支付密码", Json.toJSON(enty)));
			return ok();
		} catch (Exception ex) {
			return error("用户修改密码时发生异常,请联系管理员!");
		}
	}
	
	//获取用户余额信息
	@GetMapping("userWallet/getAmount")
	public JsonResult userWalletGetAmount() {
		try {
			User user = getLoginUser();
			if (user == null) {
				return error("用户未登录!");
			}
			UserWallet userWallet = userWalletService.getByUserId(user.getId());
			if(userWallet==null) {
				return error("未查询到用户Id("+user.getId()+")的钱包信息");
			}
			return ok(userWallet);
		} catch (Exception ex) {
			return error("获取用户余额时发生异常,请联系管理员!");
		}
	}
	
	//获取资金变动类型选项
	@RequestMapping(value = "changeType/select", method = RequestMethod.GET)
	public JsonResult changeTypeSelect() {
		Map<String,Object> jo = Maps.newHashMap();
		for(UserWalletLogTypeEnum e : UserWalletLogTypeEnum.values()){
			jo.put(String.valueOf(e.getCode()), e.getName());
		}
		return ok(jo);
	}
	
	//查询可购买的产品
	@RequestMapping(value = "machine/query", method = RequestMethod.POST)
	public JsonResult machineQuery(Machine enty, PageQuery pageQuery) {
		IPage<Machine> page = new Page<Machine>(pageQuery.getPageNo(), pageQuery.getPageSize());
		page = machineService.select(page, enty);
		return toPageJson(page);
	}
	
	//获取产品详情
	@GetMapping("machine/{id}")
	public JsonResult machineGet(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) {
		if (StringUtils.isBlank(id.toString())) {
			return error("错误的参数");
		}
		try {
			Machine machine = machineService.get(id);
			if(machine == null ||YnEnum.NO.getCode() == machine.getYn()) {
				return error("此产品不存在或已经下线!");
			}
			return ok(machine);
		} catch (Exception ex) {
			return error("获取产品详情时发生异常,请联系管理员!");
		}
	}
	
	//获取平台方的钱包地址
	@RequestMapping(value = "usdtWallet/getBase", method = RequestMethod.GET)
	public JsonResult usdtWalletGetBase(String chain) {
		if (StringUtils.isBlank(chain)) {
			return error("错误的参数");
		}
		String address = "";
		if("ERC20".equals(chain)) {
			address = redisUtil.get(Constant.getAddressByERC20());
		}else if("TRC20".equals(chain)) {
			address = redisUtil.get(Constant.getAddressByTRC20());
		}else if("OMNI".equals(chain)) {
			address = redisUtil.get(Constant.getAddressByOMNI());
		}else {
			return error("未知的链名称!");
		}
		if (StringUtils.isBlank(address)) {
			return error("获取钱包地址失败，请联系客服处理!");
		}
		address = address.replace("，", ",");//中文逗号转英文
		int length = address.split(",").length;
		int r = (int)(Math.random()*length);
		return ok(address.split(",")[r]);
	}
	
	//获取USDT汇率
	@RequestMapping(value = "exchangeRate/get", method = RequestMethod.GET)
	public JsonResult getExchangeRate() {
		return ok(redisUtil.get(Constant.getUsdtExchangeRate()));
	}
	
	//获取划转最小数量/手续费率/下限/上限
	@RequestMapping(value = "transferFee/get", method = RequestMethod.GET)
	public JsonResult getTransferFee() {
		Map<String,String> map = new HashMap<String,String>();
		map.put("minTransferCount", redisUtil.get(Constant.getMinTransferCount()));
		map.put("transferRate", redisUtil.get(Constant.getTransferRate()));
		map.put("minTransferAmount", redisUtil.get(Constant.getMinTransferAmount()));
		map.put("maxTransferAmount", redisUtil.get(Constant.getMaxTransferAmount()));
		return ok(map);
	}
	
	//获取提现最小数量/手续费率/下限/上限
	@RequestMapping(value = "cashOutFee/get", method = RequestMethod.GET)
	public JsonResult getCashOutFee() {
		Map<String,String> map = new HashMap<String,String>();
		map.put("minCashOutCount", redisUtil.get(Constant.getMinCashOutCount()));
		map.put("cashOutRate", redisUtil.get(Constant.getCashOutRate()));
		map.put("minCashOutAmount", redisUtil.get(Constant.getMinCashOutAmount()));
		map.put("maxCashOutAmount", redisUtil.get(Constant.getMaxCashOutAmount()));
		return ok(map);
	}
	
	//加密
	@RequestMapping(value = "getCiphertext", method = RequestMethod.POST)
	public JsonResult getCiphertext(String plaintext) {
		if(StringUtils.isBlank(plaintext)) {
			return error("错误的参数!");
		}
		return ok(AesEncodeUtil.vue_ciphertext(plaintext));
	}
	
	//解密
	@RequestMapping(value = "getPlaintext", method = RequestMethod.POST)
	public JsonResult getPlaintext(String ciphertext) {
		if(StringUtils.isBlank(ciphertext)) {
			return error("错误的参数!");
		}
		String value = AesEncodeUtil.vue_plaintext(ciphertext);
		if(StringUtils.isBlank(value)) {
			return error("无法识别的参数!");
		}
		return ok(value);
	}
	
	private JsonResult checkUser(User enty) {
		if (enty == null) {
			return error("错误的参数!");
		}

		if (StringUtils.isBlank(enty.getAccount()) || enty.getAccount().length() > 20) {
			return error("账号为空或者超过20个字符!");
		}

		if (StringUtils.isBlank(enty.getNickName()) || enty.getNickName().length() > 50) {
			return error("昵称为空或者超过50个字符!");
		}
		
		if (StringUtils.isBlank(enty.getNickName()) || enty.getNickName().length() > 50) {
			return error("昵称为空或者超过50个字符!");
		}
			
		return ok();
	}
	
}		