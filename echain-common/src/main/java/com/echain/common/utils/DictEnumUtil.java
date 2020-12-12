package com.echain.common.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.echain.common.enums.MenuTypeEnum;
import com.echain.common.enums.business.NewsInfoStateEnum;
import com.echain.common.enums.business.NewsInfoTypeEnum;
import com.echain.common.enums.business.user.UserAccountTypeEnum;
import com.echain.common.enums.business.user.UserAuthenticationStateEnum;
import com.echain.common.enums.business.user.UserCashOutStateEnum;
import com.echain.common.enums.business.user.UserMachineStateEnum;
import com.echain.common.enums.business.user.UserQuestionEnum;
import com.echain.common.enums.business.user.UserRechargeStateEnum;
import com.echain.common.enums.business.user.UserStateEnum;
import com.echain.common.enums.business.user.UserTypeEnum;
import com.echain.common.enums.business.user.UserWalletLogTypeEnum;

public class DictEnumUtil {

	private static Map<String,Object> dictMap ;
	static {
		dictMap = new HashMap<String, Object>();
		//菜单类型
		dictMap.put("menuType", MenuTypeEnum.toJsonObject());
		//注册类型
		dictMap.put("userAccountType", UserAccountTypeEnum.toJsonObject());
		//用户类型
		dictMap.put("userType", UserTypeEnum.toJsonObject());
		//认证状态
		dictMap.put("userAuthenticationState", UserAuthenticationStateEnum.toJsonObject());
		//问题选项
		dictMap.put("question", UserQuestionEnum.toJsonObject());
		//用户状态
		dictMap.put("userState", UserStateEnum.toJsonObject());
		//提现状态
		dictMap.put("userCashOutState", UserCashOutStateEnum.toJsonObject());
		//日志状态
		dictMap.put("userWalletLogType", UserWalletLogTypeEnum.toJsonObject());
		//充值状态
		dictMap.put("userRechargeState", UserRechargeStateEnum.toJsonObject());
		//用户矿机状态
		dictMap.put("userMachineState", UserMachineStateEnum.toJsonObject());
		//资讯类型
		dictMap.put("newsInfoType", NewsInfoTypeEnum.toJsonObject());
		//资讯状态
		dictMap.put("newsInfoState", NewsInfoStateEnum.toJsonObject());
		
	}
	
	public static Set<String> keys(){
		return dictMap.keySet();
	}
	
	public static Object get(String key) {
		return dictMap.get(key);
	}
}
