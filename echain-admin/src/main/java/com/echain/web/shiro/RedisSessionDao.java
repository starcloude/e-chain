package com.echain.web.shiro;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

//import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.util.SerializationUtils;

import com.echain.service.common.RedisUtil;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RedisSessionDao extends AbstractSessionDAO {

	/**
	 * 过期时间 分钟
	 */
	private int expire;

	@Resource
	private RedisUtil redisUtil;
	
	private LoadingCache<String, Session> sessionCache = CacheBuilder.newBuilder().maximumSize(20000)
			.expireAfterWrite(10, TimeUnit.SECONDS).build(new CacheLoader<String,Session>() {
				public Session load(String sessionId) {
					try {
						return readSessionFromRedis(sessionId);
					} catch (Exception ex) {
						log.error("Role.缓存 发生异常! ", ex);
						return null;
					}
				}
			});
	
	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId = this.generateSessionId(session);
		this.assignSessionId(session, sessionId);
		this.saveSession(session);
		return sessionId;
	}

	/**
	 * 从缓存中获取session
	 * 
	 * @param sessionId
	 * @return
	 */
	private Session readSessionFromRedis(String sessionId) {
		log.debug("Read Redis.SessionId= {}",new String(getKey(ShiroRedisConstant.SHIRO_REDIS_SESSION_PRE, sessionId.toString())));
		return (SimpleSession) SerializationUtils.deserialize(
				redisUtil.getCache(getKey(ShiroRedisConstant.SHIRO_REDIS_SESSION_PRE, sessionId)));
	}
	
	@Override
	protected Session doReadSession(Serializable sessionId) {
		if (sessionId == null) {
			log.error("session id is null");
			return null;
		}
		try {
			return sessionCache.get(sessionId.toString());
		}catch(Exception ex) {
			return readSessionFromRedis(sessionId.toString());
		}
	}

	@Override
	public void update(Session session) throws UnknownSessionException {
		if (session instanceof ValidatingSession && !((ValidatingSession) session).isValid()) {
            return;
        }
		this.saveSession(session);
	}

	int i = 0;

	public void saveSession(Session session) {
		if (session == null || session.getId() == null) {
			log.error("session or session id is null");
			return;
		}
		session.setTimeout(expire * 1000 * 60);
		int timeout = expire * 60;

		// 保存用户会话
		redisUtil.setEx(this.getKey(ShiroRedisConstant.SHIRO_REDIS_SESSION_PRE, session.getId().toString()),
				SerializationUtils.serialize(session), timeout);
		// 获取用户id
		String uid = getUserId(session);
		if (StringUtils.isNotBlank(uid)) {
			// 保存用户会话对应的UID
			try {
				redisUtil.setEx(this.getKey(ShiroRedisConstant.SHIRO_SESSION_PRE, session.getId().toString()), uid,
						timeout, TimeUnit.SECONDS);
				// 保存在线UID
				redisUtil.setEx(this.getKey(ShiroRedisConstant.UID_PRE, uid), ("online" + (i++)), timeout,
						TimeUnit.SECONDS);
			} catch (Exception ex) {
				log.error("getBytes error:" + ex.getMessage());
			}
		}
	}

	public String getUserId(Session session) {
		SimplePrincipalCollection pricipal = (SimplePrincipalCollection) session
				.getAttribute("org.apache.shiro.subject.support.DefaultSubjectContext_PRINCIPALS_SESSION_KEY");
		if (null != pricipal) {
			return pricipal.getPrimaryPrincipal().toString();
		}
		return null;
	}

	public String getKey(String prefix, String keyStr) {
		return prefix + keyStr;
	}

	@Override
	public void delete(Session session) {
		if (session == null || session.getId() == null) {
			log.error("session or session id is null");
			return;
		}

		// 删除用户会话
		redisUtil.delete(this.getKey(ShiroRedisConstant.SHIRO_REDIS_SESSION_PRE, session.getId().toString()));
		// 获取缓存的用户会话对应的UID
		String uid = redisUtil.get(this.getKey(ShiroRedisConstant.SHIRO_SESSION_PRE, session.getId().toString()));
		// 删除用户会话sessionid对应的uid
		redisUtil.delete(this.getKey(ShiroRedisConstant.SHIRO_SESSION_PRE, session.getId().toString()));
		// 删除在线uid
		redisUtil.delete(this.getKey(ShiroRedisConstant.UID_PRE, uid));
		// 删除用户缓存的角色
		redisUtil.delete(this.getKey(ShiroRedisConstant.ROLE_PRE, uid));
		// 删除用户缓存的权限
		redisUtil.delete(this.getKey(ShiroRedisConstant.PERMISSION_PRE, uid));
		
		//删除
		sessionCache.invalidate(session.getId());
	}

	@Override
	public Collection<Session> getActiveSessions() {
		Set<Session> sessions = new HashSet<>();

		Set<String> keys = redisUtil.keys(ShiroRedisConstant.SHIRO_REDIS_SESSION_PRE + "*");
		if (keys != null && keys.size() > 0) {
			for (String key : keys) {
				Session s = (Session) SerializationUtils.deserialize(redisUtil.getCache(key));
				sessions.add(s);
			}
		}
		return sessions;
	}

	/**
	 * 当前用户是否在线
	 *
	 * @param uid 用户id
	 * @return
	 */
	public boolean isOnLine(String uid) {
		String value = redisUtil.get(ShiroRedisConstant.UID_PRE + uid);
		return StringUtils.isNotBlank(value);
	}

	public void setExpire(int expire) {
		this.expire = expire;
	}
}