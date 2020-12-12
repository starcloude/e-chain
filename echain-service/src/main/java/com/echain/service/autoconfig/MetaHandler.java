package com.echain.service.autoconfig;

import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
@Component
public class MetaHandler implements MetaObjectHandler {

	/**
	 * 新增数据执行
	 * 
	 * @param metaObject
	 */
	@Override
	public void insertFill(MetaObject metaObject) {
//		if(metaObject.hasSetter("createTime")) {
//			this.setFieldValByName("createTime", new Date(), metaObject);
//		}
		
		if(metaObject.hasSetter("yn")) {
			this.setFieldValByName("yn", 0, metaObject);
		}
	}

	@Override
	public void updateFill(MetaObject metaObject) {
//		if(metaObject.hasSetter("updateTime")) {
//			this.setFieldValByName("updateTime", new Date(), metaObject);
//		}
	}
}