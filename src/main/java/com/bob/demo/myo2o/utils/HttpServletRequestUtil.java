package com.bob.demo.myo2o.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author bob
 * @version 创建时间：2018年8月21日 上午9:03:29 类说明 获取请求参数中的值
 */
public class HttpServletRequestUtil {

	public static int getInt(HttpServletRequest request, String param) {
		try {
			return Integer.decode(request.getParameter(param));
		}catch(Exception e) {
			return -1;
		}
	}
	public static long getLong(HttpServletRequest request, String param) {
		try {
			return Long.valueOf(request.getParameter(param));
		}catch(Exception e) {
			return -1L;
		}
	}
	public static double getDouble(HttpServletRequest request, String param) {
		try {
			return Double.valueOf(request.getParameter(param));
		}catch(Exception e) {
			return -1d;
		}
	}
	public static boolean getBoolean(HttpServletRequest request, String param) {
		try {
			return Boolean.valueOf(request.getParameter(param));
		}catch(Exception e) {
			return false;
		}
	}
	public static String getString(HttpServletRequest request,String param) {
		try {
			String value = request.getParameter(param);
			if(value!=null) {
				value = value.trim();
			}
			if("".equals(value)) {
				value=null;
			}
			return value;
		}catch(Exception e) {
			return null;
		}
	}
}
