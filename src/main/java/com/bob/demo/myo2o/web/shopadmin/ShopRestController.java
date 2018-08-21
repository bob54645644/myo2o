package com.bob.demo.myo2o.web.shopadmin;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;

import com.bob.demo.myo2o.dto.ShopExecution;
import com.bob.demo.myo2o.entity.Area;
import com.bob.demo.myo2o.entity.PersonInfo;
import com.bob.demo.myo2o.entity.Shop;
import com.bob.demo.myo2o.entity.ShopCategory;
import com.bob.demo.myo2o.enums.ShopStateEnum;
import com.bob.demo.myo2o.service.AreaService;
import com.bob.demo.myo2o.service.ShopCategoryService;
import com.bob.demo.myo2o.service.ShopService;
import com.bob.demo.myo2o.utils.HttpServletRequestUtil;
import com.bob.demo.myo2o.utils.ImageHolder;
import com.bob.demo.myo2o.utils.VerifyCodeUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	
	//处理图片流
	@Autowired
	private MultipartResolver multipartResolver;

	// 注册店铺的初始化信息
	@GetMapping("/registershopinfo")
	public Map<String, Object> registerShopInfo() {
		Map<String, Object> modelMap = new HashMap<>();
		try {
			// 获取区域信息
			List<Area> areaList = areaService.getAreaList();
			modelMap.put("areaList", areaList);
			// 获取所有二级店铺类别
			ShopCategory shopCategoryCondition = new ShopCategory();
			List<ShopCategory> shopCategoryList = shopCategoryService.getShopCategory(shopCategoryCondition);
			modelMap.put("shopCategoryList", shopCategoryList);
			modelMap.put("success", true);
		}catch(Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}

	// 注册店铺
	@PostMapping("/registershop")
	public Map<String,Object> registerShop(HttpServletRequest request) throws IOException{
		Map<String,Object> modelMap = new HashMap<>();
		//添加验证码验证
		if(!VerifyCodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "验证码错误！");
			return modelMap;
		}
		//必须有personInfo才能注册店铺
		PersonInfo user = null;
		try {
			user = (PersonInfo)request.getSession().getAttribute("user");
			if(user==null || user.getPersonId()==null) {
				modelMap.put("success", false);
				modelMap.put("errMsg", "请登录");
				return modelMap;
			}
		}catch(Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		//得到shop
		Shop shop =null;
		String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
		if(shopStr==null) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "店铺信息为空");
			return modelMap;
		}
		try {
			ObjectMapper mapper = new ObjectMapper();
			shop = mapper.readValue(shopStr, Shop.class);
			//设置必要的信息
			shop.setOwner(user);
			shop.setCreateTime(new Date());
			shop.setLastEditTime(new Date());
			shop.setEnableStatus(0);
			shop.setPriority(0);
		}catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "格式不正确");
			return modelMap;
		}
		//处理缩略图
		ImageHolder imageHolder = null;
		if(multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest resolveMultipart = multipartResolver.resolveMultipart(request);
			MultipartFile thumbnailImg = resolveMultipart.getFile("thumbnailImg");
			if(thumbnailImg==null) {
				modelMap.put("success", false);
				modelMap.put("errMsg", "缩略图为空");
				return modelMap;
			}
			imageHolder = new ImageHolder(thumbnailImg.getInputStream(), thumbnailImg.getOriginalFilename());
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "缩略图不能为空");
			return modelMap;
		}
		try {
			ShopExecution se = shopService.addShop(shop, imageHolder);
			if(se.getState()==ShopStateEnum.SUCCESS.getState()) {
				modelMap.put("success", true);
			}else {
				modelMap.put("success", false);
				modelMap.put("errMsg", "新增店铺失败");
			}
		}catch(Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}
	// 编辑店铺的初始化信息
	//编辑店铺
	// 获取店铺列表
	@GetMapping("/getshoplist")
	public Map<String, Object> getShopList(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		// 从session中获取personinfo，前期先硬编码创建
		PersonInfo user = new PersonInfo();
		user.setPersonId(1L);
		user.setPersonName("xiu");
		request.getSession().setAttribute("user", user);

		PersonInfo nowUser = (PersonInfo) request.getSession().getAttribute("user");
		if (nowUser == null || nowUser.getPersonId() == null) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请登录");
			return modelMap;
		}

		Shop shopCondition = new Shop();
		shopCondition.setOwner(nowUser);
		ShopExecution se = shopService.getShopListByCondition(shopCondition, 0, 100);
		modelMap.put("success", true);
		modelMap.put("shopList", se.getShopList());
		modelMap.put("count", se.getCount());
		modelMap.put("username", nowUser.getPersonName());
		return modelMap;
	}
}
