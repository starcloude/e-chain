package com.echain.common.utils;

import java.util.Set;
import java.util.SortedMap;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApiSignUtil {

	/**
	 * 校验的key
	 */
	private static final String signKey = "sign";

	/**
	 * 不参与签名的key
	 */
	private static final Set<String> excludeParam = Sets.newHashSet("sign");

	private static final String publicSalt = "API";

	/**
	 * @param params 所有的请求参数都会在这里进行排序加密
	 * @return 得到签名
	 */
	public static String getSign(SortedMap<String, String> params, String salt) {
		return getSign(params, "", salt);
	}
	
	/**
	 * 新增加密方式
	 * @param params
	 * @param splitor 参数分隔符 md5(公钥+分隔符+参数1值1+分隔符+...参数N值N+分隔符+私盐)
	 * @param salt
	 * @return
	 */
	public static String getSign(SortedMap<String, String> params,String splitor, String salt) {
		StringBuilder sb = new StringBuilder(publicSalt).append(splitor);
		// 去掉空,去掉sign
		params.entrySet().forEach(entry -> {
			// 拼装参数,排除sign
			if (!excludeParam.contains(entry.getKey())) {
				if (StringUtils.isNotBlank(entry.getKey()) && StringUtils.isNotBlank(entry.getValue())) {
					sb.append(entry.getKey()).append(entry.getValue()).append(splitor);
				}
			}
		});
		String sourceCode = sb.append(salt).toString();
		System.out.println(sourceCode);
		if(log.isDebugEnabled()) {
			log.debug(sourceCode);
		}
		return DigestUtils.md5Hex(sourceCode).toUpperCase();
	}
	
	/**
	 * @param params 所有的请求参数都会在这里进行排序加密
	 * @return 验证签名结果
	 */
	public static boolean verifySign(SortedMap<String, String> params, String salt) {
		return verifySign(params, "", salt);
	}
	
	public static boolean verifySign(SortedMap<String, String> params,String splitor, String salt) {
		if (MapUtils.isEmpty(params) || params.keySet().size() <2 || StringUtils.isAnyBlank(params.get(signKey),params.get("ts"))) {
			return false;
		}
		String ts = params.get("ts");
		Long nTs = null;
		try {
			nTs = Long.valueOf(ts);
		}catch(Exception  ex){
			log.error("无法将 {} 转换为时间戳!",ts);
			return false;
		}
		
		long diff = Math.abs(System.currentTimeMillis() - nTs )/1000;
		if(diff>20) {
			log.error("当前时间:{} 请求时间戳:{} 已经过期,请重新提交!",nTs,System.currentTimeMillis());
			return false;
		}
		String sign = getSign(params,splitor, salt);
		return params.get(signKey).equals(sign);
	}

	public static void main(String[] args) {
		SortedMap<String, String> params = Maps.newTreeMap();
		params.put("url", "www.uorz.cn");
		params.put("sex", "男");
		params.put("name", "周碧银");
		params.put("age", "15");
		params.put("ts", System.currentTimeMillis()+"");
		
		String enc = getSign(params,"&","abvc");
		params.put("sign", enc);
		System.out.println(enc);
		System.out.println(verifySign(params,"&", "abvc"));
		
		// 75E030D911B379104E1EA72BC268F6AE8A68BD22
		// F4280E440CB6F9E17D5F6176A8C1B900
	}
}
