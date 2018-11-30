package com.pengblog.utils;

import org.apache.logging.log4j.LogManager;

public class LogUtil {
	
	public static void logInfo(Class clazz, String msg) {
		 LogManager.getLogger(clazz).info(msg);
	}
		
}
