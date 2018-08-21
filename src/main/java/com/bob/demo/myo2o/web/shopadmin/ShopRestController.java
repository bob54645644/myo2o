package com.bob.demo.myo2o.web.shopadmin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bob.demo.myo2o.dto.ShopExecution;
import com.bob.demo.myo2o.entity.Area;
import com.bob.demo.myo2o.entity.PersonInfo;
import com.bob.demo.myo2o.entity.Shop;
import com.bob.demo.myo2o.entity.ShopCategory;
import com.bob.demo.myo2o.service.AreaService;
import com.bob.demo.myo2o.service.ShopCategoryService;
import com.bob.demo.myo2o.service.ShopService;

/**
 * @author bob
 * @version 创建时间：2018年8月20日 下午9:34:40 类说明
 */
@RestController
@RequestMapping("/shopadmin")
public class ShopRestController {
	@Autowired
	private ShopService shopService;
	@Autowired
	private AreaService areaService;
	@Autowired
	private ShopCategoryService shopCategoryService;

	// 注册店铺的初始化信息
	 @GetMapping("/registershopinfo")
	 public Map<String,Object> registerShopInfo(){
		 Map<String,Object> modelMap = new HashMap<>();
		 
		 return null;
	 }
	// 注册店铺
	 //编辑店铺的初始化信息
	// 获取店铺列表
	@GetMapping("/getshoplist")
	public Map<String, Object> getShopList(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		// 从session中获取personinfo，前期先硬编码创建
		PersonInfo user = new PersonInfo();
		user.setPersonId(1L);
		request.getSession().setAttribute("user", user);

		PersonInfo nowUser = (PersonInfo)request.getSession().getAttribute("user");
		if(nowUser==null || nowUser.getPersonId()==null) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请登录");
			return modelMap;
		}
		//注册时用，写错地方
//		// 获取区域信息
//		List<Area> areaList = areaService.getAreaList();
//		modelMap.put("areaList", areaList);
//		// 获取所有二级店铺类别
//		ShopCategory shopCategoryCondition = new ShopCategory();
//		List<ShopCategory> shopCategoryList = shopCategoryService.getShopCategory(shopCategoryCondition);
//		modelMap.put("shopCategoryList", shopCategoryList);
//		modelMap.put("success", true);
//		return modelMap;
		Shop shopCondition = new Shop();
		shopCondition.setOwner(nowUser);
		ShopExecution se = shopService.getShopListByCondition(shopCondition, 0, 100);
		modelMap.put("success", true);
		modelMap.put("shopList", se.getShopList());
		modelMap.put("count", se.getCount());
		return modelMap;
	}
}
