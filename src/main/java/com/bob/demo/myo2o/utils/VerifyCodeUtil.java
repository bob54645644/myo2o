package com.bob.demo.myo2o.utils;

import javax.servlet.http.HttpServletRequest;

import com.google.code.kaptcha.Constants;

/**
 * @author bob
 * @version 创建时间：2018年8月21日 下午8:28:56 类说明 验证码工具类
 */
public class VerifyCodeUtil {
	public static boolean checkVerifyCode(HttpServletRequest request) {
		try {
			// 用户输入的验证码
			String userCode = HttpServletRequestUtil.getString(request, "verifyCode");
			// 真正的验证码
			String realCode = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
			if (userCode.toLowerCase().equals(realCode.toLowerCase())) {
				return true;
			} else
				return false;
		} catch (Exception e) {
			return false;
		}
	}
}
