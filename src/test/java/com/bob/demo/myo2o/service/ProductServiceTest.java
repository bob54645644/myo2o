package com.bob.demo.myo2o.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bob.demo.myo2o.dto.ProductExecution;
import com.bob.demo.myo2o.entity.Product;
import com.bob.demo.myo2o.entity.ProductCategory;
import com.bob.demo.myo2o.entity.Shop;
import com.bob.demo.myo2o.utils.ImageHolder;

/** 
* @author bob 
* @version 创建时间：2018年8月28日 下午12:32:53 
* 类说明 
*/
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)//按方法名字典顺序执行
public class ProductServiceTest {
	@Autowired
	private ProductService productService;
	@Test
	@Ignore
	public void testAddProduct() throws Exception {
		Product product = new Product();
		product.setProductName("testproduct");
		product.setProductDesc("testdesc");
		product.setNormalPrice("3.5");
		product.setPromotionPrice("3");
		product.setProductImg("testImg");
		
		ProductCategory productCategory = new ProductCategory();
		productCategory.setProductCategoryId(14L);
		product.setProductCategory(productCategory);
		
		Shop shop = new Shop();
		shop.setShopId(2L);
		product.setShop(shop);
		
		File file = new File("e:/ftp.png");
		FileInputStream inputStream = new FileInputStream(file);
		
		ImageHolder thumbnailImage = new ImageHolder(inputStream, file.getName());
		ImageHolder thumbnailImage2 = new ImageHolder(new FileInputStream(file), file.getName());
		
		List<ImageHolder> ImgList = new ArrayList<>();
		ImgList.add(thumbnailImage2);
		
		ProductExecution pe = productService.addProduct(product, thumbnailImage, ImgList);
		System.out.println(pe.getStateInfo());
		System.out.println(product);
		System.out.println(ImgList);
	}
	@Test
	@Ignore
	public void testGetByCondition() {
		Product product = new Product();
		product.setProductName("kola");
		
		ProductExecution pe = productService.getProductByCondition(product, 0, 100);
		System.out.println(pe.getStateInfo());
		System.out.println(pe.getCount());
		System.out.println(pe.getProductList());
	}
	@Test
	public void testUpdate() throws Exception {
		Product product = new Product();
		product.setProductId(7L);
		product.setProductName("testproduct");
		product.setProductDesc("testdesc");
		product.setNormalPrice("3.5");
		product.setPromotionPrice("3");
		
		ProductCategory productCategory = new ProductCategory();
		productCategory.setProductCategoryId(14L);
		product.setProductCategory(productCategory);
		
		Shop shop = new Shop();
		shop.setShopId(2L);
		product.setShop(shop);
		
		File file = new File("e:/ftp.png");
		FileInputStream inputStream = new FileInputStream(file);
		
		ImageHolder thumbnailImage = new ImageHolder(inputStream, file.getName());
		ImageHolder thumbnailImage2 = new ImageHolder(new FileInputStream(file), file.getName());
		
		List<ImageHolder> ImgList = new ArrayList<>();
		ImgList.add(thumbnailImage2);
		
		ProductExecution pe = productService.modifyProduct(product, thumbnailImage, ImgList);
		System.out.println(pe.getStateInfo());
	}
}
