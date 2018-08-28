package com.bob.demo.myo2o.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/** 
* @author bob 
* @version 创建时间：2018年8月20日 下午9:34:55 
* 类说明 
*/
@Controller
@RequestMapping("/shopadmin")
public class ShopController {
	//店铺列表
	@GetMapping("/shoplist")
	public String shopList() {
		return "shopadmin/shoplist";
	}
	//注册、修改店铺
	@GetMapping("/shopoperational")
	public String shopOperational() {
		return "shopadmin/shopoperational";
	}
	//店铺管理
	@GetMapping("/shopmanagement")
	public String shopManagement() {
		return "shopadmin/shopmanagement";
	}
	//商品类别管理
	@GetMapping("/productcategorymanagement")
	public String productCategoryManagement() {
		return "shopadmin/productcategorymanagement";
	}
}
