package com.echain.domain.business;

import com.baomidou.mybatisplus.annotation.TableName;
import com.echain.domain.CreateTimeEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("v_news_info")
public class NewsInfo extends CreateTimeEntity {
	
	/**
	 * type
	 * @see{NewsInfoTypeEnum}
	 */
	private Integer type;
	
	/**
	 * 标题
	 */
	private String title;
	
	/**
	 * 作者
	 */
	private String author;
	
	/**
	 * 简介
	 */
	private String introduce;
	
	/**
	 * 内容
	 */
	private String content;
	
	/**
	 * 链接
	 */
	private String link;
	
	/**
	 * 状态
	 * @see{NewsInfoStateEnum}
	 */
	private Integer state;
}
