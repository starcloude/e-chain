package com.echain.domain.business;

import java.util.Date;

import org.apache.ibatis.type.JdbcType;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("v_file")
public class DBFile {

	/**
	 * UUID.replace("-","") 主键ID
	 */
	@TableId(type = IdType.ASSIGN_UUID)
	private String id;

	/**
	 * 文件名称
	 */
	private String name;

	/**
	 * 类型
	 * 
	 * @see{DBFileTypeEnum}
	 */
	private Integer type;

	/**
	 * byte 数据文件
	 */
	@TableField(jdbcType = JdbcType.BLOB)
	private byte[] data;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 创建人
	 */
	private String createPin;
}
