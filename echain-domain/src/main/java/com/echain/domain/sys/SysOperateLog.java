package com.echain.domain.sys;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.echain.domain.IDEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("sys_operate_log")
public class SysOperateLog extends IDEntity {

	public SysOperateLog() {

	}

	public SysOperateLog(SysUser user, String node, String memo) {
		this.createPin = user.getNo();
		this.memo = memo;
		this.ip = user.getIp();
		this.node = node;
		this.createTime = new Date();
	}

	private String level;
	
	private String node;
	
	private String memo;
	
	private String ip;

	/**
	 * 创建时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	/**
	 * 创建人
	 */
	private String createPin;
	
	// 创建时间截止
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@TableField(exist = false)
	private Date createTimeEnd;
}
