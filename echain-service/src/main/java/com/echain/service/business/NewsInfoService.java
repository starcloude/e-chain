package com.echain.service.business;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.echain.dao.business.NewsInfoDao;
import com.echain.domain.business.NewsInfo;

@Service
public class NewsInfoService {

	@Autowired
	private NewsInfoDao newsInfoDao;

	/**
	 * 插入
	 * @param enty
	 * @return
	 */
	public Long add(NewsInfo enty) {
		int nCount = newsInfoDao.insert(enty);
		if (nCount == 1) {
			return enty.getId();
		}
		return null;
	}
	/**
	 * 查询总记录数
	 * @param enty
	 * @return
	 */
	public Integer selectCount(NewsInfo enty) {
		return newsInfoDao.selectCount(toWrapper(enty));
	}
	
	/**
	 * 根据实体查询对象信息
	 * @param enty
	 * @return
	 */
	public List<NewsInfo> select(NewsInfo enty) {
		return newsInfoDao.selectList(limitFiled(toWrapper(enty)));
	}
	
	/**
	 * 更新
	 * 
	 * @param enty
	 * @return
	 */
	public int update(NewsInfo enty) {
		int nRst = newsInfoDao.updateById(enty);
		return nRst;
	}
	
	/**
	 * 根据ID获取
	 * 
	 * @param id
	 * @return
	 */
	public NewsInfo get(Long id) {
		return newsInfoDao.selectById(id);
	}
	
	/**
	 * 根据id删除
	 * 
	 * @param userId
	 * @return
	 */
	public int delete(Long id) {
		return newsInfoDao.deleteById(id);
	}
	
	/**
	 * 分页查询
	 * 
	 * @param page
	 * @param enty
	 * @return
	 */
	public IPage<NewsInfo> select(IPage<NewsInfo> page, NewsInfo enty) {
		LambdaQueryWrapper<NewsInfo> query = limitFiled(toWrapper(enty));
		return newsInfoDao.selectPage(page, query);
	}
	
	private LambdaQueryWrapper<NewsInfo> limitFiled(LambdaQueryWrapper<NewsInfo> query) {
		return query.select(NewsInfo::getId,NewsInfo::getType,NewsInfo::getTitle,NewsInfo::getAuthor,NewsInfo::getIntroduce,NewsInfo::getContent,NewsInfo::getLink,NewsInfo::getState,NewsInfo::getCreateTime,NewsInfo::getCreatePin);
	}
	
	/**
	 * 查询条件
	 * @param enty
	 * @return
	 */
	private LambdaQueryWrapper<NewsInfo> toWrapper(NewsInfo enty) {
		return new QueryWrapper<NewsInfo>().lambda()
			// id
			.eq(enty.getId() != null, NewsInfo::getId, enty.getId())
			// type
			.eq(enty.getType() != null, NewsInfo::getType, enty.getType())
			// title
			.like(StringUtils.isNotBlank(enty.getTitle()), NewsInfo::getTitle, enty.getTitle())
			// author
			.like(StringUtils.isNotBlank(enty.getAuthor()), NewsInfo::getAuthor, enty.getAuthor())
			// introduce
			.like(StringUtils.isNotBlank(enty.getIntroduce()), NewsInfo::getIntroduce, enty.getIntroduce())
			// link
			.like(StringUtils.isNotBlank(enty.getLink()), NewsInfo::getLink, enty.getLink())
			// content
			.like(StringUtils.isNotBlank(enty.getContent()), NewsInfo::getContent, enty.getContent())
			// state
			.eq(enty.getState() != null, NewsInfo::getState, enty.getState())
			//ID 倒叙
			.orderByDesc(NewsInfo::getId);
	}
	
}
