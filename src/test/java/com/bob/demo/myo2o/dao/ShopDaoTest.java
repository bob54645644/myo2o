package com.bob.demo.myo2o.dao;

import java.util.Date;
import java.util.List;

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
		shop.setShopName("牛肉面");
		shop.setShopDesc("面条劲道，汤汁香浓。");
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
		shopCategory.setShopCategoryId(3L);
		
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
	@Ignore
	public void testQueryById() {
		Shop shop = shopDao.queryShopById(1L);
		System.out.println(shop);
	}
	@Test
	@Ignore
	public void testQuery() {
		Shop shop = new Shop();
		
//		ShopCategory shopCategory = new ShopCategory();
////		shopCategory.setShopCategoryId(5L);
//		
//		ShopCategory parent = new ShopCategory();
//		parent.setShopCategoryId(1L);
//		shopCategory.setParent(parent);
//		
//		shop.setShopCategory(shopCategory);
		
//		Area area = new Area();
//		area.setAreaId(1);
//		shop.setArea(area);
		
//		shop.setEnableStatus(1);
//		shop.setShopName("米");
		
		PersonInfo owner = new PersonInfo();
		owner.setPersonId(1L);
		shop.setOwner(owner);
		
		List<Shop> list = shopDao.queryShop(shop, 0, 100);
		System.out.println(list);
		int i = shopDao.queryShopCount(shop);
		System.out.println(i);
	}
	@Test
	public void testUpdate() {
		Shop shop = new Shop();
		shop.setShopId(1L);
		shop.setShopName("小米粥");
		shop.setShopDesc("好喝，养胃");
		shop.setAdvice("没有建议");
		shop.setShopAddr("路南");
		shop.setPhone("110");
		shop.setPriority(4);
		shop.setLastEditTime(new Date());
		shop.setEnableStatus(1);
		
		Area area = new Area();
		area.setAreaId(3);
		shop.setArea(area);
		
		ShopCategory shopCategory = new ShopCategory();
		shopCategory.setShopCategoryId(3L);
		shop.setShopCategory(shopCategory);
		
		PersonInfo owner = new PersonInfo();
		owner.setPersonId(1L);
		shop.setOwner(owner);
		
		int i = shopDao.updateShop(shop);
		System.out.println(i);
		System.out.println(shop);
	}

}
