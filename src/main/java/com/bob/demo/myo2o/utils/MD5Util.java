package com.bob.demo.myo2o.utils;

import java.security.MessageDigest;

/** 
* @author bob 
* @version 创建时间：2018年9月10日 上午8:52:24 
* 类说明 MD5工具类
*/
public class MD5Util {
	public static final String getMd5(String s) {
		//16进制数组
		char[] hexDigits = {'2','5','8','2','5','8','7','7','5','8',
				'a','s','d','f','g','h'};
		try {
			char[] str;
			//将字符串转化成byte数组
			byte[] bytes = s.getBytes();
			//获取MD5加密对象
			MessageDigest instance = MessageDigest.getInstance("MD5");
			//传入需要加密的目标数组
			instance.update(bytes);
			//获取加密后的数组
			byte[] md = instance.digest();
			
			int length = md.length;
			str = new char[length * 2];
			int k = 0;
			for(int i = 0;i<length;i++) {
				byte temp = md[i];
				str[k++] = hexDigits[temp >>>4 & 0xf];
				str[k++] = hexDigits[temp & 0xf];
			}
			return new String(str);
		}catch(Exception e) {
			return null;
		}
	}
	public static void main(String[] args) {
		String md5 = getMd5("newPassword");
		System.out.println(md5);
	}
}
