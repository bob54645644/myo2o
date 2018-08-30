package com.bob.demo.myo2o.web.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/** 
* @author bob 
* @version 创建时间：2018年8月18日 下午8:31:38 
* 类说明 
*/
@Controller
@RequestMapping("/frontend")
public class IndexController {
	
	@GetMapping("/index")
	public String index() {
		return "frontend/index";
	}
	@GetMapping("/shoplist")
	public String shopList() {
		return "frontend/shoplist";
	}
}
