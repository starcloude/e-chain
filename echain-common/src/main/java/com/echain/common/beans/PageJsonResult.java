package com.echain.common.beans;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageJsonResult  extends JsonResult{

	public PageJsonResult(){
		this(false);
	}
	
	public PageJsonResult(boolean success){
		this(success,"");
	}
	
	public PageJsonResult(boolean success,String message){
		setSuccess(success);
		setMsg(message);
	}
	
	/**
	 * 总行数
	 */
	private long total = 1;
	
	/**
	 * 当前页数
	 */
	private long current = 1;
	
	/**
	 * 每页显示行数
	 */
	private long size = 10;
	
	/**
	 * 数据
	 */

	public long getPages() {
		if (getSize() == 0) {
	        return 0L;
	    }
	    long pages = getTotal() / getSize();
	    if (getTotal() % getSize() != 0) {
	        pages++;
	    }
	    return pages;
	}
}
