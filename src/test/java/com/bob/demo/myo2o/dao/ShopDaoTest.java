package com.bob.demo.myo2o.dao;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bob.demo.myo2o.entity.Area;
import com.bob.demo.myo2o.entity.PersonInfo;
import com.bob.demo.myo2o.entity.Shop;
import com.bob.demo.myo2o.entity.ShopCategory;

/** 
* @author bob 
* @version 创建时间：2018年8月19日 下午10:43:24 
* 类说明 
*/
@RunWith(SpringRunner.class)
@SpringBootTest
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)//按方法名字典顺序执行
public class ShopDaoTest {
	@Autowired
	private ShopDao shopDao;
	
	@Test
	@Ignore
	public void testInsertShop() {
		Shop shop = new Shop();
		shop.setShopName("可口可乐");
		shop.setShopDesc("冰爽");
		shop.setShopAddr("路东");
		shop.setPhone("120");
		shop.setPriority(4);
		shop.setCreateTime(new Date());
		shop.setLastEditTime(new Date());
		shop.setEnableStatus(1);
		shop.setAdvice("没有建议");
		
		Area area = new Area();
		area.setAreaId(1);
		
		ShopCategory shopCategory = new ShopCategory();
		shopCategory.setShopCategoryId(5L);
		
		PersonInfo personInfo = new PersonInfo();
		personInfo.setPersonId(1L);
		
		shop.setArea(area);
		shop.setShopCategory(shopCategory);
		shop.setOwner(personInfo);
		
		int i = shopDao.insertShop(shop);
		System.out.println(i);
		System.out.println(shop);
	}
	@Test
	public void testQueryById() {
		Shop shop = shopDao.queryShopById(1L);
		System.out.println(shop);
	}

}
