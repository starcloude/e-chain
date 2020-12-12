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
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.SerializationUtils;

import com.echain.service.common.RedisUtil;


public class RedisSessionDao extends AbstractSessionDAO {

	private static Logger logger = LoggerFactory.getLogger(RedisSessionDao.class);

	/**
	 * 过期时间 分钟
	 */
	private int expire;

	@Resource
	private RedisUtil redisUtil;

	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId = this.generateSessionId(session);
		this.assignSessionId(session, sessionId);
		this.saveSession(session);
		return sessionId;
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		if (sessionId == null) {
			logger.error("session id is null");
			return null;
		}
		logger.debug("Read Redis.SessionId="
				+ new String(getKey(ShiroRedisConstant.SHIRO_REDIS_SESSION_PRE, sessionId.toString())));
		logger.info("Session.classLoader {}", Session.class.getClassLoader().toString());
		logger.info("SimpleSession.classLoader {}", SimpleSession.class.getClassLoader().toString());
		Session session = (SimpleSession) SerializationUtils.deserialize(
				redisUtil.getCache(getKey(ShiroRedisConstant.SHIRO_REDIS_SESSION_PRE, sessionId.toString())));
		return session;
	}

	@Override
	public void update(Session session) throws UnknownSessionException {
		this.saveSession(session);
	}

	int i = 0;

	public void saveSession(Session session) {
		if (session == null || session.getId() == null) {
			logger.error("session or session id is null");
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
				logger.error("getBytes error:" + ex.getMessage());
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
			logger.error("session or session id is null");
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
//        Set<String> keys = redisUtil.keys(ShiroRedisConstant.UID_PRE + uid);
//        if (keys != null && keys.size() > 0) {
//            return true;	
//        }
//        return false;
	}

	public void setExpire(int expire) {
		this.expire = expire;
	}
}