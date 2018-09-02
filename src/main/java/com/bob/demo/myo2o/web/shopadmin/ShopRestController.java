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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;

import com.bob.demo.myo2o.dto.LocalAuthExecution;
import com.bob.demo.myo2o.dto.ProductCategoryExecution;
import com.bob.demo.myo2o.dto.ShopExecution;
import com.bob.demo.myo2o.entity.Area;
import com.bob.demo.myo2o.entity.PersonInfo;
import com.bob.demo.myo2o.entity.ProductCategory;
import com.bob.demo.myo2o.entity.Shop;
import com.bob.demo.myo2o.entity.ShopCategory;
import com.bob.demo.myo2o.enums.LocalAuthStateEnum;
import com.bob.demo.myo2o.enums.ProductCategoryStateEnum;
import com.bob.demo.myo2o.enums.ShopStateEnum;
import com.bob.demo.myo2o.exceptions.ProductOperationalException;
import com.bob.demo.myo2o.service.AreaService;
import com.bob.demo.myo2o.service.LocalAuthService;
import com.bob.demo.myo2o.service.ProductCategoryService;
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

	@Autowired
	private ProductCategoryService productCategoryService;
	@Autowired
	private LocalAuthService localAuthService;

	// 处理图片流
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
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}

	// 注册店铺
	@PostMapping("/registershop")
	public Map<String, Object> registerShop(HttpServletRequest request) throws IOException {
		Map<String, Object> modelMap = new HashMap<>();
		// 添加验证码验证
		if (!VerifyCodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "验证码错误！");
			return modelMap;
		}
		// 必须有personInfo才能注册店铺
		PersonInfo user = null;
		try {
			user = (PersonInfo) request.getSession().getAttribute("user");
			if (user == null || user.getPersonId() == null) {
				modelMap.put("success", false);
				modelMap.put("errMsg", "请登录");
				return modelMap;
			}
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		// 得到shop
		Shop shop = null;
		String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
		if (shopStr == null) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "店铺信息为空");
			return modelMap;
		}
		try {
			ObjectMapper mapper = new ObjectMapper();
			shop = mapper.readValue(shopStr, Shop.class);
			// 设置必要的信息
			shop.setOwner(user);
			shop.setCreateTime(new Date());
			shop.setLastEditTime(new Date());
			shop.setEnableStatus(0);
			shop.setPriority(0);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "格式不正确");
			return modelMap;
		}
		// 处理缩略图
		ImageHolder imageHolder = null;
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest resolveMultipart = multipartResolver.resolveMultipart(request);
			MultipartFile thumbnailImg = resolveMultipart.getFile("thumbnailImg");
			if (thumbnailImg == null) {
				modelMap.put("success", false);
				modelMap.put("errMsg", "缩略图为空");
				return modelMap;
			}
			imageHolder = new ImageHolder(thumbnailImg.getInputStream(), thumbnailImg.getOriginalFilename());
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "缩略图不能为空");
			return modelMap;
		}
		try {
			ShopExecution se = shopService.addShop(shop, imageHolder);
			if (se.getState() == ShopStateEnum.SUCCESS.getState()) {
				modelMap.put("success", true);
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", "新增店铺失败");
			}
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}

	// 编辑店铺的初始化信息
	@GetMapping("/editshopinfo")
	public Map<String, Object> editShopInfo(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		long shopId = HttpServletRequestUtil.getLong(request, "shopId");
		Shop shop = shopService.getByShopId(shopId);
		List<Area> areaList = areaService.getAreaList();
		List<ShopCategory> shopCategoryList = shopCategoryService.getShopCategory(new ShopCategory());
		modelMap.put("shop", shop);
		modelMap.put("areaList", areaList);
		modelMap.put("shopCategoryList", shopCategoryList);
		modelMap.put("success", true);
		return modelMap;
	}

	// 编辑店铺
	@PostMapping("/editshop")
	public Map<String, Object> editShop(HttpServletRequest request) throws IOException {
		Map<String, Object> modelMap = new HashMap<>();

		// 验证码
		if (!VerifyCodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "验证码错误！");
			return modelMap;
		}
		// 获取shop信息
		Shop shop = null;
		String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
		if (shopStr != null) {
			try {
				ObjectMapper mapper = new ObjectMapper();
				shop = mapper.readValue(shopStr, Shop.class);
			} catch (Exception e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
				return modelMap;
			}
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "shop信息获取失败");
			return modelMap;
		}
		// 处理缩略图
		ImageHolder imageHolder = null;
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest resolveMultipart = multipartResolver.resolveMultipart(request);
			MultipartFile thumbnailImg = resolveMultipart.getFile("thumbnailImg");
			if (thumbnailImg != null) {
				imageHolder = new ImageHolder(thumbnailImg.getInputStream(), thumbnailImg.getOriginalFilename());
			}
		}
		// 修改
		try {
			shop.setLastEditTime(new Date());
			ShopExecution se = shopService.modifyShop(shop, imageHolder);
			if (se.getState() == ShopStateEnum.SUCCESS.getState()) {
				modelMap.put("success", true);
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", "修改店铺信息失败");
			}
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}

	// 获取店铺列表
	@GetMapping("/getshoplist")
	public Map<String, Object> getShopList(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		// 从session中获取personinfo，前期先硬编码创建
//		PersonInfo user = new PersonInfo();
//		user.setPersonId(1L);
//		user.setPersonName("xiu");
//		request.getSession().setAttribute("user", user);

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

	// 获取shopId，将当前shop传入session中
	@GetMapping("/shopmanagementinfo")
	public Map<String, Object> shopmanagement(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		long shopId = HttpServletRequestUtil.getLong(request, "shopId");
		if (shopId <= 0L) {
			Object currentShopObj = request.getSession().getAttribute("currentShop");
			if (currentShopObj == null) {
				modelMap.put("redirect", true);
			} else {
				Shop currentShop = (Shop) currentShopObj;
				modelMap.put("redirect", false);
				modelMap.put("shopId", currentShop.getShopId());
			}
		} else {
			Shop currentShop = new Shop();
			currentShop.setShopId(shopId);
			request.getSession().setAttribute("currentShop", currentShop);
			modelMap.put("redirect", false);
		}
		return modelMap;
	}

	// 获取商品类别
	@GetMapping("/productcategorylist")
	public Map<String, Object> productCategoryList(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		// 从session中获取currentShop
		Shop currentShop = null;
		try {
			currentShop = (Shop) request.getSession().getAttribute("currentShop");
			if (currentShop != null && currentShop.getShopId() >= 0) {
				// 查询，返回结果
				try {
					List<ProductCategory> productCategoryList = productCategoryService
							.getProductCategoryByShopId(currentShop.getShopId());
					modelMap.put("success", true);
					modelMap.put("productCategoryList", productCategoryList);
				} catch (Exception e) {
					modelMap.put("success", false);
					modelMap.put("errMsg", e.getMessage());
				}
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", "currentShop获取失败");
			}
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}

	// 批量新增商品类别
	@PostMapping("/batchaddproductcategory")
	public Map<String, Object> batchAddProductCategory(@RequestBody List<ProductCategory> productCategoryList,
			HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		try {
			Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
			// 空值判断
			if (currentShop != null && currentShop.getShopId() != null && productCategoryList != null
					&& productCategoryList.size() > 0) {
				// 对list处理
				productCategoryList.forEach(x -> {
					x.setShopId(currentShop.getShopId());
					x.setCreateTime(new Date());
				});
				ProductCategoryExecution pce = productCategoryService.batchAddProductCategory(productCategoryList);
				if (pce.getState() == ProductCategoryStateEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", "新增商品类别失败！");
				}
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", "店铺或商品类别信息为空");
			}
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}

	// 单个删除商品类别
	@PostMapping("/removeproductcategory")
	public Map<String, Object> removeProductCategory(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		try {
			// 获取currentShop
			Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
			if (currentShop != null && currentShop.getShopId() != null) {
				long productCategoryId = HttpServletRequestUtil.getLong(request, "productCategoryId");
				if (productCategoryId > -1) {
					ProductCategoryExecution pce = productCategoryService.removeProductCategory(productCategoryId,
							currentShop.getShopId());
					if (pce.getState() == ProductCategoryStateEnum.SUCCESS.getState()) {
						modelMap.put("success", true);
					} else {
						throw new ProductOperationalException("数据库删除productCategory失败！");
					}
				} else {
					throw new ProductOperationalException("productCategory信息为空");
				}

			} else {
				throw new ProductOperationalException("currentShop获取失败！");
			}
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}

	// 获取productCategory列表，根据shopId
	@GetMapping("/getproductcategorylist")
	public Map<String, Object> getProductCategoryList(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		try {
			Shop currentShop = (Shop)request.getSession().getAttribute("currentShop");
			if(currentShop!=null && currentShop.getShopId()!=null) {
				List<ProductCategory> productCategoryList = productCategoryService.getProductCategoryByShopId(currentShop.getShopId());
				modelMap.put("success",true);
				modelMap.put("productCategoryList", productCategoryList);
			}else {
				modelMap.put("success", false);
				modelMap.put("errMsg", "currentShop获取失败");
			}
		}catch(Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg",e.getMessage());
		}
		return modelMap;
	}
	//处理登录
	@PostMapping("/handlelogin")
	public Map<String,Object> handleLogin(HttpServletRequest request) {
		Map<String,Object> modelMap = new HashMap<>();
		String username = HttpServletRequestUtil.getString(request, "username");
		String password = HttpServletRequestUtil.getString(request, "password");
		if(username !=null && password !=null) {
			LocalAuthExecution lae = localAuthService.getLocalAuthByUserNameAndPwd(username, password);
			if(lae.getState() == LocalAuthStateEnum.SUCCESS.getState()) {
				PersonInfo user = new PersonInfo();
				user.setPersonId(lae.getLocalAuth().getPersonInfo().getPersonId());
				request.getSession().setAttribute("user", user);
				modelMap.put("success", true);
			}else {
				modelMap.put("success", false);
				modelMap.put("errMsg", lae.getStateInfo());
			}
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入用户名或密码！");
		}
		return modelMap;
	}
}
