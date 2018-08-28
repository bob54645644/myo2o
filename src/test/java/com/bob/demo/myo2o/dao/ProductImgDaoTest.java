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

import com.bob.demo.myo2o.entity.ProductImg;

/** 
* @author bob 
* @version 创建时间：2018年8月27日 下午5:19:15 
* 类说明 
*/
@RunWith(SpringRunner.class)
@SpringBootTest
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)//按方法名字典顺序执行
public class ProductImgDaoTest {
	@Autowired
	private ProductImgDao productImgDao;
	
	@Test
	@Ignore
	public void testBatchInsert() {
		ProductImg img = new ProductImg();
		img.setImgDesc("test");
		img.setCreateTime(new Date());
		img.setPriority(1);
		img.setProductId(2L);
		
		List<ProductImg> imgList = new ArrayList<>();
		imgList.add(img);
		
		int i = productImgDao.batchInsertProductImg(imgList);
		System.out.println(i);
		System.out.println(imgList);
	}
	@Test
	@Ignore
	public void testQueryByProductId() {
		List<ProductImg> list = productImgDao.queryByProductId(2L);
		System.out.println(list.size());
		System.out.println(list);
	}
	@Test
	@Ignore
	public void testDelete() {
		int i = productImgDao.deleteByProductId(2L);
		System.out.println(i);
	}
}
