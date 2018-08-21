package com.bob.demo.myo2o.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bob.demo.myo2o.entity.ShopCategory;

/** 
* @author bob 
* @version 创建时间：2018年8月20日 下午8:42:06 
* 类说明 
*/
@RunWith(SpringRunner.class)
@SpringBootTest
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)//按方法名字典顺序执行
public class ShopCategoryServiceTest {
	@Autowired
	private ShopCategoryService shopCategoryService;
	
	@Test
	public void testGetShopCategoryList() {
		ShopCategory shopCategory = new ShopCategory();
		
		List<ShopCategory> list = shopCategoryService.getShopCategory(shopCategory);
		System.out.println(list.size());
		System.out.println(list);
	}
	

}
