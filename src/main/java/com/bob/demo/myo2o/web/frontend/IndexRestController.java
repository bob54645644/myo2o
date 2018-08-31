package com.bob.demo.myo2o.web.frontend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bob.demo.myo2o.dto.ProductExecution;
import com.bob.demo.myo2o.dto.ShopExecution;
import com.bob.demo.myo2o.entity.Area;
import com.bob.demo.myo2o.entity.HeadLine;
import com.bob.demo.myo2o.entity.Product;
import com.bob.demo.myo2o.entity.ProductCategory;
import com.bob.demo.myo2o.entity.Shop;
import com.bob.demo.myo2o.entity.ShopCategory;
import com.bob.demo.myo2o.enums.ProductStateEnum;
import com.bob.demo.myo2o.enums.ShopStateEnum;
import com.bob.demo.myo2o.service.AreaService;
import com.bob.demo.myo2o.service.HeadLineService;
import com.bob.demo.myo2o.service.ProductCategoryService;
import com.bob.demo.myo2o.service.ProductService;
import com.bob.demo.myo2o.service.ShopCategoryService;
import com.bob.demo.myo2o.service.ShopService;
import com.bob.demo.myo2o.utils.HttpServletRequestUtil;

/**
 * @author bob
 * @version 创建时间：2018年8月29日 下午9:01:00 类说明
 */
@RestController
@RequestMapping("/frontend")
public class IndexRestController {
	@Autowired
	private HeadLineService headLineService;
	@Autowired
	private ShopCategoryService shopCategoryService;
	@Autowired
	private AreaService areaService;

	@Autowired
	private ShopService shopService;
	@Autowired
	private ProductCategoryService productCategoryService;
	@Autowired
	private ProductService productService;

	// 得到头条信息和一级店铺类别
	@GetMapping("/getheadlinelist")
	public Map<String, Object> getHeadLineList() {
		Map<String, Object> modelMap = new HashMap<>();

		// 头条
		HeadLine headLineCondition = new HeadLine();
		headLineCondition.setEnableStatus(1);

		try {
			List<HeadLine> headLineList = headLineService.getHeadLineByCondition(headLineCondition);
			// 一级分类
			List<ShopCategory> shopCategoryList = shopCategoryService.getShopCategory(null);
			modelMap.put("success", true);
			modelMap.put("headLineList", headLineList);
			modelMap.put("shopCategoryList", shopCategoryList);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}

	// 获取分类信息和区域信息
	@GetMapping("/getshoplistinfo")
	public Map<String, Object> getShopListInfo(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		try {
			// 获取parentId，有则是一级店铺类别，没有则是二级店铺类别
			long parentId = HttpServletRequestUtil.getLong(request, "parentId");
			ShopCategory shopCategoryCondition = new ShopCategory();
			if (parentId > -1) {
				ShopCategory parent = new ShopCategory();
				parent.setShopCategoryId(parentId);
				shopCategoryCondition.setParent(parent);
			}
			List<ShopCategory> shopCategoryList = shopCategoryService.getShopCategory(shopCategoryCondition);

			// 获取区域信息
			List<Area> areaList = areaService.getAreaList();

			modelMap.put("success", true);
			modelMap.put("shopCategoryList", shopCategoryList);
			modelMap.put("areaList", areaList);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}

	@GetMapping("/getshoplist")
	public Map<String, Object> getShopList(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		
		int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
		int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
		if(pageIndex>-1 && pageSize>-1) {
			try {
				// 获取各种信息
				Shop shopCondition = new Shop();
				// 商店类别
				long shopCategoryId = HttpServletRequestUtil.getLong(request, "shopCategoryId");
				if (shopCategoryId > -1L) {
					ShopCategory shopCategoryCondition = new ShopCategory();
					shopCategoryCondition.setShopCategoryId(shopCategoryId);
					shopCondition.setShopCategory(shopCategoryCondition);
				}
				// 区域信息
				int areaId = HttpServletRequestUtil.getInt(request, "areaId");
				if (areaId > -1) {
					Area area = new Area();
					area.setAreaId(areaId);
					shopCondition.setArea(area);
				}
				// 商店名称
				String shopName = HttpServletRequestUtil.getString(request, "shopName");
				if (shopName != null) {
					shopName = shopName.trim();
					shopCondition.setShopName(shopName);
				}
				
				ShopExecution se = shopService.getShopListByCondition(shopCondition, pageIndex, pageSize);
				if(se.getState() == ShopStateEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
					modelMap.put("shopList", se.getShopList());
					modelMap.put("count", se.getCount());
				}else {
					modelMap.put("success", false);
					modelMap.put("errMsg", se.getStateInfo());
				}
			}catch(Exception e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			}
			
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "检索信息不正确！");
		}
		
		return modelMap;
	}
	//商店详情初始化信息
	@GetMapping("/getshopdetailinfo")
	public Map<String,Object> getShopDetailInfo(HttpServletRequest request){
		Map<String,Object> modelMap = new HashMap<>();
		//获取shopId
		long shopId = HttpServletRequestUtil.getLong(request, "shopId");
		if(shopId>-1) {
			//商品类别
			List<ProductCategory> productCategoryList = productCategoryService.getProductCategoryByShopId(shopId);
			//店铺信息
			Shop shop = shopService.getByShopId(shopId);
			modelMap.put("success", true);
			modelMap.put("productCategoryList", productCategoryList);
			modelMap.put("shop", shop);
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "shopId获取失败");
		}
		return modelMap;
	}
	//商店详情
	@GetMapping("/getshopdetail")
	public Map<String,Object> getShopDetail(HttpServletRequest request){
		Map<String,Object> modelMap = new HashMap<>();
		//获取shopId
		long shopId = HttpServletRequestUtil.getLong(request, "shopId");
		//获取pageIndex 、pageSize
		int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
		int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
		if(shopId>-1 && pageIndex>-1 && pageSize >-1) {
			Product productCondition = new Product();
			
			Shop shop = new Shop();
			shop.setShopId(shopId);
			productCondition.setShop(shop);
			
			
			//获取shopCategoryId
			long productCategoryId = HttpServletRequestUtil.getLong(request, "productCategoryId");
			if(productCategoryId>-1) {
				ProductCategory productCategory=new ProductCategory();
				productCategory.setProductCategoryId(productCategoryId);
				productCondition.setProductCategory(productCategory);
			}
			//获取productName
			String productName = HttpServletRequestUtil.getString(request, "productName");
			if(productName !=null) {
				productCondition.setProductName(productName);
			}
			ProductExecution pe = productService.getProductByCondition(productCondition, pageIndex, pageSize);
			if(pe.getState() == ProductStateEnum.SUCCESS.getState()) {
				modelMap.put("success", true);
				modelMap.put("count", pe.getCount());
				modelMap.put("productList", pe.getProductList());
			}else {
				modelMap.put("success", false);
				modelMap.put("errMsg", pe.getStateInfo());
			}
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "必要参数获取失败");
		}
		return modelMap;
	}

}
