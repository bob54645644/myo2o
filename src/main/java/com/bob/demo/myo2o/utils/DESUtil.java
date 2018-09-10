package com.bob.demo.myo2o.utils;
/** 
* @author bob 
* @version 创建时间：2018年9月9日 下午10:52:16 
* 类说明  DES加密解密工具类
*/

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

public class DESUtil {
	//密钥key
	private static Key key;
	
	private static String KEY_STR= "myKey";
	private static String CHARSETNAME= "UTF-8";
	private static String ALGORITHM = "DES";
	
	//初始化key
	static {
		try {
			//生成DES算法对象
			KeyGenerator generator = KeyGenerator.getInstance(ALGORITHM);
			
			//运用sha1安全策略
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			//设置密钥种子
			secureRandom.setSeed(KEY_STR.getBytes());
			
			//初始化算法对象
			generator.init(secureRandom);
			//生成key
			key = generator.generateKey();
			generator = null;
		}catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	//获取加密后信息
	public static String getEncryptString(String str) {
		//基于base64 编码，接受byte[]并转换成string
		Encoder encoder = Base64.getEncoder();
		try {
			//按utf-8编码
			byte[] bytes = str.getBytes(CHARSETNAME);
			//获取加密对象
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			//初始化密码信息
			cipher.init(Cipher.ENCRYPT_MODE, key);
			
			//加密
			byte[] doFinal = cipher.doFinal(bytes);
			
			return encoder.encodeToString(doFinal);
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	//获取解密后的信息
	public static String getDecryptString(String str) {
		//基于base64解码
		Decoder decoder = Base64.getDecoder();
		
		try {
			byte[] bytes = decoder.decode(str);
			
			//获取解密对象
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			//初始化解密信息
			cipher.init(Cipher.DECRYPT_MODE, key);
			
			//解密
			byte[] doFinal = cipher.doFinal(bytes);
			
			return new String(doFinal);
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void main(String[] args) {
		String encryptString = getEncryptString("root");
		System.out.println(encryptString);
		
		String decryptString = getDecryptString(encryptString);
		System.out.println(decryptString);
	}
}
