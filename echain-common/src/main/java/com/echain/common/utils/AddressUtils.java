package com.echain.common.utils;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;

public class AddressUtils {

	public static String myIp = null;
	static {
		myIp = getServerIp();
	}

	/**
	 * 获取服务器的IP
	 * 
	 * @return
	 */
	public static String getServerIp() {
		try {
			InetAddress address = InetAddress.getLocalHost();
			return address.getHostAddress();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * 进程+IP
	 * 
	 * @return
	 */
	public static String getProcessId() {
		return ManagementFactory.getRuntimeMXBean().getName();
	}
}