package com.bob.demo.myo2o.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/** 
* @author bob 
* @version 创建时间：2018年8月28日 下午4:11:35 
* 类说明 
*/
@Controller
@RequestMapping("/shopadmin")
public class ProductController {
	@GetMapping("/productmanagement")
	public String productList() {
		return "shopadmin/productmanagement";
	}
	@GetMapping("/productoperational")
	public String productOperational() {
		return "shopadmin/productoperational";
	}
}
