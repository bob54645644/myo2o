package com.bob.demo.myo2o.dao;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bob.demo.myo2o.entity.HeadLine;

/** 
* @author bob 
* @version 创建时间：2018年8月29日 下午8:41:12 
* 类说明 
*/
@RunWith(SpringRunner.class)
@SpringBootTest
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)//按方法名字典顺序执行
public class HeadLineDaoTest {
	@Autowired
	private HeadLineDao headLineDao;
	
	@Test
	@Ignore
	public void testInsert() {
		HeadLine headLine = new HeadLine();
		headLine.setHeadLineName("Name2");
		headLine.setCreateTime(new Date());
		headLine.setEnableStatus(0);
		headLine.setLastEditTime(new Date());
		headLine.setPriority(1);
		headLine.setHeadLineImg("/upload/item/shop/2/product/9/9956220180829131302.jpg");
		
		int i = headLineDao.insertHeadLine(headLine);
		System.out.println(i);
		System.out.println(headLine);
	}
	@Test
	@Ignore
	public void testQuery() {
		HeadLine headLine = new HeadLine();
		headLine.setEnableStatus(1);
		List<HeadLine> list = headLineDao.queryHeadLineListByCondition(headLine);
		System.out.println(list.size());
	}
}
