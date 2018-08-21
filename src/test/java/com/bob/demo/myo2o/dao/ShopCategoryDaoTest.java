package com.bob.demo.myo2o.dao;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bob.demo.myo2o.entity.ShopCategory;

/** 
* @author bob 
* @version 创建时间：2018年8月19日 下午7:36:54 
* 类说明 
*/
@RunWith(SpringRunner.class)
@SpringBootTest
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)//按方法名字典顺序执行
public class ShopCategoryDaoTest {
	@Autowired
	private ShopCategoryDao shopCategoryDao;
	
	@Test
	@Ignore
	public void testInsertShopCategory() {
//		ShopCategory shopCategory = new ShopCategory();
//		shopCategory.setShopCategoryName("喝的");
//		shopCategory.setShopCategoryDesc("补充水分");
//		shopCategory.setPriority(3);
//		shopCategory.setCreateTime(new Date());
//		shopCategory.setLastEditTime(new Date());
//		
//		int i = shopCategoryDao.insertShopCategory(shopCategory);
//		System.out.println(i);
//		System.out.println(shopCategory);
		
		//二级店铺分类
		ShopCategory shopCategory = new ShopCategory();
		shopCategory.setShopCategoryName("喝的");
		shopCategory.setShopCategoryDesc("饮料");
		shopCategory.setPriority(3);
		shopCategory.setCreateTime(new Date());
		shopCategory.setLastEditTime(new Date());
		
		ShopCategory parent = new ShopCategory();
		parent.setShopCategoryId(2L);
		
		shopCategory.setParent(parent);
		
		int i = shopCategoryDao.insertShopCategory(shopCategory);
		System.out.println(i);
		System.out.println(shopCategory);
	}
	@Test
//	@Ignore
	public void testQueryShopCategory() {
		ShopCategory shopCategory = new ShopCategory();
		
		ShopCategory parent= new ShopCategory();
		parent.setShopCategoryId(1L);
		
		shopCategory.setParent(parent);
		
		List<ShopCategory> list = shopCategoryDao.queryShopCategory(shopCategory);
		System.out.println(list.size());
		System.out.println(list);
	}
	@Test
	@Ignore
	public void testQueryShopCategoryById() {
		ShopCategory category = shopCategoryDao.queryShopCategoryById(5L);
		System.out.println(category);
	}

}
