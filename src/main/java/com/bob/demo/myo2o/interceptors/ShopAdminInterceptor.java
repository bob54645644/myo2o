package com.bob.demo.myo2o.interceptors;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.bob.demo.myo2o.entity.PersonInfo;

/** 
* @author bob 
* @version 创建时间：2018年9月2日 下午4:21:25 
* 类说明  shopAdmin拦截器
*/
@Component
public class ShopAdminInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Object userObj = request.getSession().getAttribute("user");
		if(userObj != null) {
			try {
				PersonInfo user = (PersonInfo)userObj;
				if(user !=null && user.getPersonId()!=null && user.getEnableStatus()==1) {
					return true;
				}
			}catch(Exception e) {
				return false;
			}
		}
		//重定向到登录页
		PrintWriter out = response.getWriter();
		out.println("<html><script>");
		out.println("window.open('/shopadmin/login','_self')");
		out.println("</script></html>");
		return false;
	}
	
}
