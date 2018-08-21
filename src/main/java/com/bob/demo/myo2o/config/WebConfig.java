package com.bob.demo.myo2o.config;


import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.google.code.kaptcha.servlet.KaptchaServlet;

/** 
* @author bob 
* @version 创建时间：2018年8月21日 下午8:37:17 
* 类说明 
*/
@Configuration
public class WebConfig implements WebMvcConfigurer{
	//验证码
	@Bean
	public ServletRegistrationBean<KaptchaServlet> servletRegistrationBean(){
		ServletRegistrationBean<KaptchaServlet> bean = new ServletRegistrationBean<>(new KaptchaServlet(),"/kaptcha");
		bean.addInitParameter("kaptcha.border", "no");// 无边框
		bean.addInitParameter("kaptcha.textproducer.font.color", "red"); // 字体颜色
		bean.addInitParameter("kaptcha.image.width", "135");// 图片宽度
		bean.addInitParameter("kaptcha.textproducer.char.string", "ACDEFHKPRSTWX345679");// 使用哪些字符生成验证码
		bean.addInitParameter("kaptcha.image.height", "50");// 图片高度
		bean.addInitParameter("kaptcha.textproducer.font.size", "43");// 字体大小
		bean.addInitParameter("kaptcha.noise.color", "black");// 干扰线的颜色
		bean.addInitParameter("kaptcha.textproducer.char.length", "4");// 字符个数
		bean.addInitParameter("kaptcha.textproducer.font.names", "Arial");// 字体
		return bean;
	}
}
