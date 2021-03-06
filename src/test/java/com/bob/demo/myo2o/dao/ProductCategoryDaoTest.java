package com.bob.demo.myo2o.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bob.demo.myo2o.entity.ProductCategory;

/** 
* @author bob 
* @version 创建时间：2018年8月26日 下午3:41:56 
* 类说明 
*/
@RunWith(SpringRunner.class)
@SpringBootTest
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)//按方法名字典顺序执行
public class ProductCategoryDaoTest {
	
	@Autowired
	private ProductCategoryDao productCategoryDao;
	
	@Test
	@Ignore
	public void testBatchInsert() {
		ProductCategory productCategory1 = new ProductCategory();
		productCategory1.setShopId(6L);
		productCategory1.setProductCategoryName("鱼类");
		productCategory1.setCreateTime(new Date());
		productCategory1.setPriority(1);
		
		ProductCategory productCategory2 = new ProductCategory();
		productCategory2.setShopId(6L);
		productCategory2.setProductCategoryName("蔬菜类");
		productCategory2.setCreateTime(new Date());
		productCategory2.setPriority(1);
		
		List<ProductCategory> categories = new ArrayList<>();
		categories.add(productCategory1);
		categories.add(productCategory2);
		
		int i = productCategoryDao.batchInsertProductCategory(categories);
		System.out.println(i);
	}
	@Test
	@Ignore
	public void testQueryByShopId() {
		 List<ProductCategory> list = productCategoryDao.queryByShopId(6L);
		 System.out.println(list.size());
		 System.out.println(list);
	}
	@Test
	@Ignore
	public void testDeleteById() {
		int i = productCategoryDao.deleteById(3L,6);
		System.out.println(i);
	}
}
