package com.echain.web.controller.common;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.echain.common.beans.JsonResult;
import com.echain.common.utils.DictEnumUtil;
import com.google.common.collect.Maps;

@RestController
@RequestMapping(value = "/api/common/")
public class CommonController {

	@RequestMapping(value = "/all")
	public JsonResult list() {
		return JsonResult.OK(DictEnumUtil.keys());
	}
	
	@RequestMapping(value = "/get")
	public JsonResult get(String codes) {
		if(StringUtils.isBlank(codes)) {
			return JsonResult.OK();
		}
		Map<String,Object> data = Maps.newHashMap();
		for(String code : codes.split(",")) {
			if(StringUtils.isNotBlank(code)) {
				data.put(code, DictEnumUtil.get(code));
			}
		}
		return JsonResult.OK(data);
	}
}
