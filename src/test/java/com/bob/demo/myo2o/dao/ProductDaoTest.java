package com.bob.demo.myo2o.dao;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bob.demo.myo2o.entity.Product;
import com.bob.demo.myo2o.entity.ProductCategory;
import com.bob.demo.myo2o.entity.Shop;

/** 
* @author bob 
* @version 创建时间：2018年8月27日 下午4:13:12 
* 类说明 
*/
@RunWith(SpringRunner.class)
@SpringBootTest
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)//按方法名字典顺序执行
public class ProductDaoTest {
	@Autowired
	private ProductDao productDao;
	@Test
	@Ignore
	public void testInsert() {
		Product product = new Product();
		product.setProductName("240ml Kola");
		product.setProductDesc("解渴");
		product.setNormalPrice("3.5");
		product.setPromotionPrice("3.0");
		product.setPriority(1);
		product.setCreateTime(new Date());
		product.setLastEditTime(new Date());
		product.setEnableStatus(1);
		
		ProductCategory productCategory = new ProductCategory();
		productCategory.setProductCategoryId(9L);
		product.setProductCategory(productCategory);
		
		Shop shop = new Shop();
		shop.setShopId(2L);
		product.setShop(shop);
		
		int i = productDao.insertProduct(product);
		System.out.println(i);
		System.out.println(product);
	}
	@Test
//	@Ignore
	public void testQueryById() {
		Product product = productDao.queryById(7L);
		System.out.println(product);
	}
	@Test
	@Ignore
	public void testQueryProduct() {
		Product product = new Product();
		product.setProductName("kola");
		
		Shop shop = new Shop();
		shop.setShopId(2L);
		
		ProductCategory productCategory = new ProductCategory();
		productCategory.setProductCategoryId(9L);
		
		product.setEnableStatus(1);
		product.setShop(shop);
		product.setProductCategory(productCategory);
		
		List<Product> list = productDao.queryProduct(product, 0, 100);
		int i = productDao.queryProductCount(product);
		System.out.println(list.size());
		System.out.println(list);
		System.out.println(i);
	}
	@Test
	@Ignore
	public void testUpdate() {
		Product product = new Product();
		product.setProductId(2L);
		product.setProductName("240ml Kola");
		product.setProductDesc("解渴");
		product.setNormalPrice("3.5");
		product.setPromotionPrice("3.0");
		product.setPriority(1);
		product.setLastEditTime(new Date());
		product.setEnableStatus(1);
		
		ProductCategory productCategory = new ProductCategory();
		productCategory.setProductCategoryId(9L);
		product.setProductCategory(productCategory);
		
		Shop shop = new Shop();
		shop.setShopId(2L);
		product.setShop(shop);
		int i = productDao.updateProduct(product);
		System.out.println(i);
		System.out.println(product);
	}
	@Test
	@Ignore
	public void testDelete() {
		int i = productDao.deleteProduct(1L, 2L);
		System.out.println(i);
	}
	@Test
	@Ignore
	public void testToNull() {
		int i = productDao.updateProductCategoryIdToNull(10L);
		System.out.println(i);
	}
}
