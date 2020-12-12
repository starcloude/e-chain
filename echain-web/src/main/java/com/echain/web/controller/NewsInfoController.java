package com.echain.web.controller;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.echain.common.beans.JsonResult;
import com.echain.common.beans.PageJsonResult;
import com.echain.common.beans.PageQuery;
import com.echain.common.enums.business.NewsInfoStateEnum;
import com.echain.domain.business.NewsInfo;
import com.echain.service.business.NewsInfoService;

@RestController
@RequestMapping("/api/newsInfo/")
public class NewsInfoController extends AbsSupperController {
	
	@Resource
	private NewsInfoService newsInfoService;
	
	//查询资讯记录
	@RequestMapping(value = "query", method = RequestMethod.POST)
	public PageJsonResult queryList(NewsInfo enty, PageQuery pageQuery) {
		PageJsonResult result = new PageJsonResult(true);
		result = new PageJsonResult(true);
		result.setCurrent(pageQuery.getPageNo());
		result.setSize(pageQuery.getPageSize());
		
		enty.setState(NewsInfoStateEnum.PASS.getCode());
		IPage<NewsInfo> page = new Page<NewsInfo>(pageQuery.getPageNo(), pageQuery.getPageSize());
		page = newsInfoService.select(page, enty);
		result.setResult(page.getRecords());
		result.setTotal(page.getTotal());
		return result;
	}
	
	//获取资讯记录详情
	@GetMapping("{id}")
	public JsonResult getDetail(@PathVariable Long id) {
		if (StringUtils.isBlank(id.toString())) {
			return JsonResult.Error("错误的参数");
		}
		NewsInfo enty = newsInfoService.get(id);
		return JsonResult.OK(enty);
	}
	
}		