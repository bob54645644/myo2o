package com.bob.demo.myo2o.dao;

import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bob.demo.myo2o.entity.Area;

/** 
* @author bob 
* @version 创建时间：2018年8月19日 下午4:35:00 
* 类说明 
*/
@RunWith(SpringRunner.class)
@SpringBootTest
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)//按方法名字典顺序执行
public class AreaTest {
	@Autowired
	private AreaDao areaDao;
	@Test
	@Ignore
	public void testInsertArea() {
		Area area = new Area();
		area.setAreaName("南苑");
		area.setPriority(2);
		area.setCreateTime(new Date());
		area.setLastEditTime(new Date());
		
		areaDao.insertArea(area);
	}
	@Test
//	@Ignore
	public void testQueryAreaList() {
		List<Area> list = areaDao.queryAreaList();
		System.out.println(list.size());
		System.out.println(list);
	}
}
