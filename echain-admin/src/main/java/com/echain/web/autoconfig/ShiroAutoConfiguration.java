package com.echain.web.autoconfig;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.echain.web.shiro.CustomRealm;
import com.echain.web.shiro.CustomUserFilter;
import com.echain.web.shiro.RedisSessionDao;
import com.echain.web.shiro.ShiroSessionManager;

@Configuration
public class ShiroAutoConfiguration {

	@Value(value="${shiro.session.timeout:60}")
	private Integer sessionTimeOut;
	
	@Bean(name = "securityManager")
	public SecurityManager securityManager(CustomRealm customRealm,DefaultWebSessionManager sessionManager) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		// 配置 SecurityManager，并注入 shiroRealm
		securityManager.setRealm(customRealm);
		// 配置 rememberMeCookie
		
//		securityManager.setRememberMeManager(rememberMeManager());
		// 配置 缓存管理类 cacheManager
//		securityManager.setCacheManager(cacheManager());
		
		//session 管理
		securityManager.setSessionManager(sessionManager);
		return securityManager;
	}
	
	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		// 设置 securityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		// .setSecurityManager(securityManager);
		// 登录的 url
//		shiroFilterFactoryBean.setLoginUrl("/index/#/login");
		// 登录成功后跳转的 url
//		shiroFilterFactoryBean.setSuccessUrl("/index");
		// 未授权 url
//		shiroFilterFactoryBean.setUnauthorizedUrl("/403");
		
		 Map<String, Filter> filterMap = new HashMap<>(1);
	     //定义拦截器名称
		 filterMap.put("authc",new CustomUserFilter());
	        
		shiroFilterFactoryBean.setFilters(filterMap);
		
		LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
		
		// 设置免认证 url
//		filterChainDefinitionMap.put("/static/**", "anon");
//		filterChainDefinitionMap.put("/webjars/**", "anon");
//		filterChainDefinitionMap.put("/views/login*", "anon");
//		filterChainDefinitionMap.put("/views/doLogin*", "anon");
//		filterChainDefinitionMap.put("/doLogin*", "anon");
//		filterChainDefinitionMap.put("/logout*", "anon");
//		filterChainDefinitionMap.put("/40*", "anon");
//		filterChainDefinitionMap.put("/50*", "anon");
//		filterChainDefinitionMap.put("/*.ico", "anon");
//		filterChainDefinitionMap.put("/index*/**", "anon");
//		filterChainDefinitionMap.put("/views/layout*", "anon");
//		filterChainDefinitionMap.put("/views/menu*", "anon");
//		filterChainDefinitionMap.put("/captcha/**", "anon");
		filterChainDefinitionMap.put("/api/login*", "anon");
		filterChainDefinitionMap.put("/api/logout*", "anon");
		filterChainDefinitionMap.put("/_debug/**", "anon");
		filterChainDefinitionMap.put("/api/10086/*", "anon");
		filterChainDefinitionMap.put("/api/**", "authc");
//		filterChainDefinitionMap.put("/**", "anon");
		
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}

	// 注入自定义的realm，告诉shiro如何获取用户信息来做登录或权限控制
	@Bean(name="customRealm")
	public CustomRealm customRealm() {
		return new CustomRealm();
	}

	@Bean("sessionManager")
	public DefaultWebSessionManager sessionManager(RedisSessionDao redisSessionDao) {
		ShiroSessionManager sessionManager = new ShiroSessionManager();
		sessionManager.setSessionDAO(redisSessionDao);
		sessionManager.setDeleteInvalidSessions(true);
		//单位毫秒
		sessionManager.setGlobalSessionTimeout(sessionTimeOut*1000);
		sessionManager.setSessionIdCookie(sessionIdCookie());
		sessionManager.setSessionIdUrlRewritingEnabled(false);
		//<!-- 定时检查失效的session -->
		sessionManager.setSessionValidationSchedulerEnabled(true);
		return sessionManager;
	}
	
	private SimpleCookie sessionIdCookie() {
		SimpleCookie  cookie = new SimpleCookie();
		cookie.setPath("/");
		cookie.setName("SHARE_JSESSIONID");
		return cookie;
	}
	
	@Bean(name="redisSessionDao")
	public RedisSessionDao redisSessionDao() {
		RedisSessionDao redisSessionDao = new RedisSessionDao();
		//超时时间
		redisSessionDao.setExpire(sessionTimeOut);
		return redisSessionDao;
	}

	@Bean(name = "lifecycleBeanPostProcessor")
	public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	@Bean
	@DependsOn({ "lifecycleBeanPostProcessor" })
	public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		advisorAutoProxyCreator.setProxyTargetClass(true);
		return advisorAutoProxyCreator;
	}
	
	   /**
	    * 开启aop注解支持
	    * @param securityManager
	    * @return
	    */
	   @Bean
	   public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
	       AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
	       authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
	       return authorizationAttributeSourceAdvisor;
	   }
}
