package com.bob.demo.myo2o.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bob.demo.myo2o.dto.ShopExecution;
import com.bob.demo.myo2o.entity.Area;
import com.bob.demo.myo2o.entity.PersonInfo;
import com.bob.demo.myo2o.entity.Shop;
import com.bob.demo.myo2o.entity.ShopCategory;
import com.bob.demo.myo2o.utils.ImageHolder;

/** 
* @author bob 
* @version 创建时间：2018年8月20日 下午3:38:06 
* 类说明 
*/
@RunWith(SpringRunner.class)
@SpringBootTest
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)//按方法名字典顺序执行
public class ShopServiceTest {
	@Autowired
	private ShopService shopService;
	
	@Test
	@Ignore
	public void testGetShopList() {
		Shop shop = new Shop();
		PersonInfo owner = new PersonInfo();
		owner.setPersonId(1L);
		shop.setOwner(owner);
		ShopExecution se = shopService.getShopListByCondition(shop, 0, 100);
		System.out.println(se.getCount());
		System.out.println(se.getShopList());
	}
	@Test
	@Ignore
	public void testAddShop() throws FileNotFoundException {
		Shop shop = new Shop();
		shop.setShopName("烧烤");
		shop.setShopDesc("酥脆，香辣");
		shop.setPhone("111");
		shop.setShopAddr("路西");
		shop.setAdvice("没有建议");
		shop.setCreateTime(new Date());
		shop.setLastEditTime(new Date());
		shop.setPriority(4);
		shop.setEnableStatus(1);
		
		Area area = new Area();
		area.setAreaId(2);
		shop.setArea(area);
		
		ShopCategory shopCategory = new ShopCategory();
		shopCategory.setShopCategoryId(6L);
		shop.setShopCategory(shopCategory);
		
		PersonInfo owner = new PersonInfo();
		owner.setPersonId(1L);
		shop.setOwner(owner);
		
		File file = new File("e:/ftp.png");
		FileInputStream inputStream = new FileInputStream(file);
		ImageHolder imageHolder = new ImageHolder(inputStream, file.getName());
		
		ShopExecution se = shopService.addShop(shop, imageHolder);
		System.out.println(se.getStateInfo());
	}
	@Test
	@Ignore
	public void testQueryById() {
		Shop shop = shopService.getByShopId(1L);
		System.out.println(shop);
	}
	@Test
	public void testModifyShop() throws FileNotFoundException {
		Shop shop = new Shop();
		shop.setShopId(6L);
		shop.setShopName("烧烤");
		shop.setShopDesc("酥脆，香辣");
		shop.setPhone("111");
		shop.setShopAddr("路西");
		shop.setAdvice("没有建议1111");
		shop.setLastEditTime(new Date());
		shop.setPriority(4);
		shop.setEnableStatus(1);
		
		Area area = new Area();
		area.setAreaId(2);
		shop.setArea(area);
		
		ShopCategory shopCategory = new ShopCategory();
		shopCategory.setShopCategoryId(6L);
		shop.setShopCategory(shopCategory);
		
		PersonInfo owner = new PersonInfo();
		owner.setPersonId(1L);
		shop.setOwner(owner);
		
		File file = new File("e:/ftp.png");
		FileInputStream inputStream = new FileInputStream(file);
		ImageHolder imageHolder = new ImageHolder(inputStream, file.getName());
		
		ShopExecution se = shopService.modifyShop(shop, imageHolder);
		System.out.println(se.getStateInfo());
	}

}
