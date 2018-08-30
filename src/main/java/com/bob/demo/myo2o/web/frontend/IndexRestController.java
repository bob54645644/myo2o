package com.bob.demo.myo2o.web.frontend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bob.demo.myo2o.dto.ShopExecution;
import com.bob.demo.myo2o.entity.HeadLine;
import com.bob.demo.myo2o.entity.ProductCategory;
import com.bob.demo.myo2o.entity.Shop;
import com.bob.demo.myo2o.entity.ShopCategory;
import com.bob.demo.myo2o.service.HeadLineService;
import com.bob.demo.myo2o.service.ShopCategoryService;
import com.bob.demo.myo2o.service.ShopService;
import com.bob.demo.myo2o.utils.HttpServletRequestUtil;

/** 
* @author bob 
* @version 创建时间：2018年8月29日 下午9:01:00 
* 类说明 
*/
@RestController
@RequestMapping("/frontend")
public class IndexRestController {
	@Autowired
	private HeadLineService headLineService;
	@Autowired
	private ShopCategoryService shopCategoryService;
	
	@Autowired
	private ShopService shopService;
	
	//得到头条信息和一级店铺类别
	@GetMapping("/getheadlinelist")
	public Map<String,Object> getHeadLineList(){
		Map<String,Object> modelMap = new HashMap<>();
		
		//头条
		HeadLine headLineCondition = new HeadLine();
		headLineCondition.setEnableStatus(1);
		
		try {
			List<HeadLine> headLineList = headLineService.getHeadLineByCondition(headLineCondition);
			//一级分类
			List<ShopCategory> shopCategoryList = shopCategoryService.getShopCategory(null);
			modelMap.put("success", true);
			modelMap.put("headLineList", headLineList);
			modelMap.put("shopCategoryList", shopCategoryList);
		}catch(Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}
	//店铺列表
	@GetMapping("/getshoplist")
	public Map<String,Object> getShopList(HttpServletRequest request){
		Map<String,Object> modelMap = new HashMap<>();
		//获取parentId，有则是一级店铺类别，没有则是二级店铺类别
		//获取shopCategoryId
		long parentId = HttpServletRequestUtil.getLong(request, "parentId");
		long shopCategoryId = HttpServletRequestUtil.getLong(request, "shopCategoryId");
		ShopCategory shopCategoryCondition = new ShopCategory();
		if(parentId >-1) {
			ShopCategory parent = new ShopCategory();
			parent.setShopCategoryId(parentId);
			shopCategoryCondition.setParent(parent);
		}else if(shopCategoryId >-1) {
			shopCategoryCondition.setShopCategoryId(shopCategoryId);
		}
		
		
		Shop shopCondition = new Shop();
		shopCondition.setShopCategory(shopCategoryCondition);
		ShopExecution se = shopService.getShopListByCondition(shopCondition, 0, 100);
		modelMap.put("success", true);
		modelMap.put("shopList", se.getShopList());
		modelMap.put("count", se.getCount());
		return modelMap;
	}

}
