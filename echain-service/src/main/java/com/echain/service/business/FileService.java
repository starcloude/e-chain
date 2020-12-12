package com.echain.service.business;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.echain.dao.business.DBFileDao;
import com.echain.domain.business.DBFile;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FileService {

	@Autowired
	private DBFileDao fileDao;
	
	/**
	 * 内部缓存
	 */
	private LoadingCache<String, DBFile> dbFileCache = CacheBuilder.newBuilder().maximumSize(1000)
			.expireAfterWrite(365, TimeUnit.DAYS).build(new CacheLoader<String, DBFile>() {
				public DBFile load(String id) {
					try {
						return get(id);
					} catch (Exception ex) {
						log.error("DbFile.缓存 发生异常! ", ex);
						return null;
					}
				}
			});
	
	/**
	 * 推荐使用 getWithCache 替代
	 * 根据ID查询DBFile
	 * @param id
	 * @return
	 */
	public DBFile get(String id) {
		return fileDao.selectById(id);
	}
	
	/**
	 * 针对DBfile 缓存
	 * @param id
	 * @return
	 */
	public DBFile getWithCache(String id) {
		try {
			return dbFileCache.get(id);
		}catch(Exception ex) {
			log.error("从缓存中提取DBFile发生异常!");
			return get(id);
		}
	}

	/**
	 * 新增图片
	 * @param file
	 * @return
	 */
	public int save(DBFile file) {
		return fileDao.insert(file);
	}
}
