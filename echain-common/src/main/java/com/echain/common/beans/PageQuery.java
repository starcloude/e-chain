package com.echain.common.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Setter;

@Setter
public class PageQuery {

	@JsonIgnore
	protected Integer pageNo;

	@JsonIgnore
	protected Integer pageSize;

	public Integer getPageNo() {
		return pageNo == null || pageNo <= 0 ? 1 : pageNo;
	}

	public Integer getPageSize() {
		return pageSize == null || pageSize <= 0 || pageSize > 10000 ? 10 : pageSize;
	}

	/**
	 * 获取开始下标  (pageNo - 1 ) * pageSize
	 * @return
	 */
	@JsonIgnore
	public Integer getStart() {
		return (getPageNo() - 1) * getPageSize();
	}

	/**
	 * 获取截止下标  (pageNo) * pageSize
	 * @return
	 */
	@JsonIgnore
	public Integer getEnd() {
		return getPageNo() * getPageSize();
	}
}
