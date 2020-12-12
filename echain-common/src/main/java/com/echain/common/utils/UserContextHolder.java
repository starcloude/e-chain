package com.echain.common.utils;

import com.echain.common.beans.UserContext;

public class UserContextHolder {
	private static ThreadLocal<UserContext> userThreadLocal = new ThreadLocal<UserContext>();
	
	public static UserContext getUserContext(){
		return userThreadLocal.get();
	}
	
	public static void setUserContexct(UserContext user){
		userThreadLocal.set(user);
	}
	
	public static void remove(){
		userThreadLocal.remove();
	}
}