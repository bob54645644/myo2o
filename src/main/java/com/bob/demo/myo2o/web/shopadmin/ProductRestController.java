package com.bob.demo.myo2o.web.shopadmin;

import java.io.IOException;
import java.util.ArrayList;
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

import com.bob.demo.myo2o.dto.ProductExecution;
import com.bob.demo.myo2o.entity.Product;
import com.bob.demo.myo2o.entity.ProductCategory;
import com.bob.demo.myo2o.entity.Shop;
import com.bob.demo.myo2o.enums.ProductStateEnum;
import com.bob.demo.myo2o.service.ProductCategoryService;
import com.bob.demo.myo2o.service.ProductService;
import com.bob.demo.myo2o.utils.HttpServletRequestUtil;
import com.bob.demo.myo2o.utils.ImageHolder;
import com.bob.demo.myo2o.utils.VerifyCodeUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author bob
 * @version 创建时间：2018年8月28日 下午4:11:48 类说明
 */
@RestController
@RequestMapping("/shopadmin")
public class ProductRestController {
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductCategoryService productCategoryService;
	@Autowired
	private MultipartResolver multipartResolver;

	// 获得product列表
	@GetMapping("/getproductlist")
	public Map<String, Object> getProductList(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		try {
			// 通过session获取currentShop
			Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");

			if (currentShop == null || currentShop.getShopId() == null) {
				modelMap.put("success", false);
				modelMap.put("errMsg", "currentShop获取失败！");
				return modelMap;
			}

			Product productCondition = new Product();
			productCondition.setShop(currentShop);
			ProductExecution pe = productService.getProductByCondition(productCondition, 0, 100);
			if (pe.getState() == ProductStateEnum.SUCCESS.getState()) {
				modelMap.put("success", true);
				modelMap.put("productList", pe.getProductList());
				modelMap.put("count", pe.getCount());
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", pe.getStateInfo());
			}

		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}

	// 新增product
	@PostMapping("/addproduct")
	public Map<String, Object> addProduct(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		// 首先验证验证码
		if (!VerifyCodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "验证码错误");
			return modelMap;
		}
		// 获得currentShop
		Shop currentShop = null;
		try {
			currentShop = (Shop) request.getSession().getAttribute("currentShop");
			if (currentShop == null || currentShop.getShopId() == null) {
				modelMap.put("success", false);
				modelMap.put("errMsg", "currentShop信息获取失败");
				return modelMap;
			}
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "currentShop信息获取失败");
			return modelMap;
		}
		// 获得product
		String productStr = HttpServletRequestUtil.getString(request, "productStr");
		Product product = null;
		if (productStr != null && !"".equals(productStr)) {
			try {
				ObjectMapper mapper = new ObjectMapper();
				product = mapper.readValue(productStr, Product.class);
				// 将当前shop赋值进去
				product.setShop(currentShop);
			} catch (Exception e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
				return modelMap;
			}

		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "productStr获取失败！");
			return modelMap;
		}
		// 处理图片
		// 缩略图
		ImageHolder thumbnailImg = null;
		// 详情图
		List<ImageHolder> imgList = new ArrayList<>();
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartHttpServletRequest = multipartResolver.resolveMultipart(request);
			// 缩略图
			try {
				MultipartFile thumbnailFile = multipartHttpServletRequest.getFile("thumbnailImg");
				thumbnailImg = new ImageHolder(thumbnailFile.getInputStream(), thumbnailFile.getOriginalFilename());
			} catch (IOException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
				return modelMap;
			}
			// 详情图，最多六张
			try {
				for (int i = 0; i < 6; i++) {
					MultipartFile productImg = multipartHttpServletRequest.getFile("productImg" + i);
					if (productImg != null) {
						ImageHolder productImgHolder = new ImageHolder(productImg.getInputStream(),
								productImg.getOriginalFilename());
						imgList.add(productImgHolder);
					} else {
						// 不足6张，提前退出循环
						break;
					}
				}

			} catch (Exception e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
				return modelMap;
			}
		}
		// 新增到数据库
		try {
			ProductExecution pe = productService.addProduct(product, thumbnailImg, imgList);
			if (pe.getState() == ProductStateEnum.SUCCESS.getState()) {
				modelMap.put("success", true);
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", "新增product失败");
			}
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}

	// 修改product 包括下架功能
	@PostMapping("/modifyproduct")
	public Map<String, Object> modifyProduct(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		// 判断是不是上下架
		boolean changeStatus = HttpServletRequestUtil.getBoolean(request, "status");
		// 验证码，是上下架操作的话，跳过验证码
		if (!changeStatus && !VerifyCodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "验证码错误！");
			return modelMap;
		}
		// 获取currentShop
		try {
			Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
			if (currentShop == null || currentShop.getShopId() == null) {
				modelMap.put("success", false);
				modelMap.put("errMsg", "currentShop获取失败");
				return modelMap;
			}
			// 获取product
			String productStr = HttpServletRequestUtil.getString(request, "productStr");
			ObjectMapper mapper = new ObjectMapper();
			Product product = mapper.readValue(productStr, Product.class);
			// 验证该product是否属于该商店
			product.setShop(currentShop);
			List<Product> productList = productService.getProductByCondition(product, 0, 100).getProductList();
			// 是否属于该店铺置位符
			boolean flag = false;
			for (Product p : productList) {
				if (p.getProductId() == product.getProductId()) {
					flag = true;
				}
			}
			if (flag == false) {
				modelMap.put("success", false);
				modelMap.put("errMsg", "非法操作");
				return modelMap;
			}
			//处理上下架
			if(changeStatus) {
				Product nowProduct = productService.getProductById(product.getProductId());
				if(nowProduct.getEnableStatus()==1) {
					product.setEnableStatus(0);
				}else {
					product.setEnableStatus(1);
				}
			}
			// 处理图片
			ImageHolder thumbnailImg = null;
			List<ImageHolder> imgHolderList = new ArrayList<>();
			if (multipartResolver.isMultipart(request)) {
				MultipartHttpServletRequest resolveMultipart = multipartResolver.resolveMultipart(request);
				// 处理缩略图
				MultipartFile thumbnailFile = resolveMultipart.getFile("thumbnailImg");
				if (thumbnailFile != null && thumbnailFile.getInputStream() != null
						&& thumbnailFile.getOriginalFilename() != null) {
					//
					thumbnailImg = new ImageHolder(thumbnailFile.getInputStream(), thumbnailFile.getOriginalFilename());
				}
				// 处理详情图
				for (int i = 0; i < 6; i++) {
					MultipartFile productImg = resolveMultipart.getFile("productImg" + i);
					if (productImg != null && productImg.getInputStream() != null
							&& productImg.getOriginalFilename() != null) {
						ImageHolder productImgHolder = new ImageHolder(productImg.getInputStream(), productImg.getOriginalFilename());
						imgHolderList.add(productImgHolder);
					}else {
						//不足6个，提前退出
						break;
					}
				}
			}
			// 新增到product表
			ProductExecution pe2 = productService.modifyProduct(product, thumbnailImg, imgHolderList);
			if(pe2.getState() == ProductStateEnum.SUCCESS.getState()) {
				modelMap.put("success", true);
			}else {
				modelMap.put("success", false);
				modelMap.put("errMsg", pe2.getStateInfo());
			}

		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}
	//编辑时，获得初始化信息
	@GetMapping("/geteditinitinfo")
	public Map<String,Object> getEditInitInfo(HttpServletRequest request){
		Map<String,Object> modelMap = new HashMap<>();
		//获取currentShop
		Shop currentShop = null;
		try {
			currentShop = (Shop)request.getSession().getAttribute("currentShop");
			if(currentShop == null || currentShop.getShopId()==null) {
				modelMap.put("success", false);
				modelMap.put("errMsg", "currentShop获取失败");
				return modelMap;
			}
		}catch(Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		
		//从request中获取productId
		long productId = HttpServletRequestUtil.getLong(request, "productId");
		if(productId >-1L) {
			Product product = productService.getProductById(productId);
			List<ProductCategory> productCategoryList = productCategoryService.getProductCategoryByShopId(currentShop.getShopId());
			modelMap.put("success", true);
			modelMap.put("product", product);
			modelMap.put("productCategoryList", productCategoryList);
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "productId获取失败！");
		}
		return modelMap;
	}

}
